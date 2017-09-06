package zhao.siqi.mvptestdemo.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import org.xutils.common.util.MD5;
import org.xutils.image.ImageOptions;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类
 * Created by zhangzhi on 2017/3/15.
 */
public class Tools {

    private static String uninput = "未填";

    /**
     * activity跳转
     *
     * @param context
     * @param cls
     */
    public static void startActivity(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    /**
     * 加密标识
     *
     * @param map
     * @return
     */
    public static String getSign(Map map) {
        List<String> list = new ArrayList<>();
        String input = "";

        for (Object key : map.keySet()) {
            list.add(key + "=" + map.get(key));
        }

        Collections.sort(list);

        for (String str : list) {
            input = input + str + "&";
        }

        input = input + "key=medbanks2017";
        return MD5.md5(input);
    }


    public static void toSetValue(TextView textView, int value) {
        textView.setText(value + "");
    }

    public static void toSetValue(TextView textView, String value) {
        textView.setText(value);
    }


    /**
     * 获取文件的MD5串
     *
     * @param file
     * @return
     */
    public static String getFileMD5(File file) {
        if (!file.isFile()) {
            return null;
        }

        MessageDigest digest = null;
        FileInputStream in = null;
        byte[] buffer = new byte[1024];
        int len;

        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return toHex(digest.digest());
    }

    /**
     * 将字节数组转换为16进制字符串
     *
     * @param buffer
     * @return
     */

    public static String toHex(byte[] buffer) {

        StringBuffer sb = new StringBuffer(buffer.length * 2);
        for (int i = 0; i < buffer.length; i++) {
            sb.append(Character.forDigit((buffer[i] & 240) >> 4, 16));
            sb.append(Character.forDigit(buffer[i] & 15, 16));
        }

        return sb.toString();
    }


    /**
     * 获取设备唯一标识
     */
    public static String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String device_token = tm.getDeviceId();
        return device_token;
    }

    /**
     * 得到版本号
     *
     * @return 版本号
     */
    public static String getVersion(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo info = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*********
     * 屏蔽表情
     ****/
    private static boolean isEmojiCharacter(char codePoint) {
        return !((codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD) || ((codePoint >= 0x20) && codePoint <= 0xD7FF)) || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    /*********
     * 计算 中文长度
     ****/
    public static int calculateLength(String etstring) {
        char[] ch = etstring.toCharArray();

        int varlength = 0;
        for (char aCh : ch) {
            // changed by zyf 0825 , bug 6918，加入中文标点范围
            if ((aCh >= 0x2E80 && aCh <= 0xFE4F) || (aCh >= 0xA13F && aCh <= 0xAA40) || aCh >= 0x80) { // 中文字符范围0x4e00 0x9fbb
                varlength = varlength + 2;
            } else {
                varlength++;
            }
        }

        Log.d("TextChanged", "varlength = " + varlength);

        // 这里也可以使用getBytes,更准确嘛
        //varlength = etstring.getBytes(CharSet.forName("GBK")).lenght;// 编码根据自己的需求，注意u8中文占3个字节...
        return varlength;
    }

    /**
     * 强制软键盘隐藏
     */
    public static void forceHidenSoftKeyboad(Activity activity) {
        View view2 = activity.getWindow().peekDecorView();
        InputMethodManager inputmanger = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);

        if (view2 != null) {
            inputmanger.hideSoftInputFromWindow(view2.getWindowToken(), 0);
        }
    }

    /**
     * 打开设置页面
     *
     * @param activity
     */
    public static void openSettingActivity(final Activity activity) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
        intent.setData(uri);
        activity.startActivityForResult(intent, 100);
    }

    /**
     * 检测应用是否已经启动
     *
     * @param context
     * @return
     */
    public static boolean checkAppIsOpen(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);

