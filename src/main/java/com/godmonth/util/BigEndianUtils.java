package com.godmonth.util;

public class BigEndianUtils {
	public static int readInt(byte[] b) {
		return ((b[0] & 0xff) << 24) | ((b[1] & 0xff) << 16)
				| ((b[2] & 0xff) << 8) | ((b[3] & 0xff) << 0);
	}

	public static short readShort(byte[] b) {
		return (short) (((b[0] & 0xff) << 8) | ((b[1] & 0xff) << 0));
	}

	public static int unassignedShort2Int(short input) {
		return input & 0xffff;
	}

	public static int unassignedbyte2Int(byte input) {
		return input & 0xff;
	}

	public static int readIntFrom2ByteArray(byte[] b) {
		return unassignedShort2Int(readShort(b));
	}

	public static byte[] writeInt(int value) {
		byte[] data = new byte[4];
		data[0] = (byte) ((value >> 24) & 0xff);
		data[1] = (byte) ((value >> 16) & 0xff);
		data[2] = (byte) ((value >> 8) & 0xff);
		data[3] = (byte) ((value >> 0) & 0xff);
		return data;
	}

	public static byte[] writeShort(short value) {
		byte[] data = new byte[2];
		data[0] = (byte) ((value >> 8) & 0xff);
		data[1] = (byte) ((value >> 0) & 0xff);
		return data;
	}

	public static byte[] writeShortFromInt(int value) {
		return writeShort((short) value);
	}
}
