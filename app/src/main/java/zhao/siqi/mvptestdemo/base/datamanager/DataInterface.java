package zhao.siqi.mvptestdemo.base.datamanager;

import android.database.Observable;

import java.util.ArrayList;
import java.util.List;

import zhao.siqi.mvptestdemo.base.model.BaseBean;

/**
 * Created by zjw on 17-5-9.
 */

public interface DataInterface {
    void saveDataPF(String key, String value);

    void setIntDataPF(String key, int value);

    void saveDataSQL(String key, String value);

    String getDataPF(String key);

    int getIntDataPF(String key);

    String getDataSQL(String key);

    void setListBeanData(String key, ArrayList<? extends BaseBean> list);

    List<? extends BaseBean> getListBeanData(String key, Class<? extends BaseBean> cls);

    rx.Observable<List<? extends BaseBean>> getJsonData(String url, Class<? extends BaseBean> cls);
}
