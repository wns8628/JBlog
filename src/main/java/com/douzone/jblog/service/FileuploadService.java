package com.douzone.jblog.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileuploadService {
	
	private static final String SAVE_PATH = "/duzon/uploads";
	private static final String URL = "/uploads/images";
	
	public String restore(MultipartFile multipartFile) {
		
		String url ="";
		try {
			if(multipartFile.isEmpty()) {
				return url;
			}
			
			//1.오리지널뽑고 
			String originalFileName = multipartFile.getOriginalFilename();
			//2.확장자분리해야함 = 파일이름이 시간폴더니깐 ?
			String extName = originalFileName.substring(originalFileName.lastIndexOf('.')+1);
			//3.파일이름만들기 
			String saveFileName = generateSaveFileName(extName);
			//4.파일사이즈 담기 
			long filesize = multipartFile.getSize();
			
			
			System.out.println("############" + originalFileName);
			System.out.println("############" + extName);
			System.out.println("############" + saveFileName);
			System.out.println("############" + filesize);
			
			//5.파일쓰기 
			byte[] fileData = multipartFile.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH +"/" + saveFileName);
			os.write(fileData);
			os.close();
			
			//6. url만들기
			url = URL + "/" + saveFileName;
			
		} catch(IOException ex) {
			new RuntimeException("upload fail"); //전환시킴 
		}			
		return url;
	}

	private String generateSaveFileName(String extName) {
		
		String fileName = "";
		Calendar calendar = Calendar.getInstance();
		
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);			
		fileName += ("." + extName);
		
		return fileName;
	}
}
