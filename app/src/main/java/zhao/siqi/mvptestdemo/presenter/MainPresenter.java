package zhao.siqi.mvptestdemo.presenter;

import android.content.Context;

import org.xutils.common.Callback;

import java.util.List;
import java.util.Map;

import zhao.siqi.mvptestdemo.base.BasePresenterImpl;
import zhao.siqi.mvptestdemo.model.ProjectProgressList;
import zhao.siqi.mvptestdemo.network.MResponse;
import zhao.siqi.mvptestdemo.network.NetRequestUtil;
import zhao.siqi.mvptestdemo.network.NetResponseListener;
import zhao.siqi.mvptestdemo.view.MainContract;

import static zhao.siqi.mvptestdemo.view.MainContract.*;

/**
 * p层   数据处理层，所有的数据逻辑，业务逻辑都在这里处理；责完成View于Model间的交互
 * Created by Bill on 2017/9/5.
 */

public class MainPresenter extends BasePresenterImpl<MainContract.View> implements MainContract.Presenter {

    private View mView;
    private int mProId;
    private Context mContext;

    public MainPresenter(View mainView, int proId, Context contex) {
        mView = mainView;
        mProId = proId;
        mContext = contex;
    }

    @Override
    public void loadData() {
        // 请求网络
        loadppList();
    }

    /**
     * 加载项目进展列表
     */
    private void loadppList() {

        String url = NetRequestUtil.getInstance().getUrl(NetRequestUtil.getInstance().PROJECT_PROGRESS_LIST);
        Map map = NetRequestUtil.getInstance().getBaseMap();

        map.put("project_id", mProId);

        NetRequestUtil.getInstance().post(mContext, url, map, NetRequestUtil.REQUEST_TOKEN,
                ProjectProgressList.class, new NetResponseListener() {
                    @Override
                    public void onSuccess(MResponse response, int requestCode, String result) {

                        ProjectProgressList bean = (ProjectProgressList) response;
                        List<ProjectProgressList.DataBean> mData = bean.getData();

                        mView.setView(mData);
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback, int requestCode) {

                    }

                    @Override
                    public void onCancelled(Callback.CancelledException cex) {

                    }

                    @Override
                    public void onFinish() {

                        mView.refreshView();
                    }
                });
    }

}
