package zhao.siqi.mvptestdemo.base;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by xiaochongzi on 17-6-2.
 */

public class BasePresenterImpl<V extends BaseContract.BaseView> implements BaseContract.BasePresenter<V> {

    protected V mView;
    protected Context context;
    protected String mLastPageFragment;

    @Override
    public void onViewAttached(@NonNull V view) {
        mView = view;
        context = view.getViewContext();
    }

    @Override
    public void onStart(boolean firstStart) {

    }

    @Override
    public void onStop() {
    }

    @Override
    public void onViewDetached() {
        mView = null;
    }

    @Override
    public void onPresenterDestroyed() {

    }

    @Override
    public void onPause() {

    }
}
