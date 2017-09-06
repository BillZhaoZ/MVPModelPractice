package zhao.siqi.mvptestdemo.network;

import android.content.Context;
import android.text.TextUtils;

import com.blankj.utilcode.utils.NetworkUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.ex.HttpException;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import zhao.siqi.mvptestdemo.R;
import zhao.siqi.mvptestdemo.MyApplication;
import zhao.siqi.mvptestdemo.util.AppLog;
import zhao.siqi.mvptestdemo.util.Const;
import zhao.siqi.mvptestdemo.util.ToastU;
import zhao.siqi.mvptestdemo.util.Tools;


/**
 * 网络请求
 */
public class NetRequestUtil {

    private volatile static NetRequestUtil instance;

    /**
     * Double Check 单例模式
     *
     * @return
     */
    public static NetRequestUtil getInstance() {
        if (instance == null) {
            synchronized (NetRequestUtil.class) {
                if (instance == null) {
                    instance = new NetRequestUtil();
                }
            }
        }
        return instance;
    }

    /**
     * /appLogin','/version','/applogout','/weekReportDel','/idcode','/islogin','/losepwd'
     * 不需要传token
     */
    public static final int REQUEST_TOKEN = 0;// 默认需要token
    public static final int REQUEST_NO_TOKEN = 1; // 在登录等几个特殊接口不需要token

    public static final int REQUEST_METHOD_GET = 0;// get请求
    public static final int REQUEST_METHOD_POST = 1; // post请求

    /**
     * 基地址
     */
    private String OnlineIp = "http://114.215.104.95/";
    private String TestUrl = "http://a.crcin.medbanks.cn/"; // 测试环境
    private String OnLineUrl = "https://crcin.medbanks.cn/";  // 正式环境
    private String AboutUrl = "http://test.crcin.medbanks.cn/"; // 类生产域名
    private String BaseUrl = AboutUrl;

    /**
     * 接口地址
     */

    public String PROJECT_PROGRESS_LIST = "projectDebriefing/projectDebriefing_list"; // 项目进展列表

    /**
     * 取得完整地址
     *
     * @param apiUrl
     * @return
     */
    public String getUrl(String apiUrl) {
        return BaseUrl + apiUrl;
    }

    /**
     * 取得公共参数
     *
     * @return
     */
    public Map<String, Object> getBaseMap() {
        Map<String, Object> map = new HashMap<>();

        // 添加公参
        //  map.put("device_token", Tools.getDeviceId(MyApplication.getInstance())); //  设备唯一标识
        //  map.put("device_uuid", Tools.getDeviceId(MyApplication.getInstance()));
        map.put("appid", "android");
        map.put("version", Tools.getVersion(MyApplication.getInstance()));

       /* if (EmptyUtils.isNotEmpty(MyApplication.getInstance().getUser())
                && EmptyUtils.isNotEmpty(MyApplication.getInstance().getUser().getData().getPhone())) {
            map.put("username", MyApplication.getInstance().getUser().getData().getPhone());
        }*/

        map.put("username", "18910378037");

        return map;
    }

