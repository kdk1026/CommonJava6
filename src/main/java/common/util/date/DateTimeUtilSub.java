package common.util.date;

public class DateTimeUtilSub {
	
	private DateTimeUtilSub() {
		super();
	}

	//----------------------------------------------------
	// 날짜 형식 유효성 검사
	//----------------------------------------------------
	
	/**
	 * 해당 String 타입이 날짜 형식인지 검사<br/>
	 * 	- yyyyMMdd, yyyy-MM-dd, yyyy.MM.dd
	 * @param str
	 * @return
	 */
	public static boolean isDateValid(String str) {
		return str.matches("^[0-9]{4}[- / .]?(0[1-9]|1[012])[- / .]?(0[1-9]|1[0-9]|2[0-9]|3[01])+$");
	}
	
	/**
	 * 헤당 String 타입이 시간 형식인지 검사<br/>
	 * - HHmmss, HH:mm:ss
	 * @param str
	 * @return
	 */
	public static boolean isTimeValid(String str) {
		return str.matches("^((0[1-9])|1[0-9]|2[0-4])[:]?([0-5][0-9])[:]?([0-5][0-9])+$");
	}
	
	/**
	 * 해당 String 타입이 날짜(+시간) 형식인지 검사<br/>
	 * 	- 날짜(HHmmss), 날짜(HH:mm:ss), 날짜( HHmmss), 날짜( HH:mm:ss)
	 * @param str
	 * @return
	 */
	public static boolean isDateTimeValid(String str) {
		StringBuilder sb = new StringBuilder();
		sb.append("^[0-9]{4}[- / .]?(0[1-9]|1[012])[- / .]?(0[1-9]|1[0-9]|2[0-9]|3[01])");
		sb.append("(\\s)?(0[1-9]|1[0-9]|2[0-4])[:]?([0-5][0-9])[:]?([0-5][0-9])+$");
		return str.matches(sb.toString());
	}
	
	//----------------------------------------------------
	// 특수문자 제거 및 추가
	//----------------------------------------------------

	/**
	 * '-', '/', '.', ':' 특수문제를 제가하여 반환
	 * @param strDate
	 * @return
	 */
	public static String removeSpecialCharacter(String strDate) {
		return strDate.replaceAll("[-||/||.||:]", "");
	}
	
	/**
	 * yyyyMMdd 형식의 String 타입을 yyyy-MM-dd (HH:mm:ss) 형식으로 반환
	 * @param str
	 * @return
	 */
	public static String setSpecialCharacter(String str) {
		String sRes = "";
		StringBuilder sb = null;
		
		if ( str.length() == 14 ) {
			sb = new StringBuilder();
			sb.append("^([0-9]{4})(0[1-9]|1[012])(0[1-9]|1[0-9]|2[0-9]|3[01])");
			sb.append("(0[1-9]|1[0-9]|2[0-4])([0-5][0-9])([0-5][0-9])+$");
			
			sRes = str.replaceAll(sb.toString(), "$1-$2-$3 $4:$5:$6");			
		} else {
			sb = new StringBuilder();
			sb.append("^([0-9]{4})(0[1-9]|1[012])(0[1-9]|1[0-9]|2[0-9]|3[01])+$");
			
			sRes = str.replaceAll(sb.toString(), "$1-$2-$3");			
		}
		
		return sRes;
	}

	//----------------------------------------------------
	// 분기 계산 반환
	//----------------------------------------------------

	/**
	 * 현재 날짜 분기 반환
	 * @param strDate
	 * @return
	 */
	public static int getQuaterOfDate(int nMonth) {
		return (int) Math.ceil(nMonth / 3.0);
	}

}
