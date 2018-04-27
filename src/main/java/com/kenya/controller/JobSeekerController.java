package com.kenya.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.kenya.ajaxResult.JjsonResult1;
import com.kenya.ajaxResult.JsonCodeEnum;
/*import com.atguigu.survey.e.FileTooLargeForSaveException;
import com.atguigu.survey.e.FileTypeInvalidForSaveException;
import com.atguigu.survey.entities.guest.Survey;
import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.utils.DataprocessUtils;
import com.atguigu.survey.utils.GlobalMessage;
import com.atguigu.survey.utils.GlobalNames;*/
import com.kenya.ajaxResult.JsonResult;
import com.kenya.bean.Job;
import com.kenya.bean.JobSeeker;
import com.kenya.bean.PageBean1;
import com.kenya.bean.User;
import com.kenya.service.JobSeekerService;
import com.kenya.until.DataprocessUtils;
import com.kenya.until.StringUtil;

@Controller
@RequestMapping("/jobSeeker")
public class JobSeekerController {
	@Autowired
	private JobSeekerService jobSeekerService;
//  jobSeeker/saveJobWant   logoFile
	@ResponseBody
	@RequestMapping(value="/saveJobWant",method=RequestMethod.POST)
	public JsonResult jobWantPulish(Job survey, @RequestParam("logoFile") MultipartFile logoFile, Integer userId,
			HttpSession session) throws IOException {

		// 1.检查上传的文件是否为空
		if (!logoFile.isEmpty()) {

			// =============================================
			long size = logoFile.getSize();
			if (size > 1024 * 1000) {
				JsonResult pictureTooBig = JsonResult.PictureTooBig();
				return pictureTooBig;
			}

			String contentType = logoFile.getContentType();
			// image/gif,image/png,image/jpg,image/jpeg
			if (!contentType.startsWith("image/")) {
				JsonResult json = JsonResult.NotaPicture();

				return json;
			}

			// =====================\============

			// 2.声明/surveyLogos目录对应的虚拟路径
			String virtualPath = "/surveyLogos";

			// 3.获取ServletContext对象
			ServletContext servletContext = session.getServletContext();

			// 4.将虚拟路径转换为真实物理路径
			String realPath = servletContext.getRealPath(virtualPath);

			// 5.获取文件输入流
			InputStream inputStream = logoFile.getInputStream();

			// 6.执行压缩
			String logoPath = DataprocessUtils.resizeImages(inputStream, realPath);

			// 7.为Survey对象设置logoPath属性
			survey.setHeadimg(logoPath);
		}

		// 8.从Session域中读取User对象进而获得userId

		// 9.给Survey对象设置userId

		survey.setUserid(userId);
		int insert = jobSeekerService.insert(survey);
		if (insert >= 1) {
			JsonResult ok1 = JsonResult.getOK1(survey, JsonCodeEnum.UsedGoodsPublishSuccess.getCode(),
					JsonCodeEnum.UsedGoodsPublishSuccess.getMsg());
			return ok1;
		}

		return null;
	}

	/**
	 * 分页查询用户数据
	 * 
	 * @param pageno
	 * @param pagesize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery(String pagetext, Integer currPage, @RequestParam(value="pagesize",defaultValue="7")Integer pagesize) {
		JjsonResult1 jsonResult = new JjsonResult1();
		PageBean1<Job> jobSeekerPage = new PageBean1<Job>();
		Gson gson =  new Gson();
		try {
			// 查询用户数据
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("start", (currPage - 1) * pagesize);
			paramMap.put("size", pagesize);
			if (StringUtil.isNotEmpty(pagetext)) {
				// pagetext = pagetext.replaceAll("%", "\\%");
			}
			paramMap.put("pagetext", pagetext);

			// 分页查询数据
			List<Job> jobSeeker = jobSeekerService.pageQuery(paramMap);
			// 获取数据的总条数
			int count = jobSeekerService.queryCount(paramMap);

			int totalno = 0;// on总页码
			// 获取总页码
			if (count % pagesize == 0) {
				totalno = count / pagesize;
			} else {
				totalno = count / pagesize + 1;
			}

		
			/*
			 * userPage.setDatas(jobSeeker); userPage.setTotalno(totalno);
			 * userPage.setTotalsize(count); userPage.setPageno(pageno);
			 * userPage.setPagesize(pagesize); result.setPageObj(userPage);
			 */
	/*		for (Job job : jobSeeker) {
				this.longToDate( job.getJointime());
				
				
			}*/
			jobSeekerPage.setCode("000");
			if (totalno<=currPage) {
				jobSeekerPage.setCode("040");
			}
			jobSeekerPage.setLists(jobSeeker);
			jobSeekerPage.setTotalPage(totalno);
			jobSeekerPage.setCurrPage(currPage);
			jobSeekerPage.setTotalCount(count);
			jobSeekerPage.setPageSize(pagesize);
			
			
			//jsonResult.setPageObj(jobSeekerPage);
			
			
		/*	jsonResult.setSuccess(true);
			jsonResult.setCode("000");
*/
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setDatas(false);
		}

		return jobSeekerPage;
	}
	
	public static String longToDate(Date lo){
      
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        return sd.format(lo);
    }

}
