package org.wl.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

/**
 * @className: StringHelpers.java
 * @author: wangLei
 * @version: v1.0
 * @date: 2015年5月27日 下午1:59:51
 * @description: 日期类型处理工具类
 */
public class DateUtils {

	public static final String defaultDatePattern = "yyyy-MM-dd";

	public static final String DatePattern = "yyyy-MM-dd HH:mm";

	public static final String FM_DATE_AND_TIME = "yyyy-MM-dd HH:mm:ss";

	public final static String FM_PATTERN_CN_MD_HM = "MM月dd日 HH:mm";

	public final static String FM_PATTERN_CN_MD_NO = "MM月dd日";

	public final static String FM_PATTERN_CN_YMD_HM = "yyyy年MM月dd日 HH:mm";

	public final static String FM_PATTERN_CN_YMD_NO = "yyyy年MM月dd日";

	public final static String FM_PATTERN_CN_YM_NO = "yyyy年MM月";

	public final static String FM_PATTERN_EN_MD_HM = "MM-dd HH:mm";

	public final static String FM_PATTERN_EN_MD_NO = "MM-dd";

	public final static String FM_PATTERN_EN_YMD_HM = "yyyy/MM/dd HH:mm";

	public final static String FM_PATTERN_EN_YMD_NO = "yyyy/MM/dd";

	public final static String FM_PATTERN_EN_YM_NO = "yyyy/MM";

	public final static String[] WeekCn = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

	public final static String[] WeekEn = { "Sunday", "Monday", "Tuesday", "Wednsday", "Thursday", "Friday", "Saturday" };

	public final static String[] MonthCn = { "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二" };

	public final static String[] NumberCnS = { "〇", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十" };

	public final static String[] NumberCnF = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖", "拾" };

	public final static String[] MonthEn = { "January", "February", "March", "April", "May", "June", "July", "August",
			"September", "October", "November", "December" };

	public final static Calendar cal = Calendar.getInstance();

	// 不能外部实例化
	private DateUtils() {

	}

	/**
	 * 取得当前日期
	 *
	 * @return Date
	 */
	public static Date getNowDate() {
		Calendar cal = Calendar.getInstance();
		return new Date(cal.getTimeInMillis());
	}

	/**
	 * 取得当前时间
	 *
	 * @return java.sql.Timestamp
	 */
	public static Timestamp getNowTimestamp() {
		Calendar cal1 = Calendar.getInstance();
		return new Timestamp(cal1.getTimeInMillis());
	}

	/**
	 * 取当前日期
	 * 
	 * @return Date类型
	 */
	public static Date getTodayDate() {
		long date = System.currentTimeMillis();
		Date result = new Date(date);

		return result;
	}

	/**
	 * 取得当前时间，精确到时分秒
	 * 
	 * @return
	 */
	public static String getNowDateTime() {
		return format(getNowDate(), DateUtils.FM_DATE_AND_TIME);
	}

	/**
	 * long类型的毫秒数转化为日期字符串
	 * 
	 * @param millis
	 * @return
	 */
	public static String formatDateLong(long millis) {
		String pattern = "yyyy-MM-dd HH:mm";
		String date = DateFormatUtils.format(millis, pattern, Locale.CHINA);
		return date;
	}

	/**
	 * 格式化输入的日期 时间
	 *
	 * @param date 日期 时间
	 * @return 日期
	 */
	public static Date getDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return new Date(cal.getTimeInMillis());
	}

