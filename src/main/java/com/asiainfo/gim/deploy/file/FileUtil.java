package com.asiainfo.gim.deploy.file;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileUtil {
	
	/**
	 * 返回dir目录下文件
	 * @param file
	 * @return
	 */
	public static List<File> listFile(File file, List<File> fileList){
		if(file.isDirectory()){
			File[] subFiles = file.listFiles();
			for(File subFile : subFiles){
				if(subFile.isDirectory()){
					listFile(subFile, fileList);
				}else{
					fileList.add(subFile);
				}
			}
		}else{
			fileList.add(file);
		}
		return fileList;
	}
	
	/**
	 * 删除所传文件或目录
	 * @param file
	 */
	public static void deleteFile(File file){
		if(file.isDirectory()){
			File[] subFiles = file.listFiles();
			for(File subFile : subFiles){
				if(subFile.isDirectory()){
					deleteFile(subFile);
				}else{
					subFile.delete();
				}
			}
		}
		file.delete();
	}
	
	public static boolean createFile(File file) throws IOException {  
        if(! file.exists()) {  
            makeDir(file.getParentFile());  
        }  
        return file.createNewFile();  
    }
	
	public static void makeDir(File dir) {  
        if(! dir.getParentFile().exists()) {  
            makeDir(dir.getParentFile());  
        }  
        dir.mkdir();  
    }

}
