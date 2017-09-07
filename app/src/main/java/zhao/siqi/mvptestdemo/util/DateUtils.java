package zhao.siqi.mvptestdemo.util;

import android.util.Log;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import zhao.siqi.mvptestdemo.bean.TimeInfo;

import static com.blankj.utilcode.utils.TimeUtils.isSameDay;

/**
 * 时间工具类
 * Created by David on 16/3/16.
 */
public class DateUtils {
    /**
     * get long from yyyy-MM-dd
     * 从日期格式  获取毫秒值
     */
    public static long getLongFromyyyyMMdd(String date) {
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date parse = null;
        try {
            parse = mFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (parse != null) {
            return parse.getTime() / 1000;
        } else {
            return -1;
        }
    }

    public static String getStrDate() {
        SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return dd.format(new Date());
    }

    public static String getDateBySeparation(long date) {
        date *= 1000;
        SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return dd.format(date);
    }

    public static String getStrDate(long date) {
        date *= 1000;
        SimpleDateFormat dd = new SimpleDateFormat("yyyy.MM.dd", Locale.CHINA);
        return dd.format(date);
    }

    public static String getStrDate1(long date) {
        date *= 1000;
        SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return dd.format(date);
    }

    public static String getStrDate2(long date) {
        date *= 1000;
        SimpleDateFormat dd = new SimpleDateFormat("MM-dd", Locale.CHINA);
        return dd.format(date);
    }

    public static String getStrDateTime(long date) {
        date *= 1000;
        SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        return dd.format(date);
    }

    public static String getStrDateTime2(long date) {
        date *= 1000;
        SimpleDateFormat dd = new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA);
        return dd.format(date);
    }

    public static String getStrDate_China(long date) {
        date *= 1000;
        SimpleDateFormat dd = new SimpleDateFormat("yyyy年MM月", Locale.CHINA);
        return dd.format(date);
    }

    public static String getStrDate_China1(long date) {
        date *= 1000;
        SimpleDateFormat dd = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
        return dd.format(date);
    }

    public static String getStrDate_China1_2(long date) {
        date *= 1000;
        SimpleDateFormat dd = new SimpleDateFormat("yyyy/MM/dd", Locale.CHINA);
        return dd.format(date);
    }

    public static String getStrDate_China1_3(long date) {
        date *= 1000;
        SimpleDateFormat dd = new SimpleDateFormat("yyyy/MM", Locale.CHINA);
        return dd.format(date);
    }

    public static String getTimestampStringForPull(Date var0) {
        String var1 = null;
        long var2 = var0.getTime();

        if (isSameDay(var2)) {
            var1 = "今天 HH:mm";
        } else if (isYesterday(var2)) {
            var1 = "昨天 HH:mm";
        } else {
            var1 = "M月d日 HH:mm";
        }

        return (new SimpleDateFormat(var1, Locale.CHINA)).format(var0);
    }

    private static boolean isYesterday(long var0) {
        TimeInfo var2 = getYesterdayStartAndEndTime();
        return var0 > var2.getStartTime() && var0 < var2.getEndTime();
    }

    public static TimeInfo getYesterdayStartAndEndTime() {
        Calendar var0 = Calendar.getInstance();
        var0.add(Calendar.DAY_OF_MONTH, -1);
        var0.set(Calendar.HOUR_OF_DAY, 0);
        var0.set(Calendar.MINUTE, 0);
        var0.set(Calendar.SECOND, 0);
        var0.set(Calendar.MILLISECOND, 0);
        Date var1 = var0.getTime();
        long var2 = var1.getTime();
        Calendar var4 = Calendar.getInstance();
        var4.add(Calendar.DAY_OF_MONTH, -1);
        var4.set(Calendar.HOUR_OF_DAY, 23);
        var4.set(Calendar.MINUTE, 59);
        var4.set(Calendar.SECOND, 59);
        var4.set(Calendar.MILLISECOND, 999);
        Date var5 = var4.getTime();
        long var6 = var5.getTime();
        TimeInfo var8 = new TimeInfo();
        var8.setStartTime(var2);
        var8.setEndTime(var6);
        return var8;
    }

    /**
     * 将一个时间戳转换成提示性时间字符串，如刚刚，1秒前
     *
     * @param timeStamp
     * @return
     */
    public static String convertTimeToFormat(long timeStamp) {
        long curTime = System.currentTimeMillis() / (long) 1000;
        long time = curTime - timeStamp;

        if (time < 60 && time >= 0) {
            return "刚刚";
        } else if (time >= 60 && time < 3600) {
            return time / 60 + "分钟前";
        } else if (time >= 3600 && time < 3600 * 24) {
            return time / 3600 + "小时前";
        } else if (time >= 3600 * 24 && time < 3600 * 24 * 30) {
            return time / 3600 / 24 + "天前";
        } else if (time >= 3600 * 24 * 30 && time < 3600 * 24 * 30 * 12) {
            return time / 3600 / 24 / 30 + "个月前";
        } else if (time >= 3600 * 24 * 30 * 12) {
            return time / 3600 / 24 / 30 / 12 + "年前";
        } else {
            return "刚刚";
        }
    }


