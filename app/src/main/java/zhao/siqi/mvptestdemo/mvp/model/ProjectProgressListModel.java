package zhao.siqi.mvptestdemo.model;

import android.content.Context;

import org.xutils.common.Callback;

import java.util.List;
import java.util.Map;

import zhao.siqi.mvptestdemo.bean.ProjectProgressList;
import zhao.siqi.mvptestdemo.interfaces.IDataToPresenter;
import zhao.siqi.mvptestdemo.network.MResponse;
import zhao.siqi.mvptestdemo.network.NetRequestUtil;
import zhao.siqi.mvptestdemo.network.NetResponseListener;

/**
 * model层  进行数据的处理
 * 处理完成后  通过接口回调的方式   把数据返回到presenter层
 * <p>
 * Created by Bill on 2017/9/7.
 */
public class ProjectProgressListModel implements IPresenterToModel {

    /**
     * 加载项目进展列表
     */
    @Override
    public void loadData(int id, Context context, IDataToPresenter iDataToPresenter) {

        String url = NetRequestUtil.getInstance().getUrl(NetRequestUtil.getInstance().PROJECT_PROGRESS_LIST);
        Map map = NetRequestUtil.getInstance().getBaseMap();

        map.put("project_id", id);

        NetRequestUtil.getInstance().post(context, url, map, NetRequestUtil.REQUEST_TOKEN,
                ProjectProgressList.class, new NetResponseListener() {
                    @Override
                    public void onSuccess(MResponse response, int requestCode, String result) {

                        ProjectProgressList bean = (ProjectProgressList) response;
                        List<ProjectProgressList.DataBean> mData = bean.getData();

                        iDataToPresenter.onSuccess(mData);
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback, int requestCode) {
                        iDataToPresenter.onError(ex, isOnCallback, requestCode);
                    }

                    @Override
                    public void onCancelled(Callback.CancelledException cex) {
                        iDataToPresenter.onCancelled(cex);
                    }

                    @Override
                    public void onFinish() {
                        iDataToPresenter.onFinish();
                    }
                });
    }
}
