package common.libTest.commons.lang;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.time.StopWatch;

public class UsageEtcUtils {

	public static void randomStr() {
		System.out.println( RandomStringUtils.random(6, true, true) );
	}

	public static void escapeHtml() {
		String str = "<script>alert(1);</script>";
		System.out.println( StringEscapeUtils.escapeHtml4(str) );
	}

	public static void unEscapeHtml() {
		String str = "&lt;script&gt;alert(1);&lt;/script&gt;";
		System.out.println( StringEscapeUtils.unescapeHtml4(str) );
	}

	public static void escapeEcmaScript() {
		String str = "SQL Use 'Single Quote'";
		System.out.println( StringEscapeUtils.escapeEcmaScript(str) );
	}
	
	public static void getProperty() {
		StringBuilder sb = null;
		
		sb = new StringBuilder();
		sb.append("[Java] : ").append(SystemUtils.JAVA_VERSION);
		sb.append(", ").append(SystemUtils.JAVA_VENDOR);
		sb.append(", ").append(SystemUtils.JAVA_HOME);
		System.out.println(sb.toString());
		
		sb = new StringBuilder();
		sb.append("[OS] : ").append(SystemUtils.OS_NAME);
		sb.append(", ").append(SystemUtils.OS_ARCH);
		sb.append(", ").append(SystemUtils.OS_VERSION);
		System.out.println(sb.toString());
		
		sb = new StringBuilder();
		sb.append("[User] : ").append(SystemUtils.USER_NAME);
		sb.append(", ").append(SystemUtils.USER_HOME);
		sb.append(", ").append(SystemUtils.USER_DIR);
		System.out.println(sb.toString());
	}
	
	public static void stopWatch() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.reset();
		stopWatch.start();

		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		stopWatch.stop();
		System.out.println(stopWatch.toString());
	}

}