    public static long getDayTimestamp() {
        return 24 * 60 * 60 * 1000;
    }

    public static boolean isCloseEnough(long time, long time2) {
        long flag = time - time2;
        if (flag < 0L) {
            flag = -flag;
        }
        return flag < 3 * 60 * 1000L;
    }


    public static String getTimestampStr() {
        return Long.toString(System.currentTimeMillis());
    }

    /**
     * 将指定日期转化为秒数
     *
     * @param expireDate
     * @return
     */
    public static int getSecondsFromDate(String expireDate) {
        if (expireDate == null || expireDate.trim().equals(""))
            return 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(expireDate);
            return (int) (date.getTime() / 1000);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 将指定日期转化为秒数
     *
     * @param expireDate
     * @return
     */
    public static int getSecondsFromDate2(String expireDate) {
        if (expireDate == null || expireDate.trim().equals(""))
            return 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = null;
        try {
            date = sdf.parse(expireDate);
            return (int) (date.getTime() / 1000);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 日期转换为周几
     *
     * @param time long 系统时间的long类型
     * @return 星期一到星期日
     */
    public static String getWeekOfDate(long time) {

        Date date = new Date(time);
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;

        if (w < 0)
            w = 0;

        return weekDays[w];
    }


    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
     */
    public static String getNextDay(String nowdate, String delay) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            String mdate = "";
            Date d = strToDate(nowdate);
            long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24 * 60 * 60;
            d.setTime(myTime * 1000);
            mdate = format.format(d);

            return mdate;
        } catch (Exception e) {
            return "";
        }
    }


    /**
     * <pre>
     * 根据指定的日期字符串获取星期几
     * </pre>
     *
     * @param strDate 指定的日期字符串(yyyy-MM-dd 或 yyyy/MM/dd)
     * @return week
     * 星期几(MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY)
     */
    public static String getWeekByDateStr(String strDate) {
        /*int year = Integer.parseInt(strDate.substring(0, 4));
        int month = Integer.parseInt(strDate.substring(5, 7));
        int day = Integer.parseInt(strDate.substring(8, 10));*/

        int month = Integer.parseInt(strDate.substring(0, 2));
        int day = Integer.parseInt(strDate.substring(3, 5));

        Calendar c = Calendar.getInstance();

        //  c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, day);

        String week = "";
        int weekIndex = c.get(Calendar.DAY_OF_WEEK);

        switch (weekIndex) {
            case 1:
                week = "星期日";
                break;
            case 2:
                week = "星期一";
                break;
            case 3:
                week = "星期二";
                break;
            case 4:
                week = "星期三";
                break;
            case 5:
                week = "星期四";
                break;
            case 6:
                week = "星期五";
                break;
            case 7:
                week = "星期六";
                break;
        }
        return week;
    }


    /**
     * 根据日期字符串   返回日期
     *
     * @param dateStr
     * @return
     */
    public static Date deteAtDateString(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static String dateAtStartOfDay(Date currentDate, int n) {

        if (currentDate == null) {
            currentDate = new Date();//当前日期
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//格式化对象

        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(currentDate);//设置当前日期
        calendar.add(Calendar.DAY_OF_MONTH, +n);//天数加一
        return sdf.format(calendar.getTime());//输出格式化的日期
    }

    public static String dateAtStartOfMonth(Date currentDate, int n) {
        if (currentDate == null) {
            currentDate = new Date();//当前日期
        }
        Date date = new Date();//当前日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//格式化对象

        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(date);//设置当前日期
        calendar.add(Calendar.MONTH, +n);//月份加一

        return sdf.format(calendar.getTime());//输出格式化的日期
    }

    /**
     * 获取当前时间  多少天以后的时间
     *
     * @param n
     * @return
     */
    public static String getMonthLaterDate(int n) {

        Date date = new Date();//当前日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");//格式化对象

        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(date);//设置当前日期
        calendar.add(Calendar.MONTH, +n);//月份加一

        return sdf.format(calendar.getTime());//输出格式化的日期
    }

    /**
     * 通过年份和月份 得到当月的日子
     *
     * @param year
     * @param month
     * @return
     */
    public static int getMonthDays(int year, int month) {
        month++;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return -1;
        }
    }

    /**
     * 返回当前月份1号位于周几
     *
     * @param year  年份
     * @param month 月份，传入系统获取的，不需要正常的
     * @return 日：1		一：2		二：3		三：4		四：5		五：6		六：7
     */
    public static int getFirstDayWeek(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        Log.d("DateView", "DateView:First:" + calendar.getFirstDayOfWeek());
        return calendar.get(Calendar.DAY_OF_WEEK);
    }


}

