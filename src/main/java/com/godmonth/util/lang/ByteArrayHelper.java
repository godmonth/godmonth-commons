package com.godmonth.util.lang;

import org.apache.commons.lang3.ArrayUtils;

public class ByteArrayHelper {
	public static void fill(byte[] target, int offset, byte[] source) {
		if (source != null) {
			for (int i = 0; i < source.length; i++) {
				target[offset + i] = source[i];
			}
		}
	}

	public static byte[] trim(byte[] data, int offset, int length) {
		if (offset != 0 || length != data.length) {
			return ArrayUtils.subarray(data, offset, length);
		} else {
			return data;
		}
	}

	public static byte[] join(byte[][] content) {
		byte[] result = content[0];
		for (int i = 1; i < content.length; i++) {
			result = ArrayUtils.addAll(result, content[i]);
		}
		return result;

	}

	public static byte[][] split(byte[] data, int mtu) {
		int times = data.length / mtu;
		if (data.length % mtu > 0) {
			times++;
		}
		byte[][] result = new byte[times][];
		int startIndex = 0;
		int count = 0;
		do {
			result[count++] = ArrayUtils.subarray(data, startIndex, Math.min(
					data.length, startIndex + mtu));
			startIndex += mtu;
		} while (startIndex < data.length);
		return result;
	}
}
