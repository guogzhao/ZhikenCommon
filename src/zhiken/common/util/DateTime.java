package zhiken.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateTime {
	private Date mDate;

	private DateTime(long timeMillis) {
		mDate = new Date(timeMillis);
	}

	public DateTime() {
		mDate = new Date(System.currentTimeMillis());
	}

	public static DateTime now() {
		return new DateTime();
	}

	public static DateTime from(DateTime datetime) {
		return new DateTime(datetime.toTimeMillis());
	}

	public static DateTime from(long timeMillis) {
		return new DateTime(timeMillis);
	}

	public static DateTime from(Date date) {
		return new DateTime(date.getTime());
	}

	public static DateTime from(String datetime, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = df.parse(datetime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return DateTime.from(date);
	}

	public static DateTime from(String datetime, SimpleDateFormat dateformat) {
		Date date = null;
		try {
			date = dateformat.parse(datetime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return DateTime.from(date);
	}

	// "EEE MMM dd HH:mm:ss zZ yyyy"
	public static DateTime fromEnglish(String datetime) {
		SimpleDateFormat dateformat = new SimpleDateFormat("EEE MMM dd HH:mm:ss 鏍兼灄灏兼不鏍囧噯鏃堕棿+0800 yyyy", Locale.ENGLISH);
		Date date = null;
		try {
			date = dateformat.parse(datetime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return DateTime.from(date);
	}

	public static DateTime from(String datetime) {
		return DateTime.from(datetime, mFormat);
	}

	public static DateTime from(int year, int monthOfYear, int dayOfMonth) {
		return DateTime.from(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth, "yyyy-MM-dd");
	}

	public long toTimeMillis() {
		return mDate.getTime();
	}

	private static String mFormat;
	private static SimpleDateFormat mSimpleDateFormat;
	static {
		// mFormat = "yyyy-MM-dd kk:mm:ss";
		mFormat = "yyyy-MM-dd HH:mm:ss";
		mSimpleDateFormat = new SimpleDateFormat(mFormat);
	}

	public String toString() {
		return mSimpleDateFormat.format(mDate);
	}

	public String toString(String format) {
		return new SimpleDateFormat(format).format(mDate);
	}

	public DateTime addDay(int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(mDate);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + day);
		return new DateTime(calendar.getTime().getTime());
	}

	public DateTime setYear(int year) {
		mDate.setYear(year - 1900);
		return this;
	}

	public int getYear() {
		// Calendar calendar = Calendar.getInstance();
		// calendar.setTime(mDate);
		// return calendar.get(Calendar.YEAR);

		return mDate.getYear() + 1900;
	}

	public DateTime setMonth(int month) {
		mDate.setMonth(month);
		return this;
	}

	public int getMonth() {
		// Calendar calendar = Calendar.getInstance();
		// calendar.setTime(mDate);
		// return calendar.get(Calendar.MONTH);
		return mDate.getMonth();
	}

	public DateTime setDay(int day) {
		mDate.setDate(day);
		return this;
	}

	public int getDay() {
		// Calendar calendar = Calendar.getInstance();
		// calendar.setTime(mDate);
		// return calendar.get(Calendar.DAY_OF_MONTH);
		return mDate.getDate();
	}

	public DateTime setHours(int hours) {
		mDate.setHours(hours);
		return this;
	}

	public int getHours() {
		return mDate.getHours();
		// Calendar calendar = Calendar.getInstance();
		// calendar.setTime(mDate);
		// return calendar.get(Calendar.HOUR_OF_DAY);
	}

	public DateTime setMinutes(int minutes) {
		mDate.setMinutes(minutes);
		return this;
	}

	public int getMinutes() {
		return mDate.getMinutes();
		// Calendar calendar = Calendar.getInstance();
		// calendar.setTime(mDate);
		// return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 
	 * @param timeMillis
	 * @return [0]：diff；[1]：hours；[2]：minutes；[3]：seconds；
	 */
	public static long[] getDateDiffForHour(long timeMillis) {
		long[] result = new long[4];
		{
			result[0] = timeMillis - DateTime.now().toTimeMillis();

			result[1] = (result[0] / (60 * 60 * 1000));

			result[2] = ((result[0] % (60 * 60 * 1000)) / (60 * 1000));

			result[3] = ((result[0] % (60 * 1000)) / 1000);
		}
		return result;
	}

	/**
	 * 获得自从创建时起到现在过了多久
	 * 
	 * @return [0]：diff；[1]：minutes；[2]：seconds；
	 */
	public long[] getMinuteSecondTime() {
		long[] result = new long[3];
		{
			result[0] = System.currentTimeMillis() - toTimeMillis();
			result[1] = result[0] / (60 * 1000);
			result[2] = result[0] % (60 * 1000) / 1000;
		}
		return result;
	}
}
