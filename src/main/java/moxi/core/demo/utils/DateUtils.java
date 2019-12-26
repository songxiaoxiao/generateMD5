package moxi.core.demo.utils;

import moxi.core.demo.constans.CommonConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * 日期方法助手
 *
 * @author winter
 */
public class DateUtils {
    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    /**
     * yyyy/M/d
     */
    public static final String DATE_FORMAT1 = "yyyy/M/d";
    /**
     * yyyy-M-d
     */
    public static final String DATE_FORMAT2 = "yyyy-M-d";
    /**
     *
     G 年代标志符
     y 年
     M 月
     d 日
     h 时 在上午或下午 (1~12)
     H 时 在一天中 (0~23)
     m 分
     s 秒
     S 毫秒
     E 星期
     D 一年中的第几天
     F 一月中第几个星期几
     w 一年中第几个星期
     W 一月中第几个星期
     a 上午 / 下午 标记符
     k 时 在一天中 (1~24)
     K 时 在上午或下午 (0~11)
     z 时区
     */
    /**
     * yyyy.M.d
     */
    public static final String DATE_FORMAT3 = "yyyy.M.d";
    /**
     * yyyy-MM-dd
     */
    public static final String DATE_FORMAT4 = "yyyy-MM-dd";
    /**
     * yyyy/MM/dd
     */
    public static final String DATE_FORMAT5 = "yyyy/MM/dd";
    /**
     * yyyy.MM.dd
     */
    public static final String DATE_FORMAT6 = "yyyy.MM.dd";
    /**
     * yy-M-d
     */
    public static final String DATE_FORMAT7 = "yy-M-d";
    /**
     * yy/M/d
     */
    public static final String DATE_FORMAT8 = "yy/M/d";
    /**
     * yy.M.d
     */
    public static final String DATE_FORMAT9 = "yy.M.d";
    /**
     * yy/MM/dd
     */
    public static final String DATE_FORMA10 = "yy/MM/dd";
    /**
     * MM/dd
     */
    public static final String DATE_FORMA11 = "MM/dd";
    /**
     * HH:mm:ss
     */
    public static final String TIME_FORMAT1 = "HH:mm:ss";
    /**
     * HH:mm
     */
    public static final String TIME_FORMAT2 = "HH:mm";
    /**
     * HH:mm:ss tt
     */
    public static final String TIME_FORMAT3 = "HH:mm:ss a";
    /**
     * HH:mm tt
     */
    public static final String TIME_FORMAT4 = "HH:mm a";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String _YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";

    public static String YYYYMMDD = "yyyy-MM-dd";

    public static String YYYY_MM_DD = "yyyyMMdd";

    /**
     * 有闰年判断的regex，匹配2019-1-1
     * 允许的日期分隔符有[-/.,，_]
     * 其他的非法字符（如空格，全角空格等）要在做正则之前过滤掉
     * 更加严格的regex，不匹配2019-1-1
     * ^((?!0000)[0-9]{4}([/\\-_.,，])((0[1-9]|1[0-2])([/\\-_.,，])(0[1-9]|1[0-9]|2[0-8])|(0[13-9]|1[0-2])([/\\-_.,，])(29|30)|(0[13578]|1[02])([/\\-_.,，])31)|([0-9]{2}(0[48]|[2468][048]|[13579][26])|(0[48]|[2468][048]|[13579][26])00)([/\\-_.,，])02([/\\-_.,，])29)$
     */
    public static Pattern DATEPATTERN = Pattern.compile("(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-/.,，_])(10|12|0?[13578])([-/.,，_])(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-/.,，_])(11|0?[469])([-/.,，_])(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-/.,，_])(0?2)([-/.,，_])(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-/.,，_])(0?2)([-/.,，_])(29)$)|(^([3579][26]00)([-/.,，_])(0?2)([-/.,，_])(29)$)|(^([1][89][0][48])([-/.,，_])(0?2)([-/.,，_])(29)$)|(^([2-9][0-9][0][48])([-/.,，_])(0?2)([-/.,，_])(29)$)|(^([1][89][2468][048])([-/.,，_])(0?2)([-/.,，_])(29)$)|(^([2-9][0-9][2468][048])([-/.,，_])(0?2)([-/.,，_])(29)$)|(^([1][89][13579][26])([-/.,，_])(0?2)([-/.,，_])(29)$)|(^([2-9][0-9][13579][26])([-/.,，_])(0?2)([-/.,，_])(29)$)");
    public static Pattern TIMEPATTERN = Pattern.compile("^(?:[01]\\d|2[0-3]|\\d)(?:([:：])([0-5]\\d|\\d)){2}$");
    // public static Pattern TIMEPATTERN = Pattern.compile("^(?:[01]\\d|2[0-3])(?:([:：])[0-5]\\d){2}$");