    /**
     * 异步get请求
     *
     * @param url
     * @param maps
     * @param requestCode
     * @param clazz
     * @param listener
     * @return
     */
    public Callback.Cancelable get(String url, Map<String, String> maps, final int requestCode,
                                   final Class<? extends MResponse> clazz, final NetResponseListener listener) {

        if (!NetworkUtils.isConnected()) {
            ToastU.showShortToast(R.string.no_network);
        }

        maps.put("sign", Tools.getSign(maps)); // MD5加密

        RequestParams params = new RequestParams(url);
        if (maps != null && !maps.isEmpty()) {
            for (Map.Entry<String, String> entry : maps.entrySet()) {
                params.addQueryStringParameter(entry.getKey(), entry.getValue());
            }
        }

        Callback.Cancelable cancelable = x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                MResponse mResponse = GsonUtil.processJS(result, clazz); //按正常响应解析
                listener.onSuccess(mResponse, requestCode, result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                listener.onError(ex, isOnCallback, requestCode);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                listener.onCancelled(cex);
            }

            @Override
            public void onFinished() {
                listener.onFinish();
            }
        });

        return cancelable;
    }

    /**
     * 带缓存的异步get请求
     *
     * @param url
     * @param maps
     * @param requestCode
     * @param clazz
     * @param listener
     * @return
     */
    public Callback.Cancelable getCache(String url, Map<String, String> maps, final int requestCode,
                                        final Class<? extends MResponse> clazz, final NetResponseListener listener) {

        if (!NetworkUtils.isConnected()) {
            ToastU.showShortToast(R.string.no_network);
        }

        maps.put("sign", Tools.getSign(maps)); // MD5加密

        RequestParams params = new RequestParams(url);
        if (maps != null && !maps.isEmpty()) {
            for (Map.Entry<String, String> entry : maps.entrySet()) {
                params.addQueryStringParameter(entry.getKey(), entry.getValue());
            }
        }

        // 默认缓存存活时间, 单位:毫秒（如果服务器没有返回有效的max-age或Expires则参考）
        params.setCacheMaxAge(1000 * 60);//设置缓存当这个缓存事件过了的时候, 这时候就会不走这个onCache方法, 直接发起网络请求,

        Callback.Cancelable cancelable = x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                MResponse mResponse = GsonUtil.processJS(result, clazz);//按正常响应解析
                listener.onSuccess(mResponse, requestCode, result);
                return true;//这里返回一个true, 就是走了cache就不再发起网络请求了, 返回一个false, 就是不信任缓存数据, 再次发起网络请求
            }

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    //如果走了cache方法返回了true, 将不再发起网络请求, 这里拿到的result就是null,
                    MResponse mResponse = GsonUtil.processJS(result, clazz);//按正常响应解析
                    listener.onSuccess(mResponse, requestCode, result);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                listener.onError(ex, isOnCallback, requestCode);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                listener.onCancelled(cex);
            }

            @Override
            public void onFinished() {
                listener.onFinish();
            }
        });

        return cancelable;
    }

    /**
     * 异步post请求，去除请求标志，默认为0
     *
     * @param url
     * @param maps
     * @param clazz
     * @param listener
     * @return
     */
    public Callback.Cancelable post(Context context, String url, Map<String, Object> maps,
                                    final Class<? extends MResponse> clazz, final NetResponseListener listener) {

        return post(context, url, maps, REQUEST_TOKEN, clazz, listener);
    }

    /**
     * 异步post请求
     *
     * @param context
     * @param url
     * @param maps
     * @param requestCode
     * @param clazz
     * @param listener
     * @return
     */
    public Callback.Cancelable post(final Context context, String url, Map<String, Object> maps,
                                    final int requestCode, final Class<? extends MResponse> clazz,
                                    final NetResponseListener listener) {

        if (!NetworkUtils.isConnected()) {
            ToastU.showShortToast(R.string.no_network);
        }

        if (requestCode == REQUEST_TOKEN) {
            maps.put("token", "d59fe8550f1cb3cfbe6027fda1751f4e"); // 添加token
        }

        maps.put("sign", Tools.getSign(maps)); // MD5加密

        RequestParams params = new RequestParams(url);
        params.setMethod(HttpMethod.POST);

        if (!maps.isEmpty()) {
            for (Map.Entry<String, Object> entry : maps.entrySet()) {
                params.addParameter(entry.getKey(), entry.getValue());
            }
        }

        AppLog.i(Const.HTTP_LOG_KEY, "=======http-request-url::::::" + url);
        AppLog.i(Const.HTTP_LOG_KEY, "=======http-request-parameters::::::" + params.toString());

        final Callback.Cancelable post = x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(final String result) {

                try {
                    JSONObject jsonObject = new JSONObject(result);

                    if (jsonObject.getInt("code") == Const.LOGIN_OUT_TIME) { // 判断为token过期则重新去登录
                        if (!TextUtils.isEmpty(jsonObject.getString("message"))) {
                            ToastU.showLongToast(jsonObject.getString("message"));
                        }
                        // Tools.logout(context);
                    } else {
                        final MResponse mResponse = GsonUtil.processJS(result, clazz);//按正常响应解析
                        listener.onSuccess(mResponse, requestCode, result);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                AppLog.i(Const.HTTP_LOG_KEY, "=======---onSuccess---======:" + result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                if (ex instanceof HttpException) { // 网络错误
                    HttpException httpEx = (HttpException) ex;
                    int responseCode = httpEx.getCode();
                    String responseMsg = httpEx.getMessage();
                    String errorResult = httpEx.getResult();

                    AppLog.i(Const.HTTP_LOG_KEY, "=======---onError---网络错误======:"
                            + "返回码：" + responseCode
                            + "；---错误信息：" + responseMsg
                            + "；---错误结果：" + errorResult);

                } else { // 其他错误

                    listener.onError(ex, isOnCallback, requestCode);
                    AppLog.i(Const.HTTP_LOG_KEY, "=======---onError---其他错误======:" + ex.toString());
                }

            }

            @Override
            public void onCancelled(CancelledException cex) {
                listener.onCancelled(cex);
                AppLog.i(Const.HTTP_LOG_KEY, "=======---onCancelled---======:" + cex.toString());
            }

            // 不管成功或者失败最后都会回调该接口
            @Override
            public void onFinished() {
                listener.onFinish();
                AppLog.i(Const.HTTP_LOG_KEY, "=======---onFinished---======:" + clazz);
            }
        });

        return post;
    }

    /**
     * 普通异步网络请求  get或者post
     *
     * @param context
     * @param url
     * @param maps
     * @param requestCode
     * @param requestMethod
     * @param clazz
     * @param listener
     * @return
     */
    public Callback.Cancelable httpRequest(final Context context, String url,
                                           Map<String, Object> maps,
                                           final int requestCode, int requestMethod,
                                           final Class<? extends MResponse> clazz,
                                           final NetResponseListener listener) {

        HttpMethod method = null; // 请求方式

        if (requestMethod == REQUEST_METHOD_GET) {
            method = HttpMethod.GET;
        } else {
            method = HttpMethod.POST;
        }

        // token 添加
        if (requestCode == REQUEST_TOKEN) {
            maps.put("token", MyApplication.getInstance().getUser().getData().getToken()); // 添加token
        }

        maps.put("sign", Tools.getSign(maps)); // MD5加密

        RequestParams params = new RequestParams(url);

        if (!maps.isEmpty()) {
            for (Map.Entry<String, Object> entry : maps.entrySet()) {
                params.addBodyParameter(entry.getKey(), entry.getValue().toString());
            }
        }

        Callback.Cancelable request = x.http().request(method, params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {

                try {
                    JSONObject jsonObject = new JSONObject(result);

                    if (jsonObject.getInt("code") == Const.LOGIN_OUT_TIME) { // 判断为token过期则重新去登录
                        ToastU.showLongToast(jsonObject.getString("message"));
                        //  Tools.logout(context);
                    } else {
                        final MResponse mResponse = GsonUtil.processJS(result, clazz);//按正常响应解析
                        listener.onSuccess(mResponse, requestCode, result);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                if (ex instanceof HttpException) { // 网络错误
                    HttpException httpEx = (HttpException) ex;
                    int responseCode = httpEx.getCode();
                    String responseMsg = httpEx.getMessage();
                    String errorResult = httpEx.getResult();

                    AppLog.i(Const.HTTP_LOG_KEY, "=======---onError---网络错误======:"
                            + "返回码：" + responseCode
                            + "；---错误信息：" + responseMsg
                            + "；---错误结果：" + errorResult);

                } else { // 其他错误

                    listener.onError(ex, isOnCallback, requestCode);
                    AppLog.i(Const.HTTP_LOG_KEY, "=======---onError---其他错误======:" + ex.toString());
                }
            }

            @Override
            public void onCancelled(CancelledException cex) {
                listener.onCancelled(cex);
                AppLog.i(Const.HTTP_LOG_KEY, "=======---onCancelled---======:" + cex.toString());
            }

            @Override
            public void onFinished() {
                listener.onFinish();
                AppLog.i(Const.HTTP_LOG_KEY, "=======---onFinished---======:" + clazz);
            }
        });

        return request;
    }

    /**
     * 文件上传
     *
     * @param url
     * @param maps
     * @param file
     * @param requestCode
     * @param clazz
     * @param listener
     * @return
     */
    public Callback.Cancelable upLoadFile(String url, Map<String, String> maps, Map<String, File> file,
                                          final int requestCode, final Class<? extends MResponse> clazz,
                                          final NetUpLoadFileListener listener) {

        if (!NetworkUtils.isConnected()) {
            ToastU.showShortToast(R.string.no_network);
        }

        // token 添加
        if (requestCode == REQUEST_TOKEN) {
            maps.put("token", MyApplication.getInstance().getUser().getData().getToken()); // 添加token
        }

        maps.put("sign", Tools.getSign(maps)); // MD5加密

        RequestParams params = new RequestParams(url);

        params.setMethod(HttpMethod.POST);

        if (!maps.isEmpty()) {
            for (Map.Entry<String, String> entry : maps.entrySet()) {
                params.addBodyParameter(entry.getKey(), entry.getValue());
            }
        }

        if (file != null && !maps.isEmpty()) {
            for (Map.Entry<String, File> entry : file.entrySet()) {
                params.addBodyParameter(entry.getKey(), entry.getValue().getAbsoluteFile());
            }
        }

        AppLog.i(Const.HTTP_LOG_KEY, "=======http-request-url::::::" + url);
        AppLog.i(Const.HTTP_LOG_KEY, "=======http-request-parameters::::::" + params.toString());

        // 有上传文件时使用multipart表单, 否则上传原始文件流.
        params.setMultipart(true);//这个是标示上传的文件内容的,

        Callback.Cancelable upLoad = x.http().post(params, new Callback.ProgressCallback<String>() {
            @Override
            public void onSuccess(String result) {
                JSONObject jsonObject = null;

                try {
                    jsonObject = new JSONObject(result);

                    ToastU.showLongToast(jsonObject.getString("message"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                MResponse mResponse = GsonUtil.processJS(result, clazz);//按正常响应解析
                listener.onSuccess(mResponse, requestCode, result);

                AppLog.i(Const.HTTP_LOG_KEY, "=======---onSuccess---======:" + result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                listener.onError(ex, isOnCallback, requestCode);
                AppLog.i(Const.HTTP_LOG_KEY, "=======---onError---======:" + ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                listener.onCancelled(cex);
                AppLog.i(Const.HTTP_LOG_KEY, "=======---onCancelled---======:" + cex.toString());
            }

            @Override
            public void onFinished() {
                listener.onFinish();
                AppLog.i(Const.HTTP_LOG_KEY, "=======---onFinished---======:");
            }

            @Override
            public void onWaiting() {
                listener.onWaiting();
                AppLog.i(Const.HTTP_LOG_KEY, "=======---onWaiting---======:");
            }

            @Override
            public void onStarted() {
                listener.onStarted();
                AppLog.i(Const.HTTP_LOG_KEY, "=======---onStarted---======:");
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                listener.onLoading(current, isDownloading, requestCode);
                AppLog.i(Const.HTTP_LOG_KEY, "=======---onLoading---======:"
                        + "total:" + total + "---current:"
                        + current + "---isDownloading:"
                        + isDownloading);
            }
        });

        return upLoad;
    }

    /**
     * 文件下载
     *
     * @param url
     * @param filepath
     * @param requestCode
     * @param listener
     */
    public void downLoadFile(String url, String filepath, final int requestCode, final NetDownLoadFileListener listener) {

        if (!NetworkUtils.isConnected()) {
            ToastU.showShortToast(R.string.no_network);
        }

        RequestParams params = new RequestParams(url);
        params.setAutoRename(true);// 断点续传, 也就是说支持中断之后再继续下载,
        params.setSaveFilePath(filepath);//设置文件保存的路径

        x.http().post(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onSuccess(final File result) {
                listener.onSuccess(result, requestCode);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                listener.onError(ex, isOnCallback, requestCode);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                listener.onCancelled(cex);
            }

            @Override
            public void onFinished() {
                listener.onFinish();
            }

            @Override
            public void onWaiting() {
                listener.onWaiting();
            }

            @Override
            public void onStarted() {
                listener.onStarted();
            }

            @Override
            public void onLoading(final long total, final long current, final boolean isDownloading) {
                listener.onLoading(current, isDownloading, requestCode);
            }
        });

    }
}
