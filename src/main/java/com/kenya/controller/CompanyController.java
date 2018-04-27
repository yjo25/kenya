package com.kenya.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kenya.ajaxResult.JjsonResult1;
import com.kenya.ajaxResult.JsonResult;
import com.kenya.bean.CompanyWithBLOBs;
import com.kenya.bean.Goods;
import com.kenya.bean.PageBean1;

import com.kenya.service.CompanyService;
import com.kenya.until.DataprocessUtils;
import com.kenya.until.StringUtil;

/*
 * public HashMap<String, Object> inserLease(Lease lease,HttpServletRequest request) throws IOException {
		HashMap<String, Object> leases = new HashMap<String, Object>();
 * 
 * 
 */
@Controller
@RequestMapping("/recruit")
public class CompanyController {
	@Autowired
	private CompanyService recruitService;

	@RequestMapping("/publish")
	@ResponseBody
	public Object publishRecruit(/* @RequestParam(value="logoFile",required=false) */
			CompanyWithBLOBs recruitWithBLOBs, HttpServletRequest request, HttpSession session) throws IOException {
		/* RecruitWithBLOBs recruitWithBLOBs = new RecruitWithBLOBs(); */
		HashMap<String, Object> recruitWithMap = new HashMap<String, Object>();

		/*
		 * String logoPaths[] = { "", "", "", "","","" }; int i = -1;
		 * 
		 * for (MultipartFile Lf : logoFile) { i++; if (!Lf.isEmpty()) {// 1.检查上传的文件是否为空
		 * // =====================封线======================== long size = Lf.getSize();
		 * System.out.println(size + "ssrrrrrrssssssssssssssssssssssss"); if (size >
		 * 1024 * 1024*5) { // throw new MyException("ss5555555");//文件太大了 JsonResult
		 * pictureTooBig = JsonResult.PictureTooBig(); return
		 * JSON.toJSONString(pictureTooBig); return pictureTooBig; } String contentType
		 * = Lf.getContentType(); System.out.println(contentType +
		 * "1sssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"); if
		 * (!contentType.startsWith("image/")) { // 不是图片 JsonResult json =
		 * JsonResult.NotaPicture(); //return JSON.toJSONString(json); return json;
		 * 
		 * }
		 * 
		 * 
		 * 422320ssrrrrrrssssssssssssssssssssssss
		 * image/png1sssssssssssssssssssssssssssssssssssssssssssssssssssssssssss
		 * org.apache.catalina.core.ApplicationContextFacade@fed0811
		 * E:\.metadata\.plugins\org.eclipse.wst.server.core\tmp2\wtpwebapps\kenYa-test\
		 * surveyLogosaassdfgsdgdsgd http://localhost:8080/kenYa-test/saveSurvey
		 * 
		 * String virtualPath = "/surveyLogos"; // 3.获取ServletContext对象 ServletContext
		 * servletContext = session.getServletContext();
		 * System.out.println(servletContext);
		 * 
		 * // 4.将虚拟路径转换为真实物理路径 String realPath =
		 * servletContext.getRealPath(virtualPath); System.out.println(realPath +
		 * "aassdfgsdgdsgd");
		 * 
		 * // 5.获取文件输入流 InputStream inputStream = Lf.getInputStream();
		 * 
		 * // // 6.执行压缩 logoPaths[i] = DataprocessUtils.resizeImages(inputStream,
		 * realPath);
		 * 
		 * }
		 * 
		 * }
		 * 
		 * recruitWithBLOBs.setCompanyimg0(logoPaths[0]);
		 * recruitWithBLOBs.setCompanyimg1(logoPaths[1]);
		 * recruitWithBLOBs.setCompanyimg2(logoPaths[2]);
		 * recruitWithBLOBs.setCompanyimg3(logoPaths[3]);
		 * recruitWithBLOBs.setCompanyimg4(logoPaths[4]);
		 * recruitWithBLOBs.setCompanyimg5(logoPaths[5]);
		 */

		int count = recruitService.insert(recruitWithBLOBs);
		String s = request.getParameter("companyname");
		System.out.println(s + "233423ssssssssssssssfgdsgsdgd");
		System.out.println(recruitWithBLOBs.getCompanyname() + "sgsdg sdgsdgds");

		if (count > 0) {
			recruitWithMap.put("code", "000");
			recruitWithMap.put("msg", "招聘发布成功");
			recruitWithMap.put("data", recruitWithBLOBs);
			return recruitWithMap;
		} else {
			recruitWithMap.put("code", "000");
			recruitWithMap.put("msg", "招聘发布失败");
			return recruitWithMap;
		}

	}

	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery(String pagetext, Integer currPage,
			@RequestParam(value = "pagesize", defaultValue = "7") Integer pagesize) {
		JjsonResult1 jsonResult = new JjsonResult1();
		PageBean1<CompanyWithBLOBs> jobSeekerPage = new PageBean1<CompanyWithBLOBs>();
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
			List<CompanyWithBLOBs> jobSeeker = recruitService.pageQuery(paramMap);
			// 获取数据的总条数
			int count = recruitService.queryCount(paramMap);

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
			jobSeekerPage.setCode("000");
			if (totalno<=currPage) {
				jobSeekerPage.setCode("040");
			}
			jobSeekerPage.setLists(jobSeeker);
			jobSeekerPage.setTotalPage(totalno);
			jobSeekerPage.setCurrPage(currPage);
			jobSeekerPage.setTotalCount(count);
			jobSeekerPage.setPageSize(pagesize);

			
			jsonResult.setPageObj(jobSeekerPage);
			jsonResult.setSuccess(true);

		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setDatas(false);
		}

		return jobSeekerPage;
	}
	// 删除

	@ResponseBody
	@RequestMapping("/delet")
	public Object pageQuery(Integer companyid) {
		return recruitService.deleteByPrimaryKey(companyid);

	}

}
