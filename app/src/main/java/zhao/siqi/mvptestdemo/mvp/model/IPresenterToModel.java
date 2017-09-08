package zhao.siqi.mvptestdemo.mvp.model;

import android.content.Context;

import zhao.siqi.mvptestdemo.interfaces.IDataToPresenter;

/**
 * presenter层通往model层
 * Created by Bill on 2017/9/7.
 */

public interface IPresenterToModel {

    void loadData(int id, Context context, IDataToPresenter iDataToPresenter);

}