        for (ActivityManager.RunningTaskInfo info : list) {
            if (info.topActivity.getPackageName().equals("cn.medbanks.mymedbanks")
                    && info.baseActivity.getPackageName().equals("cn.medbanks.mymedbanks")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取屏幕宽度值
     *
     * @return
     */
    public static int getScreenWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        int screenHeigh = dm.heightPixels;
        return screenWidth;
    }


    /**
     * 设置返回头像参数
     *
     * @return
     */
    public static ImageOptions getOptions() {
        return new ImageOptions.Builder()
                .setRadius(20)
                .setUseMemCache(true)
                .build();
    }

    /**
     * 发送邮件
     *
     * @param context
     * @param emails
     * @param title
     * @param body
     */
    public static void sendEmail(Context context, String[] emails, String title, String body) {

        String[] email = emails; // 需要注意，email必须以数组形式传入
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822"); // 设置邮件格式
        intent.putExtra(Intent.EXTRA_EMAIL, email); // 接收人
        intent.putExtra(Intent.EXTRA_SUBJECT, title);//这是标题
        intent.putExtra(Intent.EXTRA_TEXT, body);//这是内容

        context.startActivity(Intent.createChooser(intent, "请选择邮件类应用"));
    }

    /**
     * 判断EditText输入的数字、中文还是字母方法
     *
     * @param txt
     * @return
     */
    public static int getEditType(String txt) {

        Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(txt);

        if (m.matches()) {
            return 0; // 数字
        }

        String reg2 = "[a-zA-Z]+";//表示+表示一个或多个中文，
        boolean matches2 = txt.matches(reg2);

        if (matches2) {
            return 1; // 字母
        }

        String reg = "[\\u4e00-\\u9fa5]+";//表示+表示一个或多个中文，
        boolean matches = txt.matches(reg);

        if (matches) {
            return 2; // 汉字
        }

        return 0;
    }


    /**
     * 获取application下面的metadata的数据
     *
     * @param context
     * @param name    metadata的name
     * @return
     */
    public static String getMetaData(Context context, String name) {
        if (TextUtils.isEmpty(name)) {
            return "";
        }
        ApplicationInfo appInfo = null;
        try {
            appInfo = context.getPackageManager().getApplicationInfo("cn.medbanks.mymedbanks",
                    PackageManager.GET_META_DATA);
            String msg = appInfo.metaData.getString(name);
            return msg;
        } catch (ClassCastException e) {
            return appInfo.metaData.getInt(name) + "";
        } catch (Exception e) {
            return "";
        }
    }


    public static long intToMills(int value) {
        return ((long) value) * 1000;
    }

    /**
     * 小数转百分数
     *
     * @param ftePerName
     * @return
     */
    public static String decimal2Percent(String ftePerName) {
        double names = Double.parseDouble(ftePerName);
        DecimalFormat df = new DecimalFormat("0.00%");
        return df.format(names);
    }

    /**
     * 百分数转小数
     *
     * @param fteper
     */
    public static Number percent2Decimal(String fteper) {
        NumberFormat nf = NumberFormat.getPercentInstance();

        try {
            return nf.parse(fteper);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0.0;
    }

    /**
     * 把30%表达转化为小数形式 0.3
     *
     * @param ss
     * @return
     */
    public static float perTofloat(String ss) {
        if (ss.contains("%")) {
            ss = ss.replaceAll("%", "");
            Float f = Float.valueOf(ss);
            return f / 100;
        }
        return 0;
    }


    /**
     * 获取屏幕密度
     * <p>
     * 屏幕密度比例值为：（0.75 / 1.0 / 1.5 /2 / 3）参考值为：mdpi 160
     *
     * @return
     */
    public static float getScreenDensity(Activity activity) {
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        float density = metric.density;
        return density;
    }

    /**
     * 获取屏幕高度值
     *
     * @return
     */
    public static int getScreenHeigh(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        int screenHeigh = dm.heightPixels;
        return screenHeigh;
    }

}
