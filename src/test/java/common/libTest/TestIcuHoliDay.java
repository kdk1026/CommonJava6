package common.libTest;

import org.junit.Test;

import common.util.date.Icu4JHolidayUtil;

public class TestIcuHoliDay {
	
	@Test
	public void test() {
		System.out.println( Icu4JHolidayUtil.getHoliday("20180215")  );
	}

}
