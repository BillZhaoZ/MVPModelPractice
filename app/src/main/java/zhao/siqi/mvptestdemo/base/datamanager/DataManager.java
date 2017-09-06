package zhao.siqi.mvptestdemo.base.datamanager;

import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import zhao.siqi.mvptestdemo.MyApplication;
import zhao.siqi.mvptestdemo.base.model.BaseBean;
import zhao.siqi.mvptestdemo.util.JsonUtil;
import zhao.siqi.mvptestdemo.util.SPHelper;

/**
 * Created by zjw on 17-5-9.
 */

public class DataManager implements DataInterface {
    private Gson mGson;
    private SPHelper mSPHelper = SPHelper.getInstance();
    private static DataManager dataManager;

    private DataManager() {
        mGson = new Gson();
    }

    public static DataManager getInstance() {
        if (dataManager == null) {
            dataManager = new DataManager();
        }
        return dataManager;
    }

    @Override
    public void saveDataPF(String key, String value) {
        mSPHelper.setString(key, value);
    }

    @Override
    public void saveDataSQL(String key, String value) {
    }

    @Override
    public String getDataPF(String key) {
        return mSPHelper.getString(key, "");
    }

    @Override
    public void setIntDataPF(String key, int value) {
        mSPHelper.setInt(key, value);
    }

    @Override
    public int getIntDataPF(String key) {
        return mSPHelper.getInt(key, 0);
    }

    @Override
    public String getDataSQL(String key) {
        return null;
    }

    @Override
    public void setListBeanData(String key, ArrayList<? extends BaseBean> list) {
        mSPHelper.setString(key, mGson.toJson(list));
    }

    @Override
    public List<? extends BaseBean> getListBeanData(String key, Class<? extends BaseBean> cls) {
        String str = getDataPF(key);
        if (TextUtils.isEmpty(str)) return null;
        return JsonUtil.stringToList(str, cls);
    }

    @Override
    public Observable<List<? extends BaseBean>> getJsonData(String url, Class<? extends BaseBean> cls) {
        return Observable.create(subscriber -> {
            try {
                List<? extends BaseBean> mlist = JsonUtil.stringToList(url, cls, MyApplication.getInstance());
                subscriber.onNext(mlist);
                subscriber.onCompleted();
            } catch (Exception e) {
                e.printStackTrace();
                subscriber.onError(e);
            }
        });
    }
}
