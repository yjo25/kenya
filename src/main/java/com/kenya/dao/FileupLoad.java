package com.kenya.dao;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.kenya.bean.Lease;

public class FileupLoad {
	 public String upload(MultipartFile file,HttpServletRequest request) throws Exception{  
		 System.out.println("comming!");  
	        String path = request.getSession().getServletContext().getRealPath("/images");  
	        System.out.println("path>>"+path);
	        String fileName = file.getOriginalFilename();  
	        System.out.println("fileName>>"+fileName);  
	          
	        File dir = new File(path, fileName);  
	        System.out.println("dir.exists()>>"+dir.exists());  
	        if(!dir.exists()){  
	            dir.mkdirs();  
	        }  
	        System.out.println("dir.exists()>>"+dir.exists());  
//	      MultipartFile�Դ��Ľ�������  
	        file.transferTo(dir);
	        System.out.println(fileName);
	        return "images\\"+fileName;
	 }
}
