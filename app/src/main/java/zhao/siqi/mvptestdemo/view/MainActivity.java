package zhao.siqi.mvptestdemo.view;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import zhao.siqi.mvptestdemo.R;
import zhao.siqi.mvptestdemo.base.BaseActivity;
import zhao.siqi.mvptestdemo.bean.ProjectProgressList;
import zhao.siqi.mvptestdemo.mvp.MainContract;
import zhao.siqi.mvptestdemo.presenter.MainPresenter;
import zhao.siqi.mvptestdemo.view.adapter.ProjectProgressListAdapter;

/**
 * View不直接与Model交互，而是通过与Presenter交互来与Model间接交互。Presenter与View的交互是通过接口来进行的。
 * 通常View与Presenter是一对一的，但复杂的View可能绑定多个Presenter来处理逻辑。
 * <p>
 * 从MVC到MVP的一个转变，就是减少了Activity的职责，减轻了它的负担，简化了Activity中的代码和一些操作，
 * 将逻辑代码提取到了Presenter中进行处理，降低了其耦合度。
 */
public class MainActivity extends BaseActivity implements MainContract.View {

    /**
     * butterknife注解
     */
    @BindView(R.id.listview)
    ListView mListView;
    @BindView(R.id.no_meesage)
    TextView noMessage;

    private ProjectProgressListAdapter mAdapter;
    List<ProjectProgressList.DataBean> mDataBeanList = new ArrayList<>();
    MainContract.Presenter presenter;

    @Override
    protected void initView() {
        mAdapter = new ProjectProgressListAdapter(context);
        mListView.setAdapter(mAdapter);
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected int getContentViewID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        super.initData();

        int projectID = 181;

        // 建立View和presenter的关系
        new MainPresenter(this, projectID, context);

        // 请求网络数据
        presenter.loadDataFromServer();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    /**
     * 数据设置给adapter
     *
     * @param mData
     */
    @Override
    public void setDataToView(List<ProjectProgressList.DataBean> mData) {
        mAdapter.setData(mData);

        mDataBeanList = mData;
    }

    /**
     * 网络请求成功后  刷新界面
     */
    @Override
    public void refreshView() {

        if (mDataBeanList.isEmpty() || mDataBeanList.size() == 0) {
            mListView.setVisibility(View.GONE);
            noMessage.setVisibility(View.VISIBLE);
        } else {
            mListView.setVisibility(View.VISIBLE);
            noMessage.setVisibility(View.GONE);
        }
    }
}
