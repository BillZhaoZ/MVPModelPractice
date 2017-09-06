package zhao.siqi.mvptestdemo;

import android.support.multidex.MultiDexApplication;

import com.blankj.utilcode.utils.Utils;
import com.google.gson.Gson;

import org.xutils.x;

import zhao.siqi.mvptestdemo.model.UserInfoBean;
import zhao.siqi.mvptestdemo.util.ValueStorage;

/**
 * Application是为了保存全局变量的，系统默认会创建这个类。如果我们想在这个类中去保存一些其它的变量，则写一个类继承它
 * 在清单文件中指定让系统创建我们的类
 * Created by zhangzhi on 2017/3/7.
 */
public class MyApplication extends MultiDexApplication {

    static final String USER_INFO = "user_info";
    private static MyApplication instance;
    private UserInfoBean userInfoBean = null;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        // xUtils 初始化
        initXutils();

        // Android UtilCode 初始化
        Utils.init(this);

        ValueStorage.init(this);
    }

    /**
     * 存储用户信息
     *
     * @param bean
     */
    public void setUser(UserInfoBean bean) {
        userInfoBean = bean;

        Gson gson = new Gson();
        String result = gson.toJson(bean);
        ValueStorage.put(USER_INFO, result);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public UserInfoBean getUser() {

        if (userInfoBean == null) {
            String result = ValueStorage.getString(USER_INFO);
            Gson gson = new Gson();
            userInfoBean = gson.fromJson(result, UserInfoBean.class);
        }

        return userInfoBean;
    }

    /**
     * 获取Application实例
     *
     * @return
     */
    public static MyApplication getInstance() {
        return instance;
    }

    /**
     * 初始化xUtils
     */
    private void initXutils() {
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 开启debug会影响性能
    }

}
