package zhao.siqi.mvptestdemo.interfaces;

import org.xutils.common.Callback;

/**
 * model层通往presenter的接口回调
 * Created by Bill on 2017/9/7.
 */
public interface IDataToPresenter {

    void onSuccess(Object mData);

    void onFinish();

    void onError(Throwable ex, boolean isOnCallback, int requestCode);

    void onCancelled(Callback.CancelledException cex);
}
