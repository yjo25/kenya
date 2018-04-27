package com.kenya.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.kenya.bean.News;

public interface NewsService {
    
  public List<News> pageQuery(Map<String, Object> paramMap);

	public int queryCount(Map<String, Object> paramMap);
	public  int  insertSelective (News news);
	
}
