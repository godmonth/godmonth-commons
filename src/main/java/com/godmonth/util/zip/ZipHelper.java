package com.godmonth.util.zip;

import java.io.File;
import java.io.IOException;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class ZipHelper {
	private ZipHelper() {
	}

	public static String unzip(String zipFile, String workingDir, boolean createSubDir) throws IOException,
			ZipException {
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

	public static ZipFile create(String folder, String file) throws ZipException {
		File f = new File(file);
		if (f.exists()) {
			return null;
		}
		ZipParameters parameters = new ZipParameters();
		parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
		parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
		ZipFile zf = new ZipFile(file);
		zf.addFolder(folder, parameters);
		return zf;
	}
}
