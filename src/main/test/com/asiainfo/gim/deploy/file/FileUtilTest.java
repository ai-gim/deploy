package com.asiainfo.gim.deploy.file;

import java.io.File;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class FileUtilTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<File> fileList = FileUtil.listFile("F:/迅雷下载/xcat/iso");
		for(File file : fileList){
//			System.out.println(file.getName());
			if(StringUtils.endsWithIgnoreCase(file.getName(), ".iso")){
				System.out.println(file.getName());
			}
		}

	}

}