	/**
	 * 格式化日期时间 yyyy年MM月dd日 HH时mm分ss秒
	 * 
	 * @param myDate
	 * @return
	 */
	public static String formatDateToYMDHMS(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	/**
	 * 格式化日期 yyyy年MM月dd日
	 * 
	 * @param myDate
	 * @return
	 */
	public static String formatDateToyyMd(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat(FM_PATTERN_CN_YMD_NO);
		String strDate = formatter.format(myDate);
		return strDate;
	}

	/**
	 * 当前日期，时间转换为长整型
	 * 
	 * @return 长整型数字
	 */
	public static long getNowDateTimeLong() {
		Calendar cal = Calendar.getInstance();
		return cal.getTimeInMillis();
	}

	/**
	 * 格式化时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param myDate
	 * @return
	 */
	public static String formatDateTOyMdHms(Date myDate) {
		String strDate = null;
		if (myDate == null)
			strDate = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		strDate = formatter.format(myDate);
		return strDate;
	}

	/**
	 * 格式化日期时间 yyyy-MM-dd
	 * 
	 * @param myDate
	 * @return
	 */
	public static String formatDateTOymd(Date myDate) {
		String strDate = null;
		if (myDate == null)
			strDate = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		strDate = formatter.format(myDate);
		return strDate;
	}

	/**
	 * 格式化日期时间 yyyy/MM/dd
	 * 
	 * @param myDate
	 * @return
	 */
	public static String formatDateToyMd(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat(FM_PATTERN_EN_YMD_NO);
		String strDate = formatter.format(myDate);
		return strDate;
	}

	/**
	 * 格式化日期时间 MM-dd HH:mm
	 * 
	 * @param myDate
	 * @return
	 */
	public static String formatDateToMdHm(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	/**
	 * 格式化日期时间 yyyyMMdd HH:mm:ss
	 * 
	 * @param myDate
	 * @return
	 */
	public static String formatDateToymdHms(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	/**
	 * 用于获取指定日期该月的所有日期
	 *
	 * @param date Date 要获取的月日期列表的指定日期
	 * @return Date[] Date 返回的日期列表
	 */
	public static Date[] getMonthDays(Date date) {

		Calendar cale = Calendar.getInstance();
		cale.setTime(date);

		int today = cale.get(Calendar.DAY_OF_MONTH);
		int days = cale.getActualMaximum(Calendar.DAY_OF_MONTH);
		long millis = cale.getTimeInMillis();

		Date dates[] = new Date[days];
		for (int i = 1; i <= days; i++) {
			long sub = (today - i) * 24 * 60 * 60 * 1000L;
			dates[i - 1] = new Date(millis - sub);
		}

		cale = null;
		return dates;
	}

	/**
	 * 用于获取指定日期该周的所有日期
	 *
	 * @param date Date 要获取的周日期列表的指定日期
	 * @return Date[] Date 返回的日期列表
	 */
	public static Date[] getWeekDays(Date date) {

		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		cale.setFirstDayOfWeek(Calendar.SUNDAY);

		int days = 7;
		int today = cale.get(Calendar.DAY_OF_WEEK);
		long millis = cale.getTimeInMillis();

		Date dates[] = new Date[days];
		for (int i = 1; i <= days; i++) {
			long sub = (today - i) * 24 * 60 * 60 * 1000L;
			dates[i - 1] = new Date(millis - sub);
		}

		cale = null;
		return dates;
	}

	/**
	 * 根据开始时间和结束时间返回相应的时间段字符串，如果开始时间和结束时间在同一天， 则返回的格式为“X点X分-X点X分”，如果不在同一天，返回的格式为“X月X日-X月X日”
	 *
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return 返回的时间段字符串
	 */
	public static String getTimeSlice(Timestamp startTime, Timestamp endTime) {

		String rtn = "";

		Calendar caleStart = Calendar.getInstance();
		Calendar caleEnd = Calendar.getInstance();
		caleStart.setTimeInMillis(startTime.getTime());
		caleEnd.setTimeInMillis(endTime.getTime());

		String dayStart = caleStart.get(Calendar.YEAR) + "年" + (caleStart.get(Calendar.MONTH) + 1) + "月"
				+ caleStart.get(Calendar.DAY_OF_MONTH) + "日";
		String dayEnd = caleEnd.get(Calendar.YEAR) + "年" + (caleEnd.get(Calendar.MONTH) + 1) + "月"
				+ caleEnd.get(Calendar.DAY_OF_MONTH) + "日";

		if (dayStart.equals(dayEnd)) {
			// 同一天
			rtn = caleStart.get(Calendar.HOUR_OF_DAY) + "点" + caleStart.get(Calendar.MINUTE) + "分-"
					+ caleEnd.get(Calendar.HOUR_OF_DAY) + "点" + caleEnd.get(Calendar.MINUTE) + "分";
		} else {
			// 不在同一天
			rtn = (caleStart.get(Calendar.MONTH) + 1) + "月" + caleStart.get(Calendar.DAY_OF_MONTH) + "日" + "-"
					+ (caleEnd.get(Calendar.MONTH) + 1) + "月" + caleEnd.get(Calendar.DAY_OF_MONTH) + "日";
		}

		caleStart = null;
		caleEnd = null;
		return rtn;
	}

	/**
	 * 根据日期获得日期星期几格式的字符串，如“2004-07-28 星期三”
	 *
	 * @param date 指定的日期
	 * @return 返回的日期星期几格式的字符串
	 */
	public static String getDayWeek(Date date) {

		String days[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		cale.setFirstDayOfWeek(Calendar.SUNDAY);
		return formatDateTOymd(date) + " " + days[cale.get(Calendar.DAY_OF_WEEK) - 1];
	}

	/**
	 * 获得指定日期所在月最小时间
	 *
	 * @param date 指定的日期
	 * @return 指定日期所在月的最小时间
	 */
	public static Date getMinDayInMonth(Date date) {

		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		cale.set(Calendar.DAY_OF_MONTH, cale.getActualMinimum(Calendar.DAY_OF_MONTH));
		Date newDate = new Date(cale.getTimeInMillis());
		return newDate;
	}

	/**
	 * 获得指定日期所在月的最大时间
	 *
	 * @param date
	 * @return
	 */
	public static Date getMaxDayInMonth(Date date) {

		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		cale.set(Calendar.DAY_OF_MONTH, cale.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date newDate = new Date(cale.getTimeInMillis());
		return newDate;
	}

	/**
	 * 得到当前年-月
	 * 
	 * @return
	 */
	public static String getThisYearAndMonth() {
		String dateString = "";

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		Date currentTime_1 = new Date();

		dateString = formatter.format(currentTime_1);

		return dateString;
	}

	/**
	 * 得到当前年-月-日
	 * 
	 * @return
	 */
	public static String getThisYearAndMonthAndDay() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = formatter.format(new Date());
		return dateStr;
	}

	/**
	 * 得到当前年
	 * 
	 * @return
	 */
	public static String getThisYear() {
		String dateString = "";

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		Date currentTime_1 = new Date();
		dateString = formatter.format(currentTime_1);

		return dateString;
	}

	/**
	 * 得到当前月
	 * 
	 * @return
	 */
	public static String getThisMonth() {

		String dateString = "";

		SimpleDateFormat formatter = new SimpleDateFormat("MM");
		Date currentTime_1 = new Date();
		dateString = formatter.format(currentTime_1);

		return dateString;
	}

	/**
	 * 得到当前季度
	 * 
	 * @return
	 */
	public static String getThisQuarter() {

		String quarter = "";
		String month = getThisMonth();

		if (month.equals("01") || month.equals("02") || month.equals("03")) {
			quarter = "第一季度";

		} else if (month.equals("04") || month.equals("05") || month.equals("06")) {
			quarter = "第二季度";

		} else if (month.equals("07") || month.equals("08") || month.equals("09")) {
			quarter = "第三季度";

		} else {
			quarter = "第四季度";

		}

		return quarter;
	}

	/**
	 * 将字符串日期转换成Date类型,如：Date d = DateFormat("2001-02-17");
	 * 
	 * @param datetime
	 * @return
	 */
	public static Date toDate(String datetime) {
		String[] dt = SplitString(datetime, "-");

		if ((dt == null) || (dt.length != 3)) {
			return null;
		}

		int[] idt = new int[3];

		for (int i = 0; i < idt.length; i++) {
			idt[i] = StringHelpers.toInt(dt[i]);
		}

		Calendar c = Calendar.getInstance();
		c.set(idt[0], idt[1] - 1, idt[2]);

		return new Date(c.getTime().getTime());
	}

	/**
	 * 指定格式进行日期的转化
	 * 
	 * @param format
	 * @param datetime
	 * @return
	 */
	public static Date DateFormat(String format, String datetime) {
		SimpleDateFormat sdf = null;
		Date d = null;

		try {
			sdf = new SimpleDateFormat(format, Locale.CHINA);
			d = (Date) sdf.parse(datetime);
		} catch (Exception e) {
		}

		return d;
	}

	/**
	 * 计算出两个日期段之间的每一个月的分段日期列表 start：开始日期 end：结束日期 返回：两个日期段之间的月份分隔日期列表。
	 * 如：2002-05-23至2002-07-15两个日期段之间的日期分隔列表是2002-05-23、2002-06-01、2002-07-15 注意：开始日期必须小于结束日期。
	 */
	public static Date[][] getMonthDividedDateList(Date startDate, Date endDate) {
		Date[][] result = new Date[0][2];

		if ((startDate != null) && (endDate != null)) {
			Date start = (startDate.getTime() > endDate.getTime()) ? endDate : startDate;
			Date end = (startDate.getTime() < endDate.getTime()) ? endDate : startDate;
			java.util.Vector<Object> v = new java.util.Vector<Object>();

			// 计算开始日期的月份
			cal.setTime(new Date(start.getTime()));

			int month = cal.get(Calendar.MONTH);

			// 计算结束日期的时间
			cal.setTime(new Date(end.getTime()));

			long endtime = end.getTime();
			long tmptime = 0;
			v.add(start);

			do {
				// 取当月最后一天
				cal.set(Calendar.MONTH, month);
				cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
				tmptime = cal.getTime().getTime();

				if (tmptime <= endtime) {
					v.add(new Date(tmptime));

					// 取下月第一天
					cal.set(Calendar.MONTH, month + 1);
					cal.set(Calendar.DAY_OF_MONTH, 1);
					tmptime = cal.getTime().getTime();

					if (tmptime <= endtime) {
						v.add(new Date(tmptime));
					}
				}

				month++;
			} while (tmptime < endtime);

			v.add(end);
			result = new Date[v.size() / 2][2];

			for (int i = 0; i < result.length; i++) {
				result[i][0] = (Date) v.get((i * 2) + 0);
				result[i][1] = (Date) v.get((i * 2) + 1);
			}

			v.removeAllElements();
		}

		return result;
	}

	/**
	 * 比较两个日期是否按天相等
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isDateEqualsByDay(Date date1, Date date2) {
		if ((date1 == null) || (date2 == null)) {
			return false;
		}

		return DateUtils.getDateDays(date1) == DateUtils.getDateDays(date2);
	}

	/**
	 * 比较某个日期是否在两个日期之间，日期值不为空。
	 * 
	 * @param date
	 * @param periodStart
	 * @param periodEnd
	 * @return
	 */
	public static boolean isDateInPeriod(Date date, Date periodStart, Date periodEnd) {
		if ((date == null) || (periodStart == null) || (periodEnd == null)) {
			return false;
		}

		long d = DateUtils.getDateDays(date);
		long s = DateUtils.getDateDays(periodStart);
		long e = DateUtils.getDateDays(periodEnd);

		return ((d >= s) && (d <= e));
	}

	/**
	 * 取日期按天的数值；本地时区为+8所以要减去8小时
	 * 
	 * @param date
	 * @return
	 */
	public static long getDateDays(Date date) {
		if (date == null) {
			return -1;
		}

		return (long) ((date.getTime() + (1000 * 3600 * 8)) / (1000 * 3600 * 24));

	}

	/**
	 * 将日期转换为字符串
	 * 
	 * @param date
	 * @return X年X月X日 X周
	 */
	public static String getDateToString(Date date) {
		if (date == null) {
			return "";
		}

		String result = "";

		cal.setTime(new Date(date.getTime()));

		int _year = cal.get(Calendar.YEAR);
		int _month = cal.get(Calendar.MONTH) + 1;
		int _date = cal.get(Calendar.DATE);
		int _week = cal.get(Calendar.DAY_OF_WEEK) - 1;
		result += (_year + "年" + _month + "月" + _date + "日" + " " + WeekCn[_week]);

		return result;
	}

	/**
	 * 将字符串按照规则进行分段，如将"2001-10-12"按照"-"划分，则分为"2001"、"10"和"12"三段
	 * 
	 * @param szSource
	 * @param token
	 * @return
	 */
	public static String[] SplitString(String szSource, String token) {
		if ((szSource == null) || (token == null)) {
			return null;
		}

		StringTokenizer st1 = new StringTokenizer(szSource, token);
		String[] d1 = new String[st1.countTokens()];

		for (int x = 0; x < d1.length; x++)
			if (st1.hasMoreTokens()) {
				d1[x] = st1.nextToken();
			}

		return d1;
	}

	public static String getStr(String str) {
		return str == null ? "" : str;
	}

	/**
	 * 日期按天累加
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date getDateAddDay(Date date, long days) {
		if (date == null) {
			return null;
		}

		long times = date.getTime();

		for (int i = 0; i < (int) (days / 10); i++)
			times += (10 * 60 * 60 * 1000 * 24);

		times += ((days % 10) * 60 * 60 * 1000 * 24);

		Date dret = new Date(times); // 有问题

		return dret;
	}

	/**
	 * 日期按天递减
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date getDateMinDay(Date date, long days) {
		if (date == null) {
			return null;
		}

		long times = date.getTime();

		for (int i = 0; i < (int) (days / 10); i++)
			times -= (10 * 60 * 60 * 1000 * 24);

		times -= ((days % 10) * 60 * 60 * 1000 * 24);

		Date dret = new Date(times); // 有问题

		return dret;
	}

	/**
	 * 日期按月累加
	 * 
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date getDateAddMonth(Date date, int months) {
		if (date == null) {
			return null;
		}

		cal.setTime(new Date(date.getTime()));

		int month = cal.get(Calendar.MONTH);
		cal.set(Calendar.MONTH, month + months);

		return new Date(cal.getTime().getTime());
	}

	/**
	 * 日期按月递减
	 * 
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date getDateMinMonth(Date date, int months) {
		if (date == null) {
			return null;
		}

		cal.setTime(new Date(date.getTime()));

		int month = cal.get(Calendar.MONTH);
		cal.set(Calendar.MONTH, month - months);

		return new Date(cal.getTime().getTime());
	}

	/**
	 * 设置当前日期 今年的开始日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date setDayStartTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_YEAR, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}

	/**
	 * 根据年龄计算出生年月
	 * 
	 * @param age
	 * @return
	 */
	public static Date getBirthForAge(int age) {
		return getDateAddYear(setDayStartTime(new Date()), -age);
	}

	/**
	 * 日期按年累加
	 * 
	 * @param date
	 * @param years
	 * @return
	 */
	public static Date getDateAddYear(Date date, int years) {
		if (date == null) {
			return null;
		}

		cal.setTime(new Date(date.getTime()));

		int year = cal.get(Calendar.YEAR);
		cal.set(Calendar.YEAR, year + years);

		return new Date(cal.getTime().getTime());
	}

	/**
	 * 设置日期中的参数，可以对年、月、日、时、分、秒等进行设置
	 * 
	 * @param date
	 * @param param
	 * @param value
	 */
	public static void setDateParameter(Date date, int param, int value) {
		if (date == null) {
			return;
		}
		cal.setTime(new Date(date.getTime()));
		cal.set(param, value);
		date.setTime(cal.getTime().getTime());
	}

	/**
	 * 取日期中的参数，可以对年、月、日、时、分、秒等进行读取
	 * 
	 * @param date
	 * @param param
	 * @return
	 */
	public static int getDateParameter(Date date, int param) {
		if (date == null) {
			return -1;
		}
		cal.setTime(new Date(date.getTime()));

		return cal.get(param);
	}

	/**
	 * 得到时间中的hour 如2002--09-23 09:30:00 -->09:30
	 * 
	 * @param str
	 * @return
	 */
	public static String getHours(String str) {
		if (str != null) {
			String tmpStr = str.trim();
			int pos = tmpStr.lastIndexOf(":");
			str = str.substring(pos - 5, pos);

			return str;
		} else {
			return null;
		}
	}

	public static Collection<String> getAllYearForDay(String yy, String mm, Collection<String> incol, int start,
			int len, String op) {
		Collection<String> col = null;

		if ((incol != null) && (incol.size() > 0)) {
			col = new ArrayList<String>();

			java.util.Iterator<String> it = incol.iterator();

			while (it.hasNext()) {
				String whichday = (String) it.next();

				if (whichday.length() == 1) {
					whichday = "0" + whichday;
				}

				if (len > 0) {
					int curryear = start;

					for (int i = 1; i <= len; i++) {
						curryear++;

						if (op.equals("Y")) {
							String sDay = curryear + "-" + mm + "-" + whichday;

							if ((mm.equals("02")) && (whichday.equals("29"))) {
								if ((((curryear % 4) == 0) && ((curryear % 100) != 0)) || ((curryear % 400) == 0)) {
									col.add(sDay);
								}
							} else {
								col.add(sDay);
							}
							// if(mm)
						} else {
							for (int j = 1; j <= 12; j++) {
								String msDay = null;

								if (j < 10) {
									msDay = curryear + "-0" + j + "-" + whichday;
								} else {
									msDay = curryear + "-" + j + "-" + whichday;
								}

								if ((mm.equals("02")) && (whichday.equals("29"))) {
									if ((((curryear % 4) == 0) && ((curryear % 100) != 0)) || ((curryear % 400) == 0)) {
										col.add(msDay);
									}
								} else {
									col.add(msDay);
								}
								// if(mm)
							}
						}
					}
					// for
				}
				// if(len)
			}
			// while
		}
		// incol

		return col;
	}

	public static long getDiffDate(Date newdate, Date olddate) {
		return getDateDays(newdate) - getDateDays(olddate);
	}

	public static Collection<Object> getAllWeekForDay(String yy, String mm, Collection<String> incol, int start, int len) {
		Collection<Object> col = null;
		String max = "2010-12-31";
		long lMax = toDate(max).getTime();
		long span = 7 * 24 * 60 * 60 * 1000;

		if ((incol != null) && (incol.size() > 0)) {
			col = new ArrayList<Object>();

			java.util.Iterator<String> weekit = incol.iterator();

			while (weekit.hasNext()) {
				String strDay = (String) weekit.next();

				if (strDay.length() == 1) {
					strDay = "0" + strDay;
				}

				strDay = yy + "-" + mm + "-" + strDay;

				Date date = toDate(strDay);
				long tt = date.getTime();
				Timestamp stamp = new Timestamp(tt);
				col.add(stamp);

				while (tt <= lMax) {
					tt = tt + span;
					stamp = new Timestamp(tt);
					col.add(stamp);
				}
			}
		}

		return col;
	}

	/**
	 * 判断是否是闰年. 输入: year = 给出的 1582 年以后的年份. 输出: TRUE 是闰年, FALSE 不是.
	 * 
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		/*
		 * 能被100整除, 不能被400整除的年份, 不是闰年. 能被100整除, 也能被400整除的年份, 是闰年.
		 */
		if ((year % 100) == 0) {
			return ((year % 400) == 0);
		}

		/* 不能被100整除, 能被4整除的年份是闰年. */
		return ((year & 4) == 0);
	}

	/**
	 * 得到下一月的这一天;比如：当天2015-5-5，则结果为2015-6-5
	 * 
	 * @param date
	 * @return
	 */
	public static Date getNextMonthFirstDate(Date date) {
		Date tmpdate = getMonthFirstDate(date);
		cal.setTime(tmpdate);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DATE, 1);
		return new Date(cal.getTime().getTime());
	}

	/**
	 * 得到当前月的 第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthFirstDate(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDate = ca.getTime();
		return firstDate;
	}

	/**
	 * 月的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthLastDate(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.MONTH, 1);
		ca.set(Calendar.DAY_OF_MONTH, 0);
		Date lastDate = ca.getTime();
		return lastDate;

	}

	/**
	 * 指定天数以后的日期
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date getDateDiff(Date date, int day) {
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, day);
		return cal.getTime();
	}

	/**
	 * 当前日期，时间转换为长整型 作用用来生成随机数
	 *
	 * @return 长整型数字
	 */
	public static long getNowDateTimeToLong() {
		return cal.getTimeInMillis();
	}

	/**
	 * 获得默认的 date pattern
	 */
	public static String getDatePattern() {
		return defaultDatePattern;
	}

	public static String getDateTimePattern() {
		return DateUtils.getDatePattern() + " HH:mm:ss.S";
	}

	/**
	 * 返回预设Format的当前日期字符串
	 */
	public static String getToday() {
		Date today = new Date();
		return format(today);
	}

	/**
	 * 使用预设Format格式化Date成字符串
	 */
	public static String format(Date date) {
		return date == null ? "" : format(date, getDatePattern());
	}

	/**
	 * 使用参数Format格式化Date成字符串
	 */
	public static String format(Date date, String pattern) {
		return date == null ? "" : new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 使用预设格式将字符串转为Date
	 */
	public static Date parse(String strDate) throws ParseException {
		return StringUtils.isBlank(strDate) ? null : parse(strDate, getDatePattern());
	}

	/**
	 * 使用参数Format将字符串转为Date
	 */
	public static Date parse(String strDate, String pattern) throws ParseException {
		return StringUtils.isBlank(strDate) ? null : new SimpleDateFormat(pattern).parse(strDate);
	}

	/**
	 * 在日期上增加数个整月
	 */
	public static Date addMonth(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}

	/**
	 * 在日期上增加数个整天
	 */
	public static Date addDay(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, n);
		return cal.getTime();
	}

	/**
	 * 在日期上增加数个小时
	 */
	public static Date addHour(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, n);
		return cal.getTime();
	}

	/**
	 * 在日期上增加数个分钟
	 */
	public static Date addMinute(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, n);
		return cal.getTime();
	}

