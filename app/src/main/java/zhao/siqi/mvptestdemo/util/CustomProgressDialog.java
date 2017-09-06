package zhao.siqi.mvptestdemo.util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.EmptyUtils;

import zhao.siqi.mvptestdemo.R;


public class CustomProgressDialog extends Dialog {

    private TextView mText;
    private ImageView mLoadIcon;
    private Animation mAnimation;

    private String tip;
    private Context context = null;

    public CustomProgressDialog(Context context, String tip) {
        super(context, R.style.CustomProgressDialog);
        this.context = context;
        this.tip = tip;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_dialog);

        mLoadIcon = (ImageView) findViewById(R.id.img);
        mText = (TextView) findViewById(R.id.tipTextView);
        if (EmptyUtils.isNotEmpty(tip)) {
            mText.setText(tip);
        }
        mAnimation = AnimationUtils.loadAnimation(context, R.anim.loading_anim);
        mLoadIcon.startAnimation(mAnimation);

        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    @Override
    public void show() {
        super.show();
        if (mLoadIcon != null && mAnimation != null) {
            mLoadIcon.startAnimation(mAnimation);
        }
    }

    public void setTip(String tip) {
        mText.setText(tip);
    }

    public void setPercent(float percent) {
        mText.setText(percent + "%");
    }

}
