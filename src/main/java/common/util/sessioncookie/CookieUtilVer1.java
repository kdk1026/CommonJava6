package common.util.sessioncookie;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtilVer1 {

	private CookieUtilVer1() {
		super();
	}

	/**
	 * Servlet 2.5 쿠키 설정
	 * @param response
	 * @param name
	 * @param value
	 * @param expiry
	 * @param isUseJs
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, int expiry, boolean isSecure, boolean isUseJs, String domain) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(expiry);
		cookie.setPath("/");
		
		if (isSecure) {
			cookie.setSecure(true);
		}
		
		if ( (domain != null) && (domain.trim().length() > 0) ) {
			cookie.setDomain(domain);
		}
		
		if (isUseJs) {
			response.addCookie(cookie);
		} 
		else {
			Date expireDate = new Date();
			expireDate.setTime( expireDate.getTime() + (cookie.getMaxAge() * 1000) );

			Locale locale = Locale.US;
			DateFormat df = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss z", locale);
			df.setTimeZone(TimeZone.getTimeZone("GMT"));
			String cookieExpire = df.format(expireDate);

			StringBuilder sb = new StringBuilder();
			sb.append(cookie.getName());
			sb.append("=").append(cookie.getValue());
			sb.append("; Max-Age=").append(cookie.getMaxAge());
			sb.append("; Expires=").append(cookieExpire);
			sb.append("; Domain=").append(cookie.getDomain());
			sb.append("; Path=").append(cookie.getPath());
			sb.append("; HttpOnly");

			response.addHeader("Set-Cookie", sb.toString());
		}
	}

	/**
	 * cookieName 인자 값을 가지는 쿠키 가져오기
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static Cookie getCookie(HttpServletRequest request, String cookieName) {
		Cookie resCookie = null;
		Cookie[] cookies = request.getCookies();

		for (final Cookie c : cookies) {
			if ( cookieName.equals(c.getName()) && !"".equals(c.getValue()) ) {
				resCookie = c;
				break;
			}
		}

		return resCookie;
	}

	/**
	 * cookieName 인자 값을 가지는 쿠키의 값 가져오기
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request, String cookieName) {
		String cookieValue = "";
		Cookie cookie = getCookie(request, cookieName);

		if ( cookie != null ) {
			cookieValue = cookie.getValue();
		}

		return cookieValue;
	}

	/**
	 * 모든 쿠키 제거
	 * @param request
	 * @param response
	 */
	public static void removeCookies(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();

		if (cookies != null && cookies.length > 0) {
			for (int i=0; i < cookies.length; i++) {
				cookies[i].setPath("/");
				cookies[i].setMaxAge(0);

				response.addCookie(cookies[i]);
			}
		}
	}

	/**
	 * 특정 쿠키 제거
	 * @param request
	 * @param response
	 * @param cookieName
	 */
	public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
		Cookie cookie = new Cookie(cookieName, null);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		
		if ( cookie.getSecure() ) {
			cookie.setSecure(true);
		}

		response.addCookie(cookie);
	}

	public static boolean isExist(HttpServletRequest request, String cookieName) {
		String cookieValue = getCookieValue(request, cookieName);
		return !"".equals(cookieValue);
	}

	public static int getCookieMaxAge(HttpServletRequest request, String cookieName) {
		Cookie cookie = getCookie(request, cookieName);

		if ( cookie == null ) {
			return 0;
		} else {
			return cookie.getMaxAge();
		}
	}

}