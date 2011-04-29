package com.godmonth.util.memcached;

import java.io.IOException;

public class TestMemDSupport {
	private Process memD;
	protected String cmd = "src/test/exec/memcached_win32/memcached.exe -p 11211";

	public void startDaemon() throws IOException {
		memD = Runtime.getRuntime().exec(
				"src/test/exec/memcached_win32/memcached.exe -p 11211");
		System.out.println("started memD");
	}

	public void shutdownDaemon() {
		memD.destroy();
		System.out.println("shutdown memD");
	}

	public void setMemD(Process memD) {
		this.memD = memD;
	}

}
