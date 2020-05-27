package com.application.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * @Discription 日期工具
 */
public class DateUtil {

    /** 获取当前年月：yyyyMM */
    public static final String monthFormatStr1 = "yyyy-MM";
    /** 获取当前日期：yyyyMMdd */
    public static final String dateFormatStr1 = "yyyy-MM-dd";
    /** 获取当前日期时间：yyyyMMddHHmmss */
    public static final String dateFormatStr3 = "yyyyMMdd";

    public static final String dateFormatStr4 = "yyyy-MM-dd HH:mm:ss";

    public static final String dateFormatStr5 = "yyyyMMdd-HH";

    public static final String dateFormatStr6 = "yyyyMMddHHmmssSSS";

    /** 获取当前年月：yyyyMM */
    public static final DateTimeFormatter monthFormat1 = DateTimeFormat.forPattern(monthFormatStr1);
    /** 获取当前日期：yyyyMMdd */
    public static final DateTimeFormatter dateFormat1 = DateTimeFormat.forPattern(dateFormatStr1);
    /** 获取当前日期时间：yyyyMMddHHmmss */
    public static final DateTimeFormatter dateFormat3 = DateTimeFormat.forPattern(dateFormatStr3);

    /** 获取当前日期时间： */
    public static final DateTimeFormatter dateFormat4 = DateTimeFormat.forPattern(dateFormatStr4);

    public static final DateTimeFormatter dateFormat5 = DateTimeFormat.forPattern(dateFormatStr5);

    public static final DateTimeFormatter dateFormat6 = DateTimeFormat.forPattern(dateFormatStr6);

    /**
     * 取得当前日期时间
	 * @param pattern 日期格式字符�?
	 * @return String
     */
    public static String getCurrentDateTime(DateTimeFormatter pattern) {
        return new DateTime().toString(pattern);
    }

    /**
     * 取得当前日期时间
	 * @return Date 日期
     */
    public static Date getCurrentDateTime() {
        return new DateTime().toDate();
    }

    /**
     * 将日期型转换为字符串
     * @param date 日期
     * @return String
     */
    public static String format(Date date) {
        return new DateTime(date).toString(dateFormat1);
    }
    public static String format(Date date,String formatStr) {
        return new DateTime(date).toString(formatStr);
    }
    /**
     * 将日期型转换为字符串
     * @param date 日期
	 * @param formatter 日期格式字符
     * @return String
     */
    public static String format(Date date, DateTimeFormatter formatter) {
        return new DateTime(date).toString(formatter);
    }

    /**
     * 将String转换为Date
     * @param date 日期
     * @return Date 日期
     */
    public static Date parse(String date) { 
        return dateFormat1.parseDateTime(StringUtils.substring(date, 0, 10)).toDate();
    }

    /**
     * 将String转换为Date
     * @param date 日期
	 * @param formatter 日期格式字符
     * @return Date 日期
     */
    public static Date parse(String date, DateTimeFormatter formatter) {
        return formatter.parseDateTime(date).toDate();
    }

    /**
     * 获取月末日期
     * @param date 日期
     * @return Date 日期
     */
    public static Date getLastDayOfMonth(Date date) {
        DateTime datetime = new DateTime(date);

        int lastDay = datetime.dayOfMonth().getMaximumValue();
        return datetime.withDayOfMonth(lastDay).toDate();
    }

    /**
     * 获取月初日期
     * @param date 日期
     * @return Date 日期
     */
    public static Date getFirstDayOfMonth(Date date) {
        DateTime datetime = new DateTime(date);

        return datetime.withDayOfMonth(1).toDate();
    }

    /**
     * 获取年初日期
     * @param date 日期
     * @return Date 日期
     */
    public static Date getFirstDayOfYear(Date date) {
        DateTime datetime = new DateTime(date);
        return datetime.withMonthOfYear(1).withDayOfMonth(1).toDate();
    }

    /**
     * 获取年末日期
     * @param date 日期
     * @return Date 日期
     */
    public static Date getLastDayOfYear(Date date) {
        DateTime datetime = new DateTime(date);
        return datetime.withMonthOfYear(12).withDayOfMonth(31).toDate();
    }

    /**
     * 计算日期前后step�?
     * @param date 日期
     * @param step 天数
     * @return Date 日期
     */
    public static Date getDateByStep(Date date, int step) {
        DateTime datetime = new DateTime(date);
        return datetime.plusDays(step).toDate();
    }

    /**
     * 根据清算日期获取自然月的起止日期
     * @param date 日期
     * @return Date[]
     */
    public static Date[] getDayOfMonthInterval(Date date) {
        return new Date[] { getFirstDayOfMonth(date), getLastDayOfMonth(date) };
    }

