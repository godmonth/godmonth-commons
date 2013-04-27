package com.godmonth.util;

import java.nio.ByteBuffer;

public class BigEndianUtils {
	public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

	private BigEndianUtils() {
	}

	public static byte[] fromInt(int i) {
		return ByteBuffer.allocate(4).putInt(i).array();
	}

	public static int toInt(byte[] bytes) {
		return ByteBuffer.wrap(bytes).getInt();
	}

	public static byte[] fromLong(long i) {
		return ByteBuffer.allocate(8).putLong(i).array();
	}

	public static long toLong(byte[] bytes) {
		return ByteBuffer.wrap(bytes).getLong();
	}
}
