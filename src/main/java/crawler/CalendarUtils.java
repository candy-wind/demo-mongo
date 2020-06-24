package crawler;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * lx 常用日历操作辅助类
 * 
 */
public class CalendarUtils {
	private static  int weeks = 0;// 用来全局控制 上一周，本周，下一周的周数变化
	private static int maxDate; // 一月最大天数
	private static int maxYear; // 一年最大天数

	 

	/**
	 * 
	 * 一年以前的日期
	 * 
	 * @param date
	 * @param type
	 * @param value
	 * @param dateformat
	 * @return
	 */
	public static String getOneYearAgo(Date date, int type, int value, String dateformat) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(type, value);
		calendar.set(Calendar.DATE, 1);// 设为当前月的1号
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);// 可以方便地修改日期格式

		return dateFormat.format(calendar.getTime());
	}


	/**
	 * 得到两个日期之间所有日期 格式 yyyy-MM-dd
	 * 
	 * @param startDat
	 * @param endDat
	 * @return
	 */
	public static List<String> getBydaysBetweenDate(String startDat, String endDat) {
		List<String> dateList = new ArrayList<>();
		try {
			Calendar startCalendar = Calendar.getInstance();
			Calendar endCalendar = Calendar.getInstance();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = df.parse(startDat);
			startCalendar.setTime(startDate);
			Date endDate = df.parse(endDat);
			endCalendar.setTime(endDate);
			while (true) {
				startCalendar.add(Calendar.DAY_OF_MONTH, 1);
				if (startCalendar.getTimeInMillis() <= endCalendar.getTimeInMillis()) {
					dateList.add(df.format(startCalendar.getTime()) + "");
				} else {
					break;
				}
			}
		} catch (ParseException e) {
		}
		return dateList;
	}

	/**
	 * 
	 * yyyy.mm.dd
	 * 
	 * @return
	 */
	public static String getYMD() {
		
		Calendar cal = Calendar.getInstance();
		int y = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH) + 1;
		int d = cal.get(Calendar.DATE);
		if ( Integer.toString(m).length() < 2) {
			String moth = "0" + m;
			return y + "." + moth + "." + d;
		} else {
			return y + "." + m + "." + d;
		}
		
	}

	/**
	 * 获得当前年份
	 * 
	 * @return
	 */
	public static int getYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * 获得当前日
	 * 
	 * @return
	 */
	public static String getDay() {
		if (Calendar.getInstance().get(Calendar.DATE) <= 9) {
			return  "0" + Calendar.getInstance().get(Calendar.DATE);
		} else {
			return Integer.toString(Calendar.getInstance().get(Calendar.DATE));
		}

	}

	/**
	 * 获得当前月份
	 * 
	 * @return
	 */
	public static String getMonth() {
		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		if (month <= 9) {
			return  "0" + month;
		} else {
			return   Integer.toString(month);
		}

	}

	/**
	 * 获得今天在本年的第几天
	 * 
	 * @return
	 */
	public static int getDayOfYear() {
		return Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 获得今天在本月的第几天(获得当前日)
	 * 
	 * @return
	 */
	public static int getDayOfMonth() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获得今天在本周的第几天
	 * 
	 * @return
	 */
	public static int getDayOfWeek() {
		return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获得小时
	 * 
	 * @return
	 */
	public static String getNowHour() {
		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat fmt = new SimpleDateFormat("HH");// 格式大小写有区别
		return fmt.format(rightNow.getTime());
	}

	/**
	 * 获得yyyyMMddHH
	 * 
	 * @return
	 */
	public static String getYYYYMMDDHour() {
		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHH");// 格式大小写有区别
		return fmt.format(rightNow.getTime());
	}

	/**
	 * 获得getYYYYMMDDHHssSSS
	 * 
	 * @return
	 */
	public static String getYYYYMMDDHHssSSS() {
		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHssSSS");// 格式大小写有区别
		String sysDatetime = fmt.format(rightNow.getTime());
		Random random = new Random();

		sysDatetime += addZero(Integer.toString(random.nextInt(1000)), 3);

		return sysDatetime;
	}

	private static String addZero(String input, int count) {

		if (input.length() >= count) {
			return input;
		} else {
			String inputs =input;
			for (int i = input.length(); i < count; i++) {
				  inputs = "0" + input;
			}
			return  inputs;
		}
	}

	/**
	 * 获得今天是这个月的第几周
	 * 
	 * @return
	 */
	public static int getWeekOfMonth() {
		return Calendar.getInstance().get(Calendar.DAY_OF_WEEK_IN_MONTH);
	}

	/**
	 * 
	 * 获得N天的日期
	 * 
	 * 
	 * @param num
	 * @return
	 */
	public static Date getTimeDateNext(int num) {

		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_WEEK, num);

		return now.getTime();
	}

	/**
	 * 获得半年后的日期
	 * 
	 * @return
	 */
	public static Date getTimeYearNext() {
		Calendar.getInstance().add(Calendar.DAY_OF_YEAR, 183);
		return Calendar.getInstance().getTime();
	}

	/**
	 * 将日期转换成字符串
	 * 
	 * @param dateTime
	 * @return
	 */
	public static String convertDateToString(Date dateTime) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(dateTime);
	}

	/**
	 * 得到二个日期间的间隔天数
	 * 
	 * @param sj1
	 * @param sj2
	 * @return
	 */
	public static String getTwoDay(String sj1, String sj2) {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		long day = 0;
		try {
			java.util.Date date = myFormatter.parse(sj1);
			java.util.Date mydate = myFormatter.parse(sj2);
			day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			return "";
		}
		return Long.toString(day);
	}

	/**
	 * 根据一个日期，返回是星期几的字符串
	 *
	 * @param sdate
	 * @return
	 */
	public static String getWeek(String sdate) {
		// 再转换为时间
		Date date = CalendarUtils.strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// hour中存的就是星期几了，其范围 1~7
		// 1=星期日 7=星期六，其他类推
		return new SimpleDateFormat("EEEE").format(c.getTime());
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
		return formatter.parse(strDate, pos);
	}

	/**
	 * 两个时间之间的天数
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDays(String date1, String date2) {
		if (date1 == null || date1.isEmpty())
			return 0;
		if (date2 == null || date1.isEmpty())
			return 0;
		// 转换为标准时间
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		java.util.Date mydate = null;
		try {
			date = myFormatter.parse(date1);
			mydate = myFormatter.parse(date2);
		} catch (Exception e) {

		}
		if (date != null) {
			return (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		}
		return 0;

	}

	/**
	 * 计算当月最后一天,返回字符串
	 *
	 * @return
	 */
	public static String getDefaultDay() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
		lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天

		return sdf.format(lastDate.getTime());
	}

	/**
	 * 上月第一天
	 *
	 * @return
	 */
	public static String getPreviousMonthFirst() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, -1);// 减一个月，变为下月的1号
		// lastDate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天

		return sdf.format(lastDate.getTime());
	}

	/**
	 * 获取当月第一天
	 *
	 * @return
	 */
	public static String getFirstDayOfMonth() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		return sdf.format(lastDate.getTime());
	}

	/**
	 * 获得本周星期日的日期
	 *
	 * @return
	 */
	public static String getCurrentWeekday() {
		weeks = 0;
		int mondayPlus =  getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
		Date monday = currentDate.getTime();

		DateFormat df = DateFormat.getDateInstance();
		return df.format(monday);
	}

	/**
	 * 获取当天时间
	 *
	 * @param dateformat
	 * @return
	 */
	public static String getNowTime(String dateformat) {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);// 可以方便地修改日期格式
		return dateFormat.format(now);
	}

	/**
	 * 获得当前日期与本周日相差的天数
	 *
	 * @return
	 */
	private static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
		if (dayOfWeek == 1) {
			return 0;
		} else {
			return 1 - dayOfWeek;
		}
	}

	/**
	 * 获得本周一的日期
	 *
	 * @return
	 */
	public static String getMondayOFWeek() {
		weeks = 0;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();

		DateFormat df = DateFormat.getDateInstance();
		return df.format(monday);
	}

	/**
	 * 获得相应周的周六的日期
	 *
	 * @return
	 */
	public String getSaturday() {
		int mondayPlus =  getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks + 6);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		return df.format(monday);
	}

	/**
	 * 获得上周星期日的日期
	 *
	 * @return
	 */
	public static String getPreviousWeekSunday() {
		weeks = 0;
		weeks--;
		int mondayPlus =  getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + weeks);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		return df.format(monday);
	}

	/**
	 * 获得上周星期一的日期
	 *
	 * @return
	 */
	public static String getPreviousWeekday() {
		weeks--;
		int mondayPlus =  getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		return df.format(monday);
	}

	/**
	 * 获得下周星期一的日期
	 */
	public static String getNextMonday() {
		weeks++;
		int mondayPlus =  getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		return df.format(monday);
	}

	/**
	 * 日期前一天
	 *
	 * @param nowDate
	 * @return
	 */
	public static String getBeforeDay(String nowDate) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format.parse(nowDate);
		} catch (ParseException e) {
		}
		if(date !=null){

			return format.format(new java.util.Date(date.getTime() - 24 * 3600 * 1000));
		}
		return nowDate;

	}

	/**
	 * 获得下周星期日的日期
	 */
	public static String getNextSunday() {

		int mondayPlus =   getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 + 6);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		return df.format(monday);
	}

	@SuppressWarnings("unused")
	private static int getMonthPlus() {
		Calendar cd = Calendar.getInstance();
		int monthOfNumber = cd.get(Calendar.DAY_OF_MONTH);
		cd.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		cd.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		maxDate = cd.get(Calendar.DATE);
		if (monthOfNumber == 1) {
			return -maxDate;
		} else {
			return 1 - monthOfNumber;
		}
	}

	/**
	 * 获得上月最后一天的日期
	 * 
	 * @return
	 */
	public static String getPreviousMonthEnd() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, -1);// 减一个月
		lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		lastDate.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天
		return sdf.format(lastDate.getTime());
	}

	/**
	 * 获得下个月第一天的日期
	 * 
	 * @return
	 */
	public static String getNextMonthFirst() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, 1);// 减一个月
		lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		return sdf.format(lastDate.getTime());
	}

	/**
	 * 获得下个月最后一天的日期
	 * 
	 * @return
	 */
	public static String getNextMonthEnd() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, 1);// 加一个月
		lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		lastDate.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天
		return sdf.format(lastDate.getTime());
	}

	/**
	 * 获得明年最后一天的日期
	 * 
	 * @return
	 */
	public static String getNextYearEnd() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.YEAR, 1);// 加一个年
		lastDate.set(Calendar.DAY_OF_YEAR, 1);
		lastDate.roll(Calendar.DAY_OF_YEAR, -1);
		return sdf.format(lastDate.getTime());
	}

	/**
	 * 获得明年第一天的日期
	 * 
	 * @return
	 */
	public static String getNextYearFirst() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.YEAR, 1);// 加一个年
		lastDate.set(Calendar.DAY_OF_YEAR, 1);
		return sdf.format(lastDate.getTime());

	}

	/**
	 * 获得本年有多少天
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	private static int getMaxYear() {
		Calendar cd = Calendar.getInstance();
		cd.set(Calendar.DAY_OF_YEAR, 1);// 把日期设为当年第一天
		cd.roll(Calendar.DAY_OF_YEAR, -1);// 把日期回滚一天。
		return cd.get(Calendar.DAY_OF_YEAR);
	}

	private static int getYearPlus() {
		Calendar cd = Calendar.getInstance();
		int yearOfNumber = cd.get(Calendar.DAY_OF_YEAR);// 获得当天是一年中的第几天
		cd.set(Calendar.DAY_OF_YEAR, 1);// 把日期设为当年第一天
		cd.roll(Calendar.DAY_OF_YEAR, -1);// 把日期回滚一天。
		if (yearOfNumber == 1) {
			return -cd.get(Calendar.DAY_OF_YEAR);
		} else {
			return 1 - yearOfNumber;
		}
	}

	/**
	 * 获得本年第一天的日期
	 * 
	 * @return
	 */
	public static String getCurrentYearFirst() {
		int yearPlus =  getYearPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, yearPlus);
		Date yearDay = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		return df.format(yearDay);
	}

	// 获得本年最后一天的日期 *
	public static String getCurrentYearEnd() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
		String years = dateFormat.format(date);
		return years + "-12-31";
	}

	// 获得上年第一天的日期 *
	public static String getPreviousYearFirst() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
		String years = dateFormat.format(date);
		int yearsCalue = Integer.parseInt(years);
		yearsCalue --;
		return yearsCalue + "-1-1";
	}

	// 获得上年最后一天的日期
	public static String getPreviousYearEnd() {
		weeks--;
		int yearPlus =  getYearPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, yearPlus + maxYear * weeks + (maxYear - 1));
		Date yearDay = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		return df.format(yearDay);
	}

	/**
	 * 是否闰年
	 * 
	 * @param year
	 *            年
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}

	/**
	 * 是否闰年
	 * 
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear2(int year) {
		return new GregorianCalendar().isLeapYear(year);
	}
}
