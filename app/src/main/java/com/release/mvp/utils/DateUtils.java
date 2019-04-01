package com.release.mvp.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import com.release.mvp.ui.base.Constants;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

@SuppressLint("SimpleDateFormat")
public class DateUtils {


    public static final String DATE_DEFAULT_FORMAT = "yyyy-MM-dd";
    // 默认时间格式
    public static final String DATETIME_DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_DEFAULT_FORMAT = "HH:mm:ss";

    private static DateFormat dateTimeFormat = null;
    private static Calendar gregorianCalendar = null;
    private static DateFormat dateFormat = null;
    private static DateFormat timeFormat = null;

    static {
        dateFormat = new SimpleDateFormat(DATE_DEFAULT_FORMAT);
        dateTimeFormat = new SimpleDateFormat(DATETIME_DEFAULT_FORMAT);
        timeFormat = new SimpleDateFormat(TIME_DEFAULT_FORMAT);
        gregorianCalendar = new GregorianCalendar();
    }

    /**
     * 服务端给的时间，经常会以.0结尾，所以去除之
     *
     * @param datetime
     * @return
     */
    public static String RemoveLastZero(String datetime) {
        if (TextUtils.isEmpty(datetime))
            return "";

        if (datetime.length() > 19)
            return datetime.substring(0, 19);
        else
            return datetime;
    }