    public static Long toLong(String dateStr) {
        Date date = toDate(dateStr);
        if (date != null) {
            return date.getTime();
        }
        return null;
    }

    public static Date toDate(String dateStr) {
        if (StringUtils.isNotBlank(dateStr)) {
            if (dateStr.contains("-")) {
                if (dateStr.contains(" ")) {
                    return toDate(dateStr, DATE_FORMAT4 + " " + TIME_FORMAT1);
                } else {
                    return toDate(dateStr, DATE_FORMAT4);
                }
            } else if (dateStr.contains("/")) {
                if (dateStr.contains(" ")) {
                    return toDate(dateStr, DATE_FORMAT5 + " " + TIME_FORMAT1);
                } else {
                    return toDate(dateStr, DATE_FORMAT5);
                }
            } else if (dateStr.contains(".")) {
                if (dateStr.contains(" ")) {
                    return toDate(dateStr, DATE_FORMAT6 + " " + TIME_FORMAT1);
                } else {
                    return toDate(dateStr, DATE_FORMAT6);
                }
            }
        }
        return null;
    }

    public static Date toDate(String dateStr, String format) {
        if (StringUtils.isNotBlank(dateStr) && StringUtils.isNotBlank(format)) {
            try {
                return new SimpleDateFormat(format).parse(dateStr);
            } catch (ParseException e) {
                logger.error("*****DateUtils.toDate报错,dateStr={},format={}", dateStr, format);
            }

        }
        return null;
    }

