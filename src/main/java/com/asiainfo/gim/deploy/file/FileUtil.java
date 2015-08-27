package com.asiainfo.gim.deploy.file;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUtil {
	
	/**
	 * 返回dir目录下文件
	 * @param dir
	 * @return
	 */
	public static List<File> listFile(String dir){
		File file = new File(dir);
		if(file.isDirectory()){
			return Arrays.asList(file.listFiles());
		}
		return new ArrayList<File>();
	}

}
