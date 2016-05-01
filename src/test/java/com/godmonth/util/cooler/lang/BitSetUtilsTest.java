package com.godmonth.util.cooler.lang;

import java.util.BitSet;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.godmonth.util.lang.BitSetUtils;

public class BitSetUtilsTest {

	@Test
	public void convert() {
		BitSet b = BitSetUtils.convert(1);
		b.or(BitSetUtils.convert(4));
		Assert.assertEquals(5, BitSetUtils.convert(b));
	}
}