    /**
     * 将传入时间添加秒钟数
     *
     * @param date 时间
     * @param sec  秒数，正数为添加秒，负数是减少秒
     * @return
     */
    public static String addSec(String date, int sec) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long reminTime = sdf.parse(date).getTime() + 1000 * sec;
            return sdf.format(reminTime);
        } catch (Exception e) {
            return "";
        }
    }

    public static String parseTime(String date) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(Long.parseLong(date));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(c.getTime());

    }

    /**
     * 格式化取当前时间
     *
     * @return
     */
    public static String getThisDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
    }


    /**
     * 获取时间差 格式：5天5小时5分前
     *
     * @param time2
     * @return
     */
    public static String getDateDifference(String time2) {

        long days = 0, hours = 0, minutes = 0, seconds = 0;
        Date currentTime = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time1 = df.format(currentTime);
        try {
            Date d1 = df.parse(time1);
            Date d2 = df.parse(time2);
            long diff = d1.getTime() - d2.getTime();

            days = diff / (1000 * 60 * 60 * 24);
            hours = (diff - days * (1000 * 60 * 60 * 24))
                    / (1000 * 60 * 60);
            minutes = (diff - days * (1000 * 60 * 60 * 24) - hours
                    * (1000 * 60 * 60))
                    / (1000 * 60);
            seconds = (diff - days * (1000 * 60 * 60 * 24) - hours
                    * (1000 * 60 * 60) - minutes * (1000 * 60))
                    / 1000;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (days == 0 && hours != 0)
            return hours + "小时" + minutes + "分" + seconds + "秒前";
        else if (days == 0 && hours == 0 && minutes != 0)
            return minutes + "分" + seconds + "秒前";
        else if (days == 0 && hours == 0 && minutes == 0)
            return seconds + "秒前";
        else
            return days + "天" + hours + "小时" + minutes + "分" + seconds + "秒前";
    }

    /**
     * 时差秒
     *
     * @param time2
     * @return
     */
    public static long getDateDifferenceSeconds(String time2) {

        long days = 0, hours = 0, minutes = 0, seconds = 0;
        Date currentTime = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time1 = df.format(currentTime);
        try {
            Date d1 = df.parse(time1);
            Date d2 = df.parse(time2);
            long diff = d1.getTime() - d2.getTime();

            seconds = diff / 1000;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return seconds;
    }

    /**
     * 获取时间差 格式：6秒
     *
     * @param eTime
     * @param sTime
     * @return
     */
    public static long getDateDifferenceSecond(String eTime, String sTime) {

        long seconds = 0;

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        try {
            if (TextUtils.isEmpty(eTime) || TextUtils.isEmpty(sTime)) return 0;
            Date d1 = df.parse(eTime);
            Date d2 = df.parse(sTime);
            long diff = d1.getTime() - d2.getTime();
            seconds = diff / 1000;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return seconds;
    }

    /**
     * 获取时间差 格式：6天
     *
     * @param eTime
     * @param sTime
     * @return
     */
    public static long getDateDifference(String eTime, String sTime) {

        long days = 0;

        Date currentTime = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d1 = df.parse(eTime);
            String current = df.format(currentTime);
            Date d2 = df.parse(current);
            long diff = d1.getTime() - d2.getTime();

            days = diff / (1000 * 60 * 60 * 24);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    /**
     * 获取时间差1分
     *
     * @param eTime
     * @param sTime
     * @return
     */
    public static boolean isCloseEnough(long eTime, long sTime) {

        if (eTime == 0 || sTime == 0) return true;
        long diff = eTime - sTime;
        double seconds = diff / (1000 * 60);

        if (seconds >= 1f)
            return false;
        else
            return true;
    }

    /**
     * 将毫秒转为yyyy-MM-dd HH:mm:ss的时间
     *
     * @param time
     * @return
     */
    public static String getStringDate(long time) {
        Date date = new Date();
        date.setTime(time);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }


    /**
     * 将毫秒转为yyyy-MM-dd HH:mm的时间
     *
     * @param time
     * @return
     */
    public static String getStringDate2(long time) {
        Date date = new Date();
        date.setTime(time);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 将毫秒转为mm:ss的时间
     *
     * @param time
     * @return
     */
    public static String getCountDownDate(long time) {
        Date date = new Date();
        date.setTime(time);
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 将毫秒转为yyyy-MM-dd的时间
     *
     * @param time
     * @return
     */
    public static String getSimpleDate(long time) {
        Date date = new Date();
        date.setTime(time);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return dateString;
    }

    public static String getCurrentTaskDate() {
        Date date = new Date();
        long time = (long) SPUtil.getParam(Constants.CURRENT_TIME, System.currentTimeMillis());
        date.setTime(time);
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
        String dateString = formatter.format(date);
        return dateString;
    }

    public static long getSimpleDateMillion(String time) {
        try {
            long time2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time).getTime();
            return time2;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd HH:mm:ss
     */

    public static String getCurrentDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }


    /**
     * 判断两个时间的间隔
     *
     * @param date1
     * @param date2
     * @return date2-date1 天
     */
    public static int days(String date1, String date2) {

        if (date1 == null || date2 == null) {
            return 0;
        }
        long d1 = parseDate(date1, "yyyy-MM-dd");
        long d2 = parseDate(date2, "yyyy-MM-dd");
        int days = (int) ((d2 - d1) / (1000 * 3600 * 24));

        return Math.abs(days);
    }

    public static long parseDate(String date, String mate) {
        SimpleDateFormat format = new SimpleDateFormat(mate);
        try {
            return format.parse(date).getTime();
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 判断两个时间的间隔
     *
     * @param befdate
     * @param aftdate
     * @return date2-date1 天
     */

    public static int Months(String befdate, String aftdate) {
        if (befdate == null || aftdate == null) {
            return 0;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        try {
            bef.setTime(sdf.parse(befdate));
            aft.setTime(sdf.parse(aftdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
        int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
        return Math.abs(month + result);
    }


    //-------------------------------------------------------------------------------------------------

    /**
     * 将yyyy-MM-dd HH:mm:ss格式的时间，与当前时间相比，时间差转换为口头上的术语，如几天几小时几分几�?
     *
     * @return
     */
    public static String convert_between(String datetime) {
        try {
            long time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(datetime).getTime();
            return convert_between((int) ((time - System.currentTimeMillis()) / 1000));
        } catch (ParseException e) {
            e.printStackTrace();
            return "未知";
        }
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss格式的时间，两个时间相比，时间差转换为口头上的术语，如几天几小时几分几秒
     *
     * @return
     */
    public static String convert_between(String starttime, String endtime) {
        try {
            long ttime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(starttime).getTime();
            long etime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endtime).getTime();
            return convert_between((int) ((etime - ttime) / 1000));
        } catch (ParseException e) {
            e.printStackTrace();
            return "未知";
        }
    }

    /**
     * 将时长秒，转换为口头上的术语，如几天几小时几分几�? 1天：86400s 1时：3600s 1分：60s
     *
     * @param sec 相差的间隔，单位为秒
     * @return
     */
    public static String convert_between(long sec) {
        if (sec < 0)
            return "时间超了";
        StringBuffer buf = new StringBuffer();
        if (sec >= 86400) {
            int day = (int) (sec / 86400);
            int hour = (int) ((sec % 86400) / 3600);
            int min = (int) ((sec % 86400 % 3600) / 60);
            int second = (int) (sec % 86400 % 3600 % 60);
            buf.append(day).append("�?").append(hour).append("小时").append(min).append("�?").append(second).append("�?");
        } else if (sec > 3600) {
            int hour = (int) (sec / 3600);
            int min = (int) ((sec % 3600) / 60);
            int second = (int) (sec % 3600 % 60);
            buf.append(hour).append("小时").append(min).append("�?").append(second).append("�?");
        } else if (sec > 60) {
            int min = (int) (sec / 60);
            int second = (int) (sec % 60);
            buf.append(min).append("�?").append(second).append("�?");
        } else {
            buf.append(sec).append("�?");
        }

        return buf.toString();
    }

    /**
     * 将时长秒，转换为几分几秒，�?�用于�?�话时长之类的，�?2'30''
     *
     * @param sec
     * @return
     */
    public static String convert_between_len(long sec) {
        if (sec < 0)
            return String.valueOf(sec);

        StringBuffer buf = new StringBuffer();
        if (sec > 60) {
            int min = (int) (sec / 60);
            int second = (int) (sec % 60);
            buf.append(min).append("'").append(second).append("''");
        } else {
            buf.append(sec).append("''");
        }

        return buf.toString();
    }

    //-------------------------------------------------------------------------------------------------

    /**
     * 将EEE MMM dd HH:mm:ss zzz yyyy格式的时间，同当前时间相比，格式化为：xx分钟前，xx小时前和日期
     *
     * @param datetime
     * @return
     */
    public static String convert_before_timezone(String datetime) {
        LogUtils.v("info", datetime);
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy", Locale.ENGLISH);
        dateFormat.setLenient(false);
        Date created = null;
        try {
            created = dateFormat.parse(datetime);
        } catch (Exception e) {
            return "";
        }

        return convert_before(created.getTime());
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss格式的时间，同当前时间比对，格式化为：xx分钟前，xx小时前和日期
     *
     * @param datetime �?比对的时�?
     * @return
     */
    public static String convert_before(String datetime) {
        if (TextUtils.isEmpty(datetime)) {
            return "";
        }

        try {
            long time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(datetime).getTime();
            return convert_before(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 将对比后的时间，格式化为：xx分钟前，xx小时前和日期
     *
     * @param time �?比对的时�?
     * @return
     */
    public static String convert_before(long time) {
        if (time < 0)
            return String.valueOf(time);

        int difftime = (int) ((System.currentTimeMillis() - time) / 1000);
        if (difftime < 86400 && difftime > 0) {
            if (difftime < 3600) {
                int min = (int) (difftime / 60);
                if (min == 0)
                    return "刚刚";
                else
                    return (int) (difftime / 60) + "分钟�?";
            } else {
                return (int) (difftime / 3600) + "小时�?";
            }
        } else {
            Calendar now = Calendar.getInstance();
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(time);
            if (c.get(Calendar.YEAR) == now.get(Calendar.YEAR) && c.get(Calendar.MONTH) == now.get(Calendar.MONTH)
                    && c.get(Calendar.DATE) == now.get(Calendar.DATE)) {
                return new SimpleDateFormat("HH:mm").format(c.getTime());
            }
            if (c.get(Calendar.YEAR) == now.get(Calendar.YEAR) && c.get(Calendar.MONTH) == now.get(Calendar.MONTH)
                    && c.get(Calendar.DATE) == now.get(Calendar.DATE) - 1) {
                return new SimpleDateFormat("昨天 HH:mm").format(c.getTime());
            } else if (c.get(Calendar.YEAR) == now.get(Calendar.YEAR)
                    && c.get(Calendar.MONTH) == now.get(Calendar.MONTH)
                    && c.get(Calendar.DATE) == now.get(Calendar.DATE) - 2) {
                return new SimpleDateFormat("前天 HH:mm").format(c.getTime());
            } else if (c.get(Calendar.YEAR) == now.get(Calendar.YEAR)) {
                return new SimpleDateFormat("M月d�? HH:mm").format(c.getTime());
            } else {
                return new SimpleDateFormat("yy年M月d�?").format(c.getTime());
            }
        }
    }

    /**
     * 指定的时间，在时间条件范围内的，返回true，不在该时间范围内，返回false
     *
     * @param sDate     �?始日期，yyyy-MM-dd hh:mm:ss
     * @param eDate     结束时间，yyyy-MM-dd hh:mm:ss
     * @param checkTime �?查时间，yyyy-MM-dd hh:mm:ss
     * @return
     */
    public static boolean timeCompare(String sDate, String eDate, String checkTime) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            long sTime = sdf.parse(sDate).getTime();
            long eTime = sdf.parse(eDate).getTime();
            long sec = sdf.parse(checkTime).getTime();
            if (sec > sTime && sec < eTime)
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 当前时间，在时间条件范围内的，返回true，不在该时间范围内，返回false
     *
     * @param sDate �?始日期，hh:mm
     * @param eDate 结束时间，hh:mm
     * @return
     */
    public static boolean timeCompa(String sDate, String eDate) {
        try {
            long sec = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            long sTime = sdf.parse(df.format(sec) + " " + sDate).getTime();
            long eTime = sdf.parse(df.format(sec) + " " + eDate).getTime();
            if (sec > sTime && sec < eTime)
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断两个时间的大�?
     *
     * @param sDate �?始日期，yyyy-MM-dd hh:mm:ss
     * @param eDate 结束时间，yyyy-MM-dd hh:mm:ss
     * @return
     */
    public static boolean timeCompare(String sDate, String eDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            long sTime = sdf.parse(sDate).getTime();
            long eTime = sdf.parse(eDate).getTime();
            if (sTime > eTime)
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 格式化取当前时间
     *
     * @return
     */
    public static String getCuttentDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(System.currentTimeMillis());
    }

    public static String formatDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(new Date());
    }

    /**
     * 日期格式化yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static Date formatDate(String date, String format) {
        try {
            return new SimpleDateFormat(format).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 日期格式化yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String getDateFormat(Date date) {
        return dateFormat.format(date);
    }

    /**
     * 日期格式化yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String getDateTimeFormat(Date date) {
        return dateTimeFormat.format(date);
    }

    /**
     * 时间格式化
     *
     * @param date
     * @return HH:mm:ss
     */
    public static String getTimeFormat(Date date) {
        return timeFormat.format(date);
    }

    /**
     * 日期格式化
     *
     * @param date
     * @param formatStr 格式类型
     * @return
     */
    public static String getDateFormat(Date date, String formatStr) {
        if (!TextUtils.isEmpty(formatStr)) {
            return new SimpleDateFormat(formatStr).format(date);
        }
        return null;
    }

    /**
     * 日期格式化
     *
     * @param date
     * @return
     */
    public static Date getDateFormat(String date) {
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 时间格式化
     *
     * @param date
     * @return
     */
    public static Date getDateTimeFormat(String date) {
        try {
            return dateTimeFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前日期(yyyy-MM-dd)
     *
     * @param
     * @return
     */
    public static Date getNowDate() {
        return DateUtils.getDateFormat(dateFormat.format(new Date()));
    }

    /**
     * 获取当前月的第一天
     *
     * @return date
     */
    public static Date getFirstDayOfMonth() {
        gregorianCalendar.setTime(new Date());
        gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取当前月的最后一天
     *
     * @return
     */
    public static Date getLastDayOfMonth() {
        gregorianCalendar.setTime(new Date());
        gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
        gregorianCalendar.add(Calendar.MONTH, 1);
        gregorianCalendar.add(Calendar.DAY_OF_MONTH, -1);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取指定月的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取指定月的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
        gregorianCalendar.add(Calendar.MONTH, 1);
        gregorianCalendar.add(Calendar.DAY_OF_MONTH, -1);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取日期前一天
     *
     * @param date
     * @return
     */
    public static Date getDayBefore(Date date) {
        gregorianCalendar.setTime(date);
        int day = gregorianCalendar.get(Calendar.DATE);
        gregorianCalendar.set(Calendar.DATE, day - 1);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取日期后一天
     *
     * @param date
     * @return
     */
    public static Date getDayAfter(Date date) {
        gregorianCalendar.setTime(date);
        int day = gregorianCalendar.get(Calendar.DATE);
        gregorianCalendar.set(Calendar.DATE, day + 1);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取当前年
     *
     * @return
     */
    public static int getNowYear() {
        Calendar d = Calendar.getInstance();
        return d.get(Calendar.YEAR);
    }

    /**
     * 获取当前月份
     *
     * @return
     */
    public static int getNowMonth() {
        Calendar d = Calendar.getInstance();
        return d.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当月天数
     *
     * @return
     */
    public static int getNowMonthDay() {
        Calendar d = Calendar.getInstance();
        return d.getActualMaximum(Calendar.DATE);
    }

    /**
     * 获取时间段的每一天
     *
     * @param startDate 开始日期
     * @param endDate 结算日期
     * @return 日期列表
     */
    public static List<Date> getEveryDay(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return null;
        }
        // 格式化日期(yy-MM-dd)
        startDate = DateUtils.getDateFormat(DateUtils.getDateFormat(startDate));
        endDate = DateUtils.getDateFormat(DateUtils.getDateFormat(endDate));
        List<Date> dates = new ArrayList<Date>();
        gregorianCalendar.setTime(startDate);
        dates.add(gregorianCalendar.getTime());
        while (gregorianCalendar.getTime().compareTo(endDate) < 0) {
            // 加1天
            gregorianCalendar.add(Calendar.DAY_OF_MONTH, 1);
            dates.add(gregorianCalendar.getTime());
        }
        return dates;
    }

    /**
     * 获取提前多少个月
     *
     * @param monty
     * @return
     */
    public static Date getFirstMonth(int monty) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -monty);
        return c.getTime();
    }

    //根据日期获得周几
    public static String getWeek(String pTime) {
        String Week = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(pTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            Week += "日";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 2) {
            Week += "一";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 3) {
            Week += "二";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 4) {
            Week += "三";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 5) {
            Week += "四";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 6) {
            Week += "五";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 7) {
            Week += "六";
        }
        return "星期"+Week;
    }
}
