
package zhao.siqi.mvptestdemo.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import org.xutils.x;

import butterknife.ButterKnife;
import zhao.siqi.mvptestdemo.util.CustomProgressDialog;

/**
 * activity 基类
 * Created by Bill on 2017/9/5.
 */
public abstract class BaseActivity<P extends BaseContract.BasePresenter<V>, V extends BaseContract.BaseView> extends FragmentActivity {

    public Context context;
    protected Activity mActivity;
    protected P mPresenter;

    private static final int FAST_CLICK_INTERVAL = 400;// 快速点击间隔，单位为毫秒
    private long mLastClickTime;    // 记录最后一次点击的时间
    private boolean isFirstStart = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;
        mActivity = this;

        int contentViewID = getContentViewID();

        if (contentViewID != 0) {
            setContentView(contentViewID);
        }

        ButterKnife.bind(this);
        x.view().inject(this);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 强制竖屏
        getWindow().setBackgroundDrawable(null);//避免GPU过度绘制

        initPresenter();
        initData();
        initView();
        initListeners();
    }

    protected abstract void initView();

    protected void initListeners() {

    }

    protected void initData() {

    }

    private void initPresenter() {
        mPresenter = getPresenter();
        if (mPresenter != null) {
            mPresenter.onViewAttached((V) this);
        }
    }

    protected P getPresenter() {
        return null;
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mPresenter != null) {
            mPresenter.onStart(isFirstStart);
            isFirstStart = false;
        }
    }

    /**
     * bind layout resource file
     */
    protected abstract int getContentViewID();

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            onBack();
            return true;//系统不会自己销毁页面了
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     *
     * @param token
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 将返回事件统一处理，如果在返回事件时需要一些自定义的事件就重写这个方法
     */
    protected void onBack() {
        finish();
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (mPresenter != null) {
            mPresenter.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 防止窗口泄露
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }

        if (mPresenter != null) {
            mPresenter.onViewDetached();
            mPresenter.onPresenterDestroyed();
        }
    }

    /**
     * 判断是否是快速点击
     */
    public synchronized boolean isFastClick() {
        long currentTime = System.currentTimeMillis();

        if (currentTime - mLastClickTime > FAST_CLICK_INTERVAL) {
            mLastClickTime = currentTime;
            return true;
        } else {
            return false;
        }
    }
    private CustomProgressDialog progressDialog;

    public void startProgressDialog(String tip) {
        if (progressDialog == null) {
            progressDialog = new CustomProgressDialog(context, tip);
        }
        progressDialog.show();
    }

    public void stopProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public void setProgressPercent(int percent) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.setPercent(percent);
        }
    }

    public void setProgressMessage(String message) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.setTip(message);
        }
    }
}