	/**
	 * 得到两个日期时间的差额
	 */
	public static long betDate(Date date, Date otherDate) {
		return date.getTime() - otherDate.getTime();
	}

	/**
	 * 以年，月，日来构造一个日子
	 */
	public static Date makeDate(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1, year);
		calendar.set(2, month - 1);
		calendar.set(5, day);
		calendar.set(11, 0);
		calendar.set(12, 0);
		calendar.set(13, 0);
		calendar.set(14, 0);
		return calendar.getTime();
	}

	/**
	 * 以年，月，日，小时，分钟，秒来构造一个日子
	 */
	public static Date makeDate(int year, int month, int day, int hour, int minute, int second) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1, year);
		calendar.set(2, month - 1);
		calendar.set(5, day);
		calendar.set(11, hour);
		calendar.set(12, minute);
		calendar.set(13, second);
		calendar.set(14, 0);
		return calendar.getTime();
	}

	/**
	 * 返回指定日期是哪一月
	 */
	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(2) + 1;
	}

	/**
	 * 返回指定日期是哪一年
	 */
	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(1);
	}

	/**
	 * 返回指定日期是哪一年
	 */
	public static int getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(5);
	}

	/**
	 * 返回指定日期是周几
	 */
	public static int getWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int week = calendar.get(Calendar.DAY_OF_WEEK);

		if (week == 1)
			return 7;
		else
			return week - 1;

	}

	/**
	 * 返回指定日期是几点
	 * 
	 * @param date
	 * @return
	 */
	public static int getHour(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);

	}

	/**
	 * 获得更定日期所在月的最后一天,可能29,30,31,28等
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDateOfMonth(Date date) {
		int year = DateUtils.getYear(date);
		int month = DateUtils.getMonth(date);
		return DateUtils.addDay(DateUtils.addMonth(DateUtils.makeDate(year, month, 1), 1), -1);

	}

	/**
	 * 获得指定日期所在月的的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDateOfMonth(Date date) {
		int year = DateUtils.getYear(date);
		int month = DateUtils.getMonth(date);
		return DateUtils.makeDate(year, month, 1);
	}

	/**
	 * 先得到指定日期所在周,再得到当周指定周几的日期,resultWeek用于指定周几,取值范围1-7
	 * 
	 * @param date
	 * @param resultWeek
	 * @return
	 */
	public static Date getDayOfWeek(Date date, int resultWeek) {
		if (resultWeek < 1 || resultWeek > 7)
			throw new IllegalArgumentException("resultWeek must in 1-7");
		int week = DateUtils.getWeek(date);
		return DateUtils.addDay(date, resultWeek - week);
	}

	/**
	 * 根据年龄取得对于的出生时间
	 * 
	 * @param age
	 * @return
	 */
	public static String getBirthday(String age) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, -Integer.parseInt(age));
		SimpleDateFormat format = new SimpleDateFormat(defaultDatePattern);
		return format.format(c.getTime());
	}

	/**
	 * 根据生日得到年龄
	 * 
	 * @param year
	 * @return
	 */
	public static Integer getAge(String year) {
		Calendar c = Calendar.getInstance();
		if (year == null) {
			return 20;
		}
		year = year.substring(0, 4);
		Integer age = c.get(Calendar.YEAR) - Integer.parseInt(year);
		return age;
	}

	/**
	 * 年龄转化为：integer
	 * 
	 * @param age
	 * @return
	 */
	public static Integer getBirthday1(Integer age) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, -age);
		c.set(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_YEAR, 0);

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return Integer.parseInt(format.format(c.getTime()));
	}

	/**
	 * 得到以当前时间为基数的序列号，方法为同步安全的
	 * 
	 * @return
	 */
	public synchronized static Long getSerialNum() {
		Long result = new Long(DateUtils.format(new Date(), "yyyyMMddHHmmss"));
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// logger.error(e);
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 得到以当前时间(精确到毫秒)为基数的序列号，方法为同步安全的
	 * 
	 * @return
	 */
	public synchronized static Long getMsSerialNum() {
		Long result = new Long(DateUtils.format(new Date(), "yyyyMMddHHmmssSSS"));
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// logger.error(e);
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 日期当天的最后一秒
	 * 
	 * @param endDate
	 * @return
	 */
	public static Date endOfDate(Date endDate) {
		String dateStr = formatDateTOymd(endDate) + " 23:59:59";
		Date endOfDate = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(FM_DATE_AND_TIME);
			endOfDate = dateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return endOfDate;
	}

	/**
	 * 日期当天的开始
	 * 
	 * @param startDate
	 * @return
	 */
	public static Date startOfDate(Date startDate) {
		String dateStr = formatDateTOymd(startDate) + " 00:00:00";
		Date endOfDate = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(FM_DATE_AND_TIME);
			endOfDate = dateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return endOfDate;
	}

	/**
	 * 获取日期当天开始时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDayStartTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 获取日期当天结尾时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDayEndTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 将时间戳类型转换为时间
	 * 
	 * @param mills
	 * @param format
	 * @return
	 */
	public static Date parseTimeStamp(long mills, String format) {
		SimpleDateFormat sf = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sf.parse(sf.format(mills));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 计算两个日期时间之间的天数(如："2015-12-21"到 "2016-1-20"返回30天)
	 * 
	 * @param beginDay
	 * @param endDay
	 * @return
	 */
	public static int getBetweenDays(String beginDate, String endDate) {
		int betweenDays = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(beginDate));
			long time1 = cal.getTimeInMillis();
			cal.setTime(sdf.parse(endDate));
			long time2 = cal.getTimeInMillis();
			long between_days = (time2 - time1) / (1000 * 3600 * 24);

			betweenDays = Integer.parseInt(String.valueOf(between_days));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return betweenDays;
	}

	/**
	 * 计算两个日期时间之间的天数(如："2015-12-21"到 "2016-1-20"返回30天)
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static int getBetweenDays(Date beginDate, Date endDate) {
		int betweenDays = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			beginDate = sdf.parse(sdf.format(beginDate));
			endDate = sdf.parse(sdf.format(endDate));
			Calendar cal = Calendar.getInstance();
			cal.setTime(beginDate);
			long time1 = cal.getTimeInMillis();
			cal.setTime(endDate);
			long time2 = cal.getTimeInMillis();
			long between_days = (time2 - time1) / (1000 * 3600 * 24);
			betweenDays = Integer.parseInt(String.valueOf(between_days));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return betweenDays;
	}

	/**
	 * 手机版消息列表时间格式显示转换方法
	 * 
	 * @param date
	 * @param i //i=0表示第一条数据
	 * @return
	 */
	public static String formatDateStr(Date date, int i) {
		SimpleDateFormat ydf1 = new SimpleDateFormat("yyyy年M月d日");
		SimpleDateFormat ydf2 = new SimpleDateFormat("yyyy年M月dd日");
		SimpleDateFormat ydf3 = new SimpleDateFormat("yyyy年MM月d日");
		SimpleDateFormat ydf4 = new SimpleDateFormat("yyyy年MM月dd日");
		SimpleDateFormat df1 = new SimpleDateFormat("M月d日");
		SimpleDateFormat df2 = new SimpleDateFormat("M月dd日");
		SimpleDateFormat df3 = new SimpleDateFormat("MM月d日");
		SimpleDateFormat df4 = new SimpleDateFormat("MM月dd日");

		Calendar today = Calendar.getInstance();
		Calendar calDate = Calendar.getInstance();
		Calendar currCal = Calendar.getInstance();

		today.setTime(new Date());
		today.set(Calendar.HOUR, 23);
		today.set(Calendar.MINUTE, 59);
		today.set(Calendar.SECOND, 59);

		calDate.setTime(date);

		currCal.setTime(new Date());

		long intervalMilli = today.getTimeInMillis() - calDate.getTimeInMillis();
		int itm = (int) (intervalMilli / (24 * 60 * 60 * 1000));
		switch (itm) {
			case 0:
				if (i == 0) {
					long currMilli = currCal.getTimeInMillis() - calDate.getTimeInMillis();
					if (currMilli < 60 * 1000) {
						return "1分钟前";
					}
					if (currMilli < 60 * 60 * 1000) {
						return (int) (currMilli / (60 * 1000)) + "分钟前";
					}
					return (int) (currMilli / (60 * 60 * 1000)) + "小时前";
				}
				if (calDate.get(Calendar.MINUTE) > 9) {
					return calDate.get(Calendar.HOUR_OF_DAY) + ":" + calDate.get(Calendar.MINUTE);
				} else {
					return calDate.get(Calendar.HOUR_OF_DAY) + ":0" + calDate.get(Calendar.MINUTE);
				}
			case 1:
				if (calDate.get(Calendar.MINUTE) > 9) {
					return "昨天 " + calDate.get(Calendar.HOUR_OF_DAY) + ":" + calDate.get(Calendar.MINUTE);
				} else {
					return "昨天 " + calDate.get(Calendar.HOUR_OF_DAY) + ":0" + calDate.get(Calendar.MINUTE);
				}
			case 2:
				return "前天 " + calDate.get(Calendar.HOUR_OF_DAY) + ":" + calDate.get(Calendar.MINUTE);
			default: {
				if (currCal.get(Calendar.YEAR) != calDate.get(Calendar.YEAR)) {
					if (calDate.get(Calendar.MONTH) + 1 < 10 && calDate.get(Calendar.DAY_OF_MONTH) < 10) {
						return ydf1.format(calDate.getTime());
					}
					if (calDate.get(Calendar.MONTH) + 1 < 10 && calDate.get(Calendar.DAY_OF_MONTH) >= 10) {
						return ydf2.format(calDate.getTime());
					}
					if (calDate.get(Calendar.MONTH) + 1 >= 10 && calDate.get(Calendar.DAY_OF_MONTH) < 10) {
						return ydf3.format(calDate.getTime());
					}
					return ydf4.format(calDate.getTime());
				} else {
					if (calDate.get(Calendar.MONTH) + 1 < 10 && calDate.get(Calendar.DAY_OF_MONTH) < 10) {
						return df1.format(calDate.getTime());
					}
					if (calDate.get(Calendar.MONTH) + 1 < 10 && calDate.get(Calendar.DAY_OF_MONTH) >= 10) {
						return df2.format(calDate.getTime());
					}
					if (calDate.get(Calendar.MONTH) + 1 >= 10 && calDate.get(Calendar.DAY_OF_MONTH) < 10) {
						return df3.format(calDate.getTime());
					}
					return df4.format(calDate.getTime());
				}
			}
		}
	}

	/**
	 * 获取自定义（可自定义输入时间，得到自定义时间（如：可以获取当前时间前/后几天/几月/几年））
	 * 
	 * @param curDate 原始时间
	 * @param tag 自定义数字
	 * @param calendarType 时间类型（如，年，月，日）
	 * @return
	 */
	public static Date getCustomDate(Date curDate, int tag, int calendarType) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(curDate);
		calendar.add(calendarType, tag);
		return calendar.getTime();
	}

	/**
	 * 格式化日期，返回时间字符串
	 * 
	 * @param curDate
	 * @param patten
	 * @return
	 */
	public static String getCustomFmtDate(Date curDate, String patten) {
		SimpleDateFormat format = new SimpleDateFormat(patten);
		return format.format(curDate);
	}

	/**
	 * 格式化时间字符串，返回日期
	 * 
	 * @param curDate
	 * @param patten
	 * @return
	 */
	public static Date getCustomFmtDate(String curDate, String patten) {
		SimpleDateFormat format = new SimpleDateFormat(patten);
		Date date = null;
		try {
			date = format.parse(curDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * * 用于获取指定日期之间的所有日期
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static List<Date> getBetweenDates(Date beginDate, Date endDate) {
		List<Date> dates = new ArrayList<Date>();
		if (beginDate == null || endDate == null) {
			return dates;
		}
		Calendar calStart = Calendar.getInstance();
		Calendar calEnd = Calendar.getInstance();
		calStart.setTime(beginDate);
		calEnd.setTime(endDate);
		while (calStart.compareTo(calEnd) != 1) {
			dates.add(calStart.getTime());
			calStart.add(Calendar.DATE, 1);
		}
		if (endDate != null) {
			dates.add(calEnd.getTime());
		}
		return dates;
	}

	/**
	 * * 用于获取指定日期之间的所有日期【修改】
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static List<Date> getBetweenDate(Date beginDate, Date endDate) {
		List<Date> dates = new ArrayList<Date>();
		if (beginDate == null || endDate == null) {
			return dates;
		}
		Calendar calStart = Calendar.getInstance();
		Calendar calEnd = Calendar.getInstance();
		calStart.setTime(beginDate);
		calEnd.setTime(endDate);
		while (calStart.compareTo(calEnd) != 1) {
			dates.add(calStart.getTime());
			calStart.add(Calendar.DATE, 1);
		}
		return dates;
	}

	/**
	 * 获取当前月的开始时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthFirstTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 获取当前月的结束时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthLastTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 0);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 根据当前月，获取指定月的开始时间
	 * 
	 * @param month
	 * @return
	 */
	public static Date getDynamicMonthFirstTime(int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
		calendar.add(Calendar.MONTH, month);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 根据当前月，获取指定月的结束时间
	 * 
	 * @param month
	 * @return
	 */
	public static Date getDynamicMonthLastTime(int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
		calendar.add(Calendar.DATE, -1);
		calendar.add(Calendar.MONTH, month + 1);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 安卓获取时间差方法 panda
	 */
	public static String getTimesToNow(String date) {
		SimpleDateFormat format = new SimpleDateFormat(FM_DATE_AND_TIME);
		String now = format.format(new Date());
		String returnText = null;
		try {
			long from = format.parse(date).getTime();
			long to = format.parse(now).getTime();
			int days = (int) ((to - from) / (1000 * 60 * 60 * 24));
			if (days == 0) {// 一天以内，以分钟或者小时显示
				int hours = (int) ((to - from) / (1000 * 60 * 60));
				if (hours == 0) {
					int minutes = (int) ((to - from) / (1000 * 60));
					if (minutes == 0) {
						returnText = "刚刚";
					} else {
						returnText = minutes + "分钟前";
					}
				} else {
					returnText = hours + "小时前";
				}
			} else if (days == 1) {
				returnText = "昨天";
			} else {
				returnText = days + "天前";
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return returnText;
	}

	public static void main(String[] args) throws ParseException {
	}

}
