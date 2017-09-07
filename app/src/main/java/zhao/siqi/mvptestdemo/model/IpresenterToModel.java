package zhao.siqi.mvptestdemo.model;

import android.content.Context;

import zhao.siqi.mvptestdemo.interfaces.IDataToPresenter;

/**
 * presenter层通往model层
 * Created by Bill on 2017/9/7.
 */

public interface IpresenterToModel {
    void loadData(int id, Context context, IDataToPresenter iDataToPresenter);
}
