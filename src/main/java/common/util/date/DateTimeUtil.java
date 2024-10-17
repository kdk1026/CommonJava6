package common.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Java 8 이하: Joda Time 권장, Java 8 이상: JSR310 권장
 */
public class DateTimeUtil {
	
	private DateTimeUtil() {
		super();
	}
	
	private static final Logger logger = LoggerFactory.getLogger(DateTimeUtil.class);
	
	private static final String YYYYMMDD = "yyyyMMdd";
	private static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

	/**
	 * 현재 날짜 및 시간 반환
	 */
	public static class Today {
		
		private Today() {
			super();
		}
		
		/**
		 * 현재 날짜를 yyyyMMdd 형식의 String 타입으로 반환
		 * @return
		 */
		public static String getTodayString() {
			SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDD);
			return formatter.format(new Date());
		}

		/**
		 * 현재 날짜를 해당 포맷의 String 타입로 반환
		 * @param dateFormat
		 * @return
		 */
		public static String getTodayString(String dateFormat) {
			SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
			return formatter.format(new Date());
		}
		
		/**
		 * 현재 시간을 HHmmss 형식의 String 타입으로 반환
		 * @return
		 */
		public static String getCurrentTime() {
			SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
			return formatter.format(new Date());
		}
	}

	/**
	 * String 타입 형식의 포맷 변환
	 */
	public static class StringFormat {
		
		private StringFormat() {
			super();
		}
		
		/**
		 * yyyyMMdd 형식의 String 타입을 해당 포맷의 String 타입으로 반환
		 * @param strDate
		 * @param dateFormat
		 * @return
		 */
		public static String getStringDate(String strDate, String dateFormat) {
			Date date = null;
			SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDD);

			try {
				date = formatter.parse(strDate);
				formatter = new SimpleDateFormat(dateFormat);
			} catch (Exception e) {
				logger.error("getStringDate Exception", e);
			}
			return formatter.format(date);
		}
	}

	/**
	 * 타입 변환
	 */
	public static class Convert {
		
		private Convert() {
			super();
		}
		
		/**
		 * yyyyMMdd(HHmmss) 형식의 String 타입을 Date 타입으로 반환<br/>
		 * @param strDate
		 * @return
		 */
		public static Date getStringToDate(String strDate) {
			Date date = null;
			SimpleDateFormat formatter = null;

			try {
				if (strDate.length() == 14) {
					formatter = new SimpleDateFormat(YYYYMMDDHHMMSS);
					date = formatter.parse(strDate);
				} else {
					formatter = new SimpleDateFormat(YYYYMMDD);
					date = formatter.parse(strDate);
				}
			} catch (Exception e) {
				logger.error("getStringToDate Exception", e);
			}
			return date;
		}

		/**
		 * Date 타입 객체를 yyyyMMdd 형식의 String 타입으로 반환
		 * @param date
		 * @return
		 */
		public static String getDateToString(Date date) {
			SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDD);
			return formatter.format(date);
		}

		/**
		 * Date 타입 객체를 해당 포맷의 String 타입으로 반환
		 * @param date
		 * @param dateFormat
		 * @return
		 */
		public static String getDateToString(Date date, String dateFormat) {
			SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
			return formatter.format(date);
		}
	}

	/**
	 * 이전/이후 날짜 반환
	 */
	public static class CalcDate {
		
		private CalcDate() {
			super();
		}
		
		/**
		 * 현재 날짜의 이전/이후 날짜를 yyyyMMdd 형식의 String 타입으로 반환<br/>
		 * 	- 인자 값이 음수 인 경우,이전 날짜 반환<br/>
		 * 	- 인자 값이 양수 인 경우, 이후 날짜 반환
		 * @param days
		 * @return
		 */
		public static String plusMinusDay(int days) {
			SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDD);
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, days);
			return formatter.format(cal.getTime());
		}

		/**
		 * yyyyMMdd 형식의 String 타입 날짜의 이전/이후 날짜를 yyyyMMdd 형식의 String 타입으로 반환<br/>
		 * 	- 인자 값이 음수 인 경우,이전 날짜 반환<br/>
		 * 	- 인자 값이 양수 인 경우, 이후 날짜 반환
		 * @param strDate
		 * @param days
		 * @return
		 */
		public static String plusMinusDay(String strDate, int days) {
			SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDD);
			Calendar cal = Calendar.getInstance();

			try {
				cal.setTime(formatter.parse(strDate));
				cal.add(Calendar.DATE, days);
			} catch (Exception e) {
				logger.error("plusMinusDay Exception", e);
			}
			return formatter.format(cal.getTime());
		}

		/**
		 * 현재 날짜의 이전/이후 날짜를 yyyyMMdd 형식의 String 타입으로 반환<br/>
		 * 	- 인자 값이 음수 인 경우,이전 날짜 반환<br/>
		 * 	- 인자 값이 양수 인 경우, 이후 날짜 반환
		 * @param months
		 * @return
		 */
		public static String plusMinusMonth(int months) {
			SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDD);
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, months);
			return formatter.format(cal.getTime());
		}

		/**
		 * yyyyMMdd 형식의 String 타입 날짜의 이전/이후 날짜를 yyyyMMdd 형식의 String 타입으로 반환<br/>
		 * 	- 인자 값이 음수 인 경우,이전 날짜 반환<br/>
		 * 	- 인자 값이 양수 인 경우, 이후 날짜 반환
		 * @param strDate
		 * @param months
		 * @return
		 */
		public static String plusMinusMonth(String strDate, int months) {
			SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDD);
			Calendar cal = Calendar.getInstance();

			try {
				cal.setTime(formatter.parse(strDate));
				cal.add(Calendar.MONTH, months);
			} catch (Exception e) {
				logger.error("plusMinusMonth Exception", e);
			}
			return formatter.format(cal.getTime());
		}

		/**
		 * 현재 날짜의 이전/이후 날짜를 yyyyMMdd 형식의 String 타입으로 반환<br/>
		 * 	- 인자 값이 음수 인 경우,이전 날짜 반환<br/>
		 * 	- 인자 값이 양수 인 경우, 이후 날짜 반환
		 * @param years
		 * @return
		 */
		public static String plusMinusYear(int years) {
			SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDD);
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.YEAR, years);
			return formatter.format(cal.getTime());
		}

		/**
		 * yyyyMMdd 형식의 String 타입 날짜의 이전/이후 날짜를 yyyyMMdd 형식의 String 타입으로 반환<br/>
		 * 	- 인자 값이 음수 인 경우,이전 날짜 반환<br/>
		 * 	- 인자 값이 양수 인 경우, 이후 날짜 반환
		 * @param strDate
		 * @param years
		 * @return
		 */
		public static String plusMinusYear(String strDate, int years) {
			SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDD);
			Calendar cal = Calendar.getInstance();

			try {
				cal.setTime(formatter.parse(strDate));
				cal.add(Calendar.YEAR, years);
			} catch (Exception e) {
				logger.error("plusMinusYear Exception", e);
			}
			return formatter.format(cal.getTime());
		}
	}

	/**
	 * 이전/이후 시간각반환
	 */
	public static class CalcTime {
		
		private CalcTime() {
			super();
		}
		
		/**
		 * 현재 날짜의 이전/이후 날짜를 yyyyMMddHHmmss 형식의 String 타입으로 반환<br/>
		 * 	- 인자 값이 음수 인 경우,이전 시각 반환<br/>
		 * 	- 인자 값이 양수 인 경우, 이후 시각 반환
		 * @param hours
		 * @return
		 */
		public static String plusMinusHour(int hours) {
			SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDDHHMMSS);
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.HOUR, hours);
			return formatter.format(cal.getTime());
		}

		/**
		 * yyyyMMddHHmmss 형식의 String 타입 날짜의 이전/이후 날짜를 yyyyMMddHHmmss 형식의 String 타입으로 반환<br/>
		 * 	- 인자 값이 음수 인 경우,이전 시각 반환<br/>
		 * 	- 인자 값이 양수 인 경우, 이후 시각 반환
		 * @param strDate
		 * @param hours
		 * @return
		 */
		public static String plusMinusHour(String strDate, int hours) {
			SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDDHHMMSS);
			Calendar cal = Calendar.getInstance();

			try {
				cal.setTime(formatter.parse(strDate));
				cal.add(Calendar.HOUR, hours);
			} catch (Exception e) {
				logger.error("plusMinusHour Exception", e);
			}
			return formatter.format(cal.getTime());
		}

		/**
		 * 현재 날짜의 이전/이후 날짜를 yyyyMMdd 형식의 String 타입으로 반환<br/>
		 * 	- 인자 값이 음수 인 경우,이전 시각 반환<br/>
		 * 	- 인자 값이 양수 인 경우, 이후 시각 반환
		 * @param minutes
		 * @return
		 */
		public static String plusMinusMinute(int minutes) {
			SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDD);
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MINUTE, minutes);
			return formatter.format(cal.getTime());
		}

		/**
		 * yyyyMMddHHmmss 형식의 String 타입 날짜의 이전/이후 날짜를 yyyyMMdd 형식의 String 타입으로 반환<br/>
		 * 	- 인자 값이 음수 인 경우,이전 날짜 반환<br/>
		 * 	- 인자 값이 양수 인 경우, 이후 날짜 반환
		 * @param strDate
		 * @param minutes
		 * @return
		 */
		public static String plusMinusMinute(String strDate, int minutes) {
			SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDDHHMMSS);
			Calendar cal = Calendar.getInstance();

			try {
				cal.setTime(formatter.parse(strDate));
				cal.add(Calendar.MINUTE, minutes);
			} catch (Exception e) {
				logger.error("plusMinusMinute Exception", e);
			}
			return formatter.format(cal.getTime());
		}

		/**
		 * 현재 날짜의 이전/이후 날짜를 yyyyMMdd 형식의 String 타입으로 반환<br/>
		 * 	- 인자 값이 음수 인 경우,이전 날짜 반환<br/>
		 * 	- 인자 값이 양수 인 경우, 이후 날짜 반환
		 * @param seconds
		 * @return
		 */
		public static String plusMinusSecond(int seconds) {
			SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDD);
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.SECOND, seconds);
			return formatter.format(cal.getTime());
		}

		/**
		 * yyyyMMddHHmmss 형식의 String 타입 날짜의 이전/이후 날짜를 yyyyMMdd 형식의 String 타입으로 반환<br/>
		 * 	- 인자 값이 음수 인 경우,이전 날짜 반환<br/>
		 * 	- 인자 값이 양수 인 경우, 이후 날짜 반환
		 * @param strDate
		 * @param seconds
		 * @return
		 */
		public static String plusMinusSecond(String strDate, int seconds) {
			SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDDHHMMSS);
			Calendar cal = Calendar.getInstance();

			try {
				cal.setTime(formatter.parse(strDate));
				cal.add(Calendar.SECOND, seconds);
			} catch (Exception e) {
				logger.error("plusMinusSecond Exception", e);
			}
			return formatter.format(cal.getTime());
		}		
	}
	
	/**
	 * 기간 간격 구하기
	 */
	public static class GetDateInterval {
		
		private GetDateInterval() {
			super();
		}
		
		// TODO: intervalYears(String strFixDate)
		
		// TODO: intervalMonths(String strFixDate)
		
		/**
		 * 현재 날짜와 일자 간격 구하기
		 * 	- 0:같다, 양수:크다, 음수:작다
		 * @param strFixDate (yyyyMMdd)
		 * @return
		 */
		public static int intervalDays(String strFixDate) {
			SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDD);
			Date date1 = new Date();
			Date date2 = new Date();

			try {
				date2 = formatter.parse(strFixDate);
			} catch (ParseException e) {
				logger.error("intervalDays ParseException", e);
			}
			
			return (int) ((date2.getTime() - date1.getTime())/(1000*60*60*24));
		}
	}
	
	/**
	 * 시간 간격 구하기
	 */
	public static class GetTimeInterval {
		
		private GetTimeInterval() {
			super();
		}
		
		/**
		 * 현재 날짜와 시간 간격 구하기
		 * 	- 0:같다, 양수:크다, 음수:작다
		 * @param strFixDate (yyyyMMddHHmmss)
		 * @return
		 */
		public static int intervalHours(String strFixDate) {
			SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDDHHMMSS);
			Date date1 = new Date();
			Date date2 = new Date();

			try {
				date2 = formatter.parse(strFixDate);
			} catch (ParseException e) {
				logger.error("intervalHours ParseException", e);
			}
			
			return (int) ((date2.getTime() - date1.getTime())/(1000*60*60));
		}
		
		/**
		 * 현재 날짜와 분 간격 구하기
		 * 	- 0:같다, 양수:크다, 음수:작다
		 * @param strFixDate (yyyyMMddHHmmss)
		 * @return
		 */
		public static int intervalMinutes(String strFixDate) {
			SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDDHHMMSS);
			Date date1 = new Date();
			Date date2 = new Date();

			try {
				date2 = formatter.parse(strFixDate);
			} catch (ParseException e) {
				logger.error("intervalMinutes ParseException", e);
			}
			
			return (int) ((date2.getTime() - date1.getTime())/(1000*60));
		}
		
		// TODO: intervalSeconds(String strFixDate)
	}

	/**
	 * 요일 구하기
	 */
	public static class GetDayOfWeek {
		
		private GetDayOfWeek() {
			super();
		}
		
		/**
		 * 현재 날짜의 요일 구하기
		 * @param strDate
		 * @return
		 */
		public static int getDayOfWeek() {
			Calendar cal = Calendar.getInstance();
			return cal.get(Calendar.DAY_OF_WEEK);
		}
		
		/**
		 * yyyyMMdd 형식의 String 타입 날짜의 요일 구하기
		 * @param strDate
		 * @return
		 */
		public static int getDayOfWeek(String strDate) {
			SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDD);
			Calendar cal = Calendar.getInstance();

			try {
				cal.setTime(formatter.parse(strDate));
			} catch (Exception e) {
				logger.error("getDayOfWeek Exception", e);
			}
			return cal.get(Calendar.DAY_OF_WEEK);
		}

		/**
		 * 현재 날짜의 1일의 요일 반환
		 * @return
		 */
		public static int getFirstDayOfWeek() {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, Calendar.MONTH, 1);
			return cal.get(Calendar.DAY_OF_WEEK);
		}

		/**
		 * yyyyMMdd 형식의 String 타입에 해당하는 1일의 요일 반환
		 * @param strDate
		 * @return
		 */
		public static int getFirstDayOfWeek(String strDate) {
			SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDD);
			Calendar cal = Calendar.getInstance();

			try {
				cal.setTime(formatter.parse(strDate));
			} catch (Exception e) {
				logger.error("getFirstDayOfWeek Exception", e);
			}

			return cal.get(Calendar.DAY_OF_WEEK);
		}
		
		/**
		 * 현재 날짜의 한글 요일 구하기
		 * @param strDate
		 * @return
		 */
		public static String getKorDayOfWeek() {
			Calendar cal = Calendar.getInstance();
			int nDow = cal.get(Calendar.DAY_OF_WEEK);
			String[] arrKorDow = {"일","월","화","수","목","금","토"};
			return arrKorDow[nDow-1];
		}

		/**
		 * yyyyMMdd 형식의 String 타입 날짜의 한글 요일 구하기
		 * @param strDate
		 * @return
		 */
		public static String getKorDayOfWeek(String strDate) {
			SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDD);
			Calendar cal = Calendar.getInstance();

			try {
				cal.setTime(formatter.parse(strDate));
			} catch (Exception e) {
				logger.error("getKorDayOfWeek Exception", e);
			}

			int nDow = cal.get(Calendar.DAY_OF_WEEK);
			String[] arrKorDow = {"일","월","화","수","목","금","토"};
			return arrKorDow[nDow-1];
		}
	}
	
	/**
	 * 마지막 일자 반환
	 */
	public static class GetDayOfMonth {
		
		private GetDayOfMonth() {
			super();
		}
		
		/**
		 * 현재 날짜의 마지막 일자를 반환
		 * @return
		 */
		public static int getLastDayOfMonth() {
			Calendar cal = Calendar.getInstance();
			return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		}
		
		/**
		 * yyyyMMdd 형식의 String 타입에 해당하는 월의 마지막 일자를 반환
		 * @param strDate
		 * @return
		 */
		public static int getLastDayOfMonth(String strDate) {
			SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDD);
			Calendar cal = Calendar.getInstance();

			try {
				cal.setTime(formatter.parse(strDate));
			} catch (Exception e) {
				logger.error("getLastDayOfMonth Exception", e);
			}
			return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		}	
	}
	
}
