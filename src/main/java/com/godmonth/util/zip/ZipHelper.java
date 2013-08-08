package com.godmonth.util.zip;

import java.io.File;
import java.io.IOException;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class ZipHelper {

	public static String unzip(String zipFile, String workingDir, boolean createSubDir) throws IOException, ZipException {
		FileUtils.forceMkdir(new File(workingDir));
		if (createSubDir) {
			String baseName = FilenameUtils.getBaseName(zipFile);
			workingDir += "/" + baseName;
			FileUtils.forceMkdir(new File(workingDir));
		}
		ZipFile zf = new ZipFile(zipFile);
		zf.extractAll(workingDir);
		return workingDir;
	}
}