    /**
     * 计算两个日期之间的天
     * @param d1
     * @param d2
     * @return
     */
    public static int getDateDifferent(Date d1, Date d2) {
        return Days.daysBetween(new DateTime(d1), new DateTime(d2)).getDays();
    }

    /**
     * 计算账单还款日期
     * @param month
     * @param day
     * @return
     */
    public static String  getRepayDate(int month,int day){
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormatStr1);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return sdf.format(calendar.getTime());
    }

    /**
     * 根据分期数 类推还款计划
     * @param num
     * @return
     */
    public static List<String> getdataList(int num){
        List<String> dataList=new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormatStr1);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,Integer.valueOf("10"));
        for(int i=0;i<num;i++){
            calendar.add(Calendar.MONTH, 1);
            dataList.add(sdf.format(calendar.getTime()));
        }
        return dataList;
    }
    public static void main(String[] args) {
		System.out.println(getdataList(6));
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.get(Calendar.DATE));
	}

    /**
     * 类推还款计划 用Jota-time重写此方法
     * @param number 分期数
     * @return
     */
    public static List<String> getAnalogyDate(int number){
        DateTime dateTime= new DateTime(new Date());
        dateTime.withDayOfYear(10);
        List<String> dataList =  new ArrayList<>();
        for (int i = 1; i < number+1; i++) {
            String format = format(dateTime.plusMonths(i).toDate());
            dataList.add(format);
        }
        return dataList;
    }

    /**
     * 日期推算
     *
     * @param startDate
     * @param daysPlus
     * @return
     */
    public static Date getDatePlusDay(Date startDate, Integer daysPlus) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_YEAR, daysPlus);
        return calendar.getTime();
    }

    public static Date getDatePlusHour(Date startDate, Integer daysPlus) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.HOUR_OF_DAY, daysPlus);
        return calendar.getTime();
    }

    /**
     * 日期推算
     *
     * @param startDate
     * @param monthPlus
     * @return
     */
    public static Date getDatePlusMonth(Date startDate, Integer monthPlus) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.MONTH, monthPlus);
        return calendar.getTime();
    }

    /**
     * 获取指定日期的当月第一天的日期
     *
     * @param startDate
     * @return
     */
    public static Date getDateFirstDay(Date startDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        return calendar.getTime();
    }

    /**
     * 获取指定日期的当月最后一天的日期
     *
     * @param startDate
     * @return
     */
    public static Date getDateLastDay(Date startDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        return calendar.getTime();
    }


    /**
     * 解析日期（传入格式）
     *
     * @param source
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date parseToDate(String source, String pattern)
            throws ParseException {
        DateFormat defaultFormat = new SimpleDateFormat(pattern);
        return defaultFormat.parse(source);
    }

    public static Date getCurrentDate(String pattern)
            throws ParseException {
        DateFormat defaultFormat = new SimpleDateFormat(pattern);
        String date = defaultFormat.format(new Date());
        return defaultFormat.parse(date);
    }

    public static String getCurrentDateyyyymmdd(){
        DateFormat defaultFormat = new SimpleDateFormat(dateFormatStr3);
        String date = defaultFormat.format(new Date());
        return date;
    }

    /**
     * int类型的时间转换成date
     * @param time
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date timeInt2Date(int time,String pattern) throws ParseException {
        long nowTimeLong=new Long(time).longValue()*1000;
        DateFormat ymdhmsFormat = new SimpleDateFormat(pattern);
        String nowTimeStr = ymdhmsFormat.format(nowTimeLong);
        Date nowTimeDate = ymdhmsFormat.parse(nowTimeStr);
        return nowTimeDate;
    }

    /**
     * int类型的时间转换成date
     * @param time
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static String timeInt2DateStr(int time,String pattern) throws ParseException {
        long nowTimeLong=new Long(time).longValue()*1000;
        DateFormat ymdhmsFormat = new SimpleDateFormat(pattern);
        String nowTimeStr = ymdhmsFormat.format(nowTimeLong);
        return nowTimeStr;
    }

    /**
     * 获取当前时间 几个月前后的日期
     * @param month
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date getDateAfterMonth(Date date,int month,String pattern) throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, month);
        Date m = c.getTime();
        String mon = format.format(m);
        return format.parse(mon);
    }

    public static long getCurrentDateLongTime(String pattern) throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String dateStr = format.format(new Date());
        Date date = format.parse(dateStr);
        return date.getTime();
    }

    /**
     * 获取当前时间 几个月前后的日期
     * @param month
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date getDateAfterDays(Date date,int day,String pattern) throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE,day);
        Date m = c.getTime();
        String mon = format.format(m);
        return format.parse(mon);
    }
}
