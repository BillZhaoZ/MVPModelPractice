package zhao.siqi.mvptestdemo.mvp.presenter;

import android.content.Context;

import org.xutils.common.Callback;

import java.util.List;

import zhao.siqi.mvptestdemo.bean.ProjectProgressList;
import zhao.siqi.mvptestdemo.interfaces.IDataToPresenter;
import zhao.siqi.mvptestdemo.mvp.contract.MainContract;
import zhao.siqi.mvptestdemo.mvp.model.ProjectProgressListModel;

import static zhao.siqi.mvptestdemo.mvp.contract.MainContract.View;

/**
 * p层   数据处理层，所有的数据逻辑，业务逻辑都在这里处理；责完成View于Model间的交互
 * Created by Bill on 2017/9/5.
 */
public class MainPresenter implements MainContract.Presenter {

    private int mProId;
    private Context mContext;
    private ProjectProgressListModel model;
    private MainContract.View mView;

    public MainPresenter(View mainView, int proId, Context contex) {
        mView = mainView;
        mProId = proId;
        mContext = contex;

        this.model = new ProjectProgressListModel();

        // 建立View和presenter关系
        mView.setPresenter(this);
    }


    @Override
    public void start() {

    }

    @Override
    public void loadDataFromServer() {

        // 请求网络   在model进行  回调返回数据
        model.loadData(mProId, mContext, new IDataToPresenter() {

            @Override
            public void onSuccess(Object mData) {
                // 设置数据给view层
                mView.setDataToAdapter((List<ProjectProgressList.DataBean>) mData);
            }

            @Override
            public void onFinish() {
                mView.refreshView();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback, int requestCode) {

            }

            @Override
            public void onCancelled(Callback.CancelledException cex) {

            }
        });
    }

}
