package zhao.siqi.mvptestdemo.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：yuanfang on 5/3/17 15:14
 * 邮箱：jingchao@newborntown.com
 */

public class JsonUtil {

    private static Gson mGson = new Gson();

    /**
     * 将json字符串转化成实体对象
     *
     * @param json
     * @param classOfT
     * @return
     */
    public static <T> Object stringToObject(String json, Class<T> classOfT) {
        return mGson.fromJson(json, classOfT);
    }

    /**
     * 将对象准换为json字符串 或者 把list 转化成json
     *
     * @param object
     * @param <T>
     * @return
     */
    public static <T> String objectToString(T object) {
        return mGson.toJson(object);
    }

    /**
     * 把json 字符串转化成list
     *
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> stringToList(String json, Class<T> cls) {
        List<T> list = new ArrayList<>();
        if (TextUtils.isEmpty(json)) return list;
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        DebugLogger.d("stringToList-------->", json);
        //元芳  -> GSON的fromJson中有异常 此方法用来在bugly的自定义日志中捕获
        try {
            for (final JsonElement elem : array) {
                list.add(mGson.fromJson(elem, cls));
            }
        } catch (Throwable thr) {
           // BuglyLog.e("Json", "JsonUtil-stringToList()-json-->" + json);
           // CrashReport.postCatchedException(thr); // bugly会将这个throwable上报
        }
        return list;
    }

    /**
     * @param fileName
     * @param cls
     * @param <T>
     * @return
     */

    public static <T> List<T> stringToList(String fileName, Class<T> cls, Context context) {
        List<T> list = new ArrayList<T>();
        BufferedReader bf = null;
        InputStreamReader inputStreamReader = null;
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取

            inputStreamReader = new InputStreamReader(
                    assetManager.open(fileName));
            bf = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bf != null) {
                try {
                    bf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        JsonArray array = new JsonParser().parse(stringBuilder.toString()).getAsJsonArray();
        for (final JsonElement elem : array) {
            list.add(mGson.fromJson(elem, cls));
        }
        return list;
    }
}
