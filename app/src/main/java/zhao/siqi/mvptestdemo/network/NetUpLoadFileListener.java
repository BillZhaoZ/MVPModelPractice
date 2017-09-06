package zhao.siqi.mvptestdemo.network;

/**
 * 文件上传的接口回调监听
 */
public interface NetUpLoadFileListener extends NetResponseListener {

    void onLoading(long current, boolean isDownloading, int requestCode);

    void onWaiting();

    void onStarted();
}