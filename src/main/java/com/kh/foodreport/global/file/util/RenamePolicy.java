package com.kh.foodreport.global.file.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class RenamePolicy {
	
	public String rename(String originFilename) {
		// 확장자
		String ext = originFilename.substring(originFilename.lastIndexOf("."));
		
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss")
								 .format(new Date());
		int randomNo = (int)(Math.random() * 900) + 100;
		
		String changeName = "FR_" + currentTime + "_"
						  + randomNo + ext;
		
		return changeName;
	}
}