    public static String dateToString(Date date, String datePattern, String timePattern) {
        if (date != null && StringUtils.isNotBlank(datePattern) && StringUtils.isNotBlank(timePattern)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
            SimpleDateFormat timeFormat = new SimpleDateFormat(timePattern);

            String yesterdayStr = dateFormat.format(Date.from(LocalDateTime.now().plusDays(-1).atZone(ZoneId.systemDefault()).toInstant()));
            String todayStr = dateFormat.format(new Date());
            String tomorrowStr = dateFormat.format(Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant()));

            String dateStr = dateFormat.format(date);
            String timeStr = timeFormat.format(date);

            if (Objects.equals(dateStr, yesterdayStr)) {
                return CommonConstants.YESTERDAY_TEXT + CommonConstants.BLANK_SPACE + timeStr;
            } else if (Objects.equals(dateStr, todayStr)) {
                return CommonConstants.TODAY_TEXT + CommonConstants.BLANK_SPACE + timeStr;
            } else if (Objects.equals(dateStr, tomorrowStr)) {
                return CommonConstants.TOMORROW_TEXT + CommonConstants.BLANK_SPACE + timeStr;
            } else {
                return dateStr + CommonConstants.BLANK_SPACE + timeStr;
            }
        }
        return null;
    }

    public static String dateToString(Date date, String pattern) {
        if (date != null && StringUtils.isNotBlank(pattern)) {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            return format.format(date);
        }
        return null;
    }

    public static String dateToYMD(Date date) {
        if (date != null) {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT4);
            return format.format(date);
        }
        return null;
    }

    public static String dateToYMDHMS(Date date) {
        if (date != null) {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT4 + " " + TIME_FORMAT1);
            return format.format(date);
        }
        return null;
    }

    public static String dateToYMD(LocalDateTime date) {
        if (date != null) {
            DateTimeFormatter df = DateTimeFormatter.ofPattern(DATE_FORMAT4);
            return df.format(date);
        }
        return null;
    }

    public static String dateToYMD(LocalDate date) {
        if (date != null) {
            DateTimeFormatter df = DateTimeFormatter.ofPattern(DATE_FORMAT4);
            return df.format(date);
        }
        return null;
    }

    public static String dateToYMDHMS(LocalDateTime date) {
        if (date != null) {
            DateTimeFormatter df = DateTimeFormatter.ofPattern(DATE_FORMAT4 + " " + TIME_FORMAT1);
            return df.format(date);
        }
        return null;
    }

    public static DayOfWeek dateToWeekDay(Date date) {
        if (date != null) {
            LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
            return dateTime.getDayOfWeek();
        }
        return null;
    }

    /**
     * 获取 年后两位+月+日+时+分+秒 四位数
     */
    public static String dateUUID() {

        String stringYMS = formatDateToStringYMS(new Date());
        return stringYMS.substring(2);

    }

    /**
     * 获取指定日期所在月份开始的时间戳
     *
     * @param date 指定日期
     */
    public static long monthBegin(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        //设置为1号,当前日期既为本月第一天
        c.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0
        c.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        c.set(Calendar.MINUTE, 0);
        //将秒至0
        c.set(Calendar.SECOND, 0);
        //将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
        // 获取本月第一天的时间戳
        return c.getTimeInMillis();
    }

    /**
     * 获取指定上一个月份的开始时间戳
     *
     * @param date 指定日期
     */
    public static long beforeMonthBeginTime(Date date) {
        if (date == null) date = new Date();

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        c.set(Calendar.MONTH, Integer.valueOf(DateUtils.formatDateToString(date, "MM")) - 2);
        //设置为1号,当前日期既为本月第一天
        c.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0
        c.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        c.set(Calendar.MINUTE, 0);
        //将秒至0
        c.set(Calendar.SECOND, 0);
        //将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
        // 获取本月第一天的时间戳
        return c.getTimeInMillis();
    }

    /**
     * 获取上一个月日期所在月份开始的时间戳
     *
     * @param date 指定日期
     */
    public static long beforeMonthEndTime(Date date) {
        if (date == null) date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        c.set(Calendar.MONTH, Integer.valueOf(DateUtils.formatDateToString(date, "MM")) - 2);
        //设置为1号,当前日期既为本月第一天
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        //将小时至0
        c.set(Calendar.HOUR_OF_DAY, 23);
        //将分钟至0
        c.set(Calendar.MINUTE, 59);
        //将秒至0
        c.set(Calendar.SECOND, 59);
        //将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
        // 获取本月最后一天的时间戳
        return c.getTimeInMillis();
    }

    /**
     * 获取指定日期所在月份开始的时间戳
     *
     * @param date        指定日期
     * @param monthNumber 往前推几个月
     */
    public static long beforeMonthEndTime(Date date, int monthNumber) {
        if (date == null) date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        c.set(Calendar.MONTH, Integer.valueOf(DateUtils.formatDateToString(date, "MM")) - monthNumber - 1);
        //设置为1号,当前日期既为本月第一天
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        //将小时至0
        c.set(Calendar.HOUR_OF_DAY, 23);
        //将分钟至0
        c.set(Calendar.MINUTE, 59);
        //将秒至0
        c.set(Calendar.SECOND, 59);
        //将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
        // 获取本月最后一天的时间戳
        return c.getTimeInMillis();
    }

    /**
     * 获取指定日期所在月份开始的时间戳
     *
     * @param date 指定日期
     */
    public static long monthEnd(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        //设置为1号,当前日期既为本月第一天
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        //将小时至0
        c.set(Calendar.HOUR_OF_DAY, 23);
        //将分钟至0
        c.set(Calendar.MINUTE, 59);
        //将秒至0
        c.set(Calendar.SECOND, 59);
        //将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
        // 获取本月最后一天的时间戳
        return c.getTimeInMillis();
    }

    /**
     * 去当前时间往前推五个小时的月份
     */
    public static long fiveHourMonthBegin() {
        long time = System.currentTimeMillis();
        return monthBegin(new Date(time - 1000 * 60 * 60 * 5));
    }

    /**
     * 去当前时间往前推五个小时的sS时间戳
     */
    public static long beforeTime() {
        long time = System.currentTimeMillis();

        return time - 1000 * 60 * 60 * 5;
    }

    public static int nowYear() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }

    public static int nowMonth() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH) + 1;
    }

    public static Date beforeMonthDate(Date date, int monthNumber) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MONTH, Integer.valueOf(formatDateToString(date, "MM")) - monthNumber - 1);
        return c.getTime();
    }

    public static Date afterMonthDate(Date date, int monthNumber) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MONTH, Integer.valueOf(formatDateToString(date, "MM")) + monthNumber - 1);
        return c.getTime();
    }

    public static Date beforeWeek(Date date, int weekNumber) {
        if (date == null) {
            date = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, Integer.valueOf(formatDateToString(date, "W")) - weekNumber);
        return c.getTime();
    }

    /**
     * 获取两个月份间的所有月份list
     *
     * @param startMonth
     * @param endMonth
     * @return
     */
    public static LinkedList<String> getMonthList(String startMonth, String endMonth) {
        LinkedList<String> monthList = new LinkedList<>();
        Date monthStart = new SimpleDateFormat("yyyy-MM").parse(startMonth, new ParsePosition(0));
        Date monthEnd = new SimpleDateFormat("yyyy-MM").parse(endMonth, new ParsePosition(0));
        while (monthStart.compareTo(monthEnd) <= 0) {
            monthList.add(new SimpleDateFormat("yyyy-MM").format(monthStart));
            monthStart = afterMonthDate(monthStart, 1);
        }
        return monthList;
    }

    public static String formatDateToString(Date date, String simple) {
        if (StringUtils.isEmpty(simple) || date == null) {
            return null;
        }
        SimpleDateFormat simpFormat = new SimpleDateFormat(simple);
        return simpFormat.format(date);
    }

    public static Date formatStringToDate(String simple, String str) {
        if (StringUtils.isEmpty(simple) || StringUtils.isEmpty(str)) {
            return null;
        }
        SimpleDateFormat simpFormat = new SimpleDateFormat(simple);
        try {
            return simpFormat.parse(str);
        } catch (ParseException e) {
            return null;
        }

    }

    public static String formatDateToStringYMS(Date date) {
        if (date == null) {
            return null;
        }
        return formatDateToString(date, YYYYMMDDHHMMSS);
    }

    public static Date formatStringToDateByRail(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        return formatStringToDate(_YYYYMMDDHHMMSS, str);
    }

    public static java.sql.Date formatStr2SqlDate(String dateStr) {
        return formatStr2SqlDate(dateStr, YYYYMMDD);
    }

    public static java.sql.Date formatStr2SqlDate(String dateStr, String format) {
        if (StringUtils.isEmpty(dateStr) || StringUtils.isEmpty(format)) {
            return null;
        }
        SimpleDateFormat simpFormat = new SimpleDateFormat(format);
        try {
            return new java.sql.Date(simpFormat.parse(dateStr).getTime());
        } catch (ParseException e) {
            return null;
        }
    }

    public static Timestamp formatStr2SqlTimestamp(String timeStr) {
        return formatStr2SqlTimestamp(timeStr, _YYYYMMDDHHMMSS);
    }

    public static Timestamp formatStr2SqlTimestamp(String timeStr, String format) {
        if (StringUtils.isEmpty(timeStr) || StringUtils.isEmpty(format)) {
            return null;
        }
        SimpleDateFormat simpFormat = new SimpleDateFormat(format);
        try {
            return new Timestamp(simpFormat.parse(timeStr).getTime());
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * he当前时间比较
     *
     * @param i    增加或者减少的天数
     * @param date 时间
     * @return
     */
    public static int compareDateByDay(int i, Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        GregorianCalendar gcal = new GregorianCalendar();
        gc.setTime(date);
        gc.add(Calendar.DATE, i);
        gcal.setTime(new Date());
        return gc.compareTo(gcal);
    }

    public static int compareDateBySecond(Date start, Date end) {
        GregorianCalendar gc = new GregorianCalendar();
        GregorianCalendar gcal = new GregorianCalendar();
        gc.setTime(start);
        gcal.setTime(end);
        return gc.compareTo(gcal);
    }

    public static Date addSecond(Date date, int seconds) {
        return addTime(date, Calendar.SECOND, seconds);
    }

    public static Date addMinute(Date date, int minutes) {
        return addTime(date, Calendar.MINUTE, minutes);
    }

    public static Date addHour(Date date, int hours) {
        return addTime(date, Calendar.HOUR, hours);
    }

    public static Date addDay(Date date, int days) {
        return addTime(date, Calendar.DATE, days);
    }

    public static Date addMonth(Date date, int month) {
        return addTime(date, Calendar.MONTH, month);
    }

    public static Date addYear(Date date, int years) {
        return addTime(date, Calendar.YEAR, years);
    }

    private static Date addTime(Date date, int field, int amount) {
        Calendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(field, amount);
        return gc.getTime();
    }

    public static long getDiff(Date date1, Date date2, TimeUnit unit) {
        long duration = date2.getTime() - date1.getTime();

        if (TimeUnit.DAYS.toString().equals(unit.toString().toUpperCase())) {
            return duration / (1000 * 60 * 60 * 24);
        }

        if (TimeUnit.HOURS.toString().equals(unit.toString().toUpperCase())) {
            return duration / (1000 * 60 * 60);
        }

        if (TimeUnit.MINUTES.toString().equals(unit.toString().toUpperCase())) {
            return duration / (1000 * 60);
        }

        if (TimeUnit.SECONDS.toString().equals(unit.toString().toUpperCase())) {
            return duration / (1000);
        }

        if (TimeUnit.MILLISECONDS.toString().equals(unit.toString().toUpperCase())) {
            return duration;
        }

        return 0L;
    }

    public static Long getMinDate(String date) {

        DateFormat df = new SimpleDateFormat(YYYYMMDD);
        try {
            Date d = df.parse(date);
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            c.set(Calendar.SECOND, 59);
            c.set(Calendar.MINUTE, 59);
            c.set(Calendar.HOUR, 23);
            return c.getTimeInMillis();
        } catch (ParseException e) {

        }
        return null;
    }

    public static Long getMaxDate(String date) {
        DateFormat df = new SimpleDateFormat(YYYYMMDD);
        try {
            Date d = df.parse(date);
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.HOUR, 0);
            return c.getTimeInMillis();
        } catch (ParseException e) {
            return System.currentTimeMillis();
        }
    }

    public static String format(final Long time) {
        TimeZone tz = TimeZone.getTimeZone("GMT+08:00");
        TimeZone.setDefault(tz);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(time) + " (GMT+08:00)";
    }

    public static String timeFormat(Long time) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(time);
    }

    public static String timeFormat(Long time, String simple) {
        TimeZone tz = TimeZone.getTimeZone("GMT+08:00");
        TimeZone.setDefault(tz);
        SimpleDateFormat df = new SimpleDateFormat(simple);
        return df.format(time);
    }

    public static long now() {
        return System.currentTimeMillis();
    }

    public static String getYmd(Long time) {
        if (null == time) {
            return null;
        }
        Date d = new Date(time);
        return formatDateToString(d, YYYYMMDD);
    }

    public static String getYm(Long time) {
        if (null == time) {
            return null;
        }
        Date d = new Date(time);
        return formatDateToString(d, "yyyy-MM");
    }

    /**
     * date1 - date2
     *
     * @param date1 <String>
     * @param date2 <String>
     * @return int
     * @throws ParseException
     * @author winter
     */
    public static int getMonthSpace(Date date1, Date date2) {
        return compareDate(formatDateToString(date2, "yyyy-MM"), formatDateToString(date1, "yyyy-MM"), 1);

    }

    /**
     * date1 - date2
     *
     * @param date1 <String>
     * @param date2 <String>
     * @return int
     * @throws ParseException
     * @author winter
     */
    public static int getDaySpace(Date date1, Date date2) {
        return compareDate(formatDateToString(date2, "yyyy-MM-dd"), formatDateToString(date1, "yyyy-MM-dd"), 0);

    }

    /**
     * date1 - date2
     *
     * @param date1 <String>
     * @param date2 <String>
     * @return int
     * @throws ParseException
     * @author winter
     */
    public static int getYearSpace(Date date1, Date date2) {
        return compareDate(formatDateToString(date2, "yyyy"), formatDateToString(date1, "yyyy"), 2);

    }

    /**
     * date1 - date2
     *
     * @param date1 <String>
     * @param date2 <String>
     * @return int
     * @throws ParseException
     * @author winter
     */
    public static int getSeanSpace(Date date1, Date date2) {

        int n = 0;

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(date2);
            c2.setTime(date1);
        } catch (Exception e3) {
            return 0;
        }
        List<String> sean = new ArrayList<>();

        // 循环对比，直到相等，n 就是所要的结果
        while (!c1.after(c2)) {
            // 比较月份，月份+1
            c1.add(Calendar.MONTH, 1);
            switch (c1.get(Calendar.MONTH)) {
                case 1:
                case 2:
                case 3:
                    if (!sean.contains(c1.get(Calendar.YEAR) + "-01")) {
                        sean.add(c1.get(Calendar.YEAR) + "-01");
                        n++;
                    }
                    break;
                case 4:
                case 5:
                case 6:
                    if (!sean.contains(c1.get(Calendar.YEAR) + "-02")) {
                        sean.add(c1.get(Calendar.YEAR) + "-02");
                        n++;
                    }
                    break;
                case 7:
                case 8:
                case 9:
                    if (!sean.contains(c1.get(Calendar.YEAR) + "-03")) {
                        sean.add(c1.get(Calendar.YEAR) + "-03");
                        n++;
                    }
                    break;
                case 10:
                case 11:
                case 12:
                    if (!sean.contains(c1.get(Calendar.YEAR) + "-04")) {
                        sean.add(c1.get(Calendar.YEAR) + "-04");
                        n++;
                    }
                    break;
                default:
                    break;
            }
        }

        return n;


    }


    //0 天，1月，2年，3季
    public static int compareDate(String date1, String date2, int stype) {
        int n = 0;

        String[] u = {"天", "月", "年"};
        String formatStyle = stype == 1 ? "yyyy-MM" : "yyyy-MM-dd";

        date2 = date2 == null ? getCurrentDate() : date2;

        DateFormat df = new SimpleDateFormat(formatStyle);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(df.parse(date1));
            c2.setTime(df.parse(date2));
        } catch (Exception e3) {
            return 0;
        }
        //List list = new ArrayList();
        // 循环对比，直到相等，n 就是所要的结果
        while (!c1.after(c2)) {
            //list.add(df.format(c1.getTime()));    // 这里可以把间隔的日期存到数组中 打印出来
            n++;
            if (stype == 1) {
                // 比较月份，月份+1
                c1.add(Calendar.MONTH, 1);
            } else {
                // 比较天数，日期+1
                c1.add(Calendar.DATE, 1);
            }
        }

        n = n - 1;

        if (stype == 2) {
            n = (int) n / 365;
        }
        return n;
    }

    /**
     * 得到当前日期
     *
     * @return
     */
    public static String getCurrentDate() {
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
        return simple.format(date);
    }

}
