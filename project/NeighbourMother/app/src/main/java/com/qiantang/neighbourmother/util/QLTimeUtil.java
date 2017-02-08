package com.qiantang.neighbourmother.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class QLTimeUtil {

	public static final String TIME_MODEL="yyyy-MM-dd";
	public static final String TIME_MODEL_CHINA="yyyy年MM月dd日";
	public static final String TIME_MODEL_1="yyyy年MM月dd日 HH:mm";
	public static final String TIME_MODEL_2="yyyy-MM-dd HH:mm";
	public static final String TIME_MODEL_TIME="HH:mm";


	public static String getQuLiangTime(long mil) {
		Calendar cale = Calendar.getInstance();
		cale.setTime(new Date(mil));
		String str = "";
		long diffDay = 0L;

		Calendar curDate = new GregorianCalendar();
		long curTime = System.currentTimeMillis();
		curDate.setTime(new Date(curTime));
		curDate.set(Calendar.HOUR_OF_DAY, 23);
		curDate.set(Calendar.MINUTE, 59);
		curDate.set(Calendar.SECOND, 59);
		curDate.set(Calendar.MILLISECOND, 999);

		diffDay = (curDate.getTimeInMillis() - mil) / 86400000L;
		String time = getStringTime(mil, "HH:mm");
		if (diffDay < 0 || diffDay > 2) {
			str = getStringTime(mil, "yyyy-MM-dd");
			// 显示原始时间
		} else if (diffDay > 1) {
			str = "前天" + " " + time;
		} else if (diffDay > 0) {
			str = "昨天" + " " + time;
		} else {
			str = getStringTime(mil, "yyyy年MM月dd日 HH:mm");
			System.out.println("kankan:" + str);
			diffDay = (curTime - mil) / 1000L;
			System.out.println("diffDay:" + diffDay);
			if (diffDay > 7200) {
				str = "今天" + " " + time;
			} else if (diffDay > 60) {
				str = getHoursMin(diffDay) + "前";
			} else {
				str = "刚刚";
			}
		}

		return str;
	}


	public static String getBbsListTime(long mil) {
		Calendar cale = Calendar.getInstance();


		String str = "";
		long diffDay = 0L;

		Calendar curDate = new GregorianCalendar();
		long curTime = System.currentTimeMillis();
		curDate.setTime(new Date(curTime));
		curDate.set(Calendar.HOUR_OF_DAY, 23);
		curDate.set(Calendar.MINUTE, 59);
		curDate.set(Calendar.SECOND, 59);
		curDate.set(Calendar.MILLISECOND, 999);

		diffDay = (curDate.getTimeInMillis() - mil) / 86400000L;
		String time = getStringTime(mil, "HH:mm");
		if (diffDay < 0 || diffDay > 2) {
			str=getYear(mil);

			// 显示原始时间
		} else if (diffDay > 1) {
			str = "前天";
		} else if (diffDay > 0) {
			str = "昨天";
		} else {
			str = getStringTime(mil, "yyyy-MM-dd HH:mm");
			diffDay = (curTime - mil) / 1000L;
			System.out.println("diffDay:" + diffDay);
			if (diffDay > 7200) {
				//str = "今天";
				str = getHoursMin(diffDay) + "前";
			} else if (diffDay > 60) {
				str = getHoursMin(diffDay) + "前";
			} else {
				str = "刚刚";
			}
		}
		return str;
	}


	private static String getHoursMin(long miln) {
		String str = "";
		int hours = (int) miln / 3600;
		int hours_yu = (int) miln % 3600;
		// int hours=(int)hours_yu/60;
		if (hours > 0) {
			str = hours + "小时";
			return str;
		}
		if (hours_yu > 0) {
			str = str + hours_yu / 60 + "分钟";
		}

		return str;
	}
	/**
	 * 将字符串转为时间戳 "yyyy年MM月dd日HH时mm分ss秒"
	 * 
	 * @param user_time
	 * @param model
	 * @return
	 */
	public static long getLongTime(String user_time, String model) {
		// "yyyy年MM月dd日HH时mm分ss秒"
		String re_time = null;
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(model);
		Date d = null;
		try {
			d = mSimpleDateFormat.parse(user_time);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long strTime = 0;
		if (d != null) {
			strTime = d.getTime();
		}
		return strTime;
	}

	/**
	 * 将时间戳转为字符串 "yyyy年MM月dd日HH时mm分ss秒"
	 * 
	 * @param lon
	 * @param model
	 * @return
	 */
	public static String getStringTime(Long lon, String model) {
		String re_StrTime = null;
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(model);
		re_StrTime = mSimpleDateFormat.format(new Date(lon));
		return re_StrTime;
	}


	private static String getYear(long time){
		String strTime=null;
		Calendar calendar=Calendar.getInstance();
		int curYear=calendar.get(Calendar.YEAR);
		calendar.setTimeInMillis(time);
		int year=calendar.get(Calendar.YEAR);
		if(curYear==year){
			strTime = getStringTime(time, "MM-dd");
		}else{
			strTime = getStringTime(time, "yyyy-MM-dd");
		}
		return strTime;
	}

}
