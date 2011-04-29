package com.godmonth.util;

import org.testng.Assert;
import org.testng.annotations.Test;

public class StringConverterTest {

	@Test
	public void testDbColumn2BeanProperty() {
		Assert.assertEquals(StringConverter.dbColumn2BeanProperty("fff_ddd"),
				"fffDdd");
	}

}
