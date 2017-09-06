package zhao.siqi.mvptestdemo.network;

import java.io.File;

/**
 * 文件下载的接口回调监听器
 */
public interface NetDownLoadFileListener extends NetResponseListener {

    void onSuccess(File response, int requestCode);

    void onLoading(long current, boolean isDownloading, int requestCode);

    void onWaiting();

    void onStarted();
}
