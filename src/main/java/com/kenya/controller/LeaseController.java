package com.kenya.controller;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSONObject;
import com.kenya.bean.Lease;
import com.kenya.bean.LeaseCustom;
import com.kenya.dao.FileupLoad;
import com.kenya.service.LeaseService;
/**
 * 同城信息
 * @author Administrator
 *
 */

@Controller  
@RequestMapping("/Lease")
public class LeaseController {
	private static Logger log=LoggerFactory.getLogger(UserController.class);
	@Autowired
	LeaseService leaseService;
	/**
	 * 查询
	 * @param leaseName
	 * @param pn
	 * @return
	 * @throws Exception
	 */
	//
	@RequestMapping("/selectByFile")
	@ResponseBody
	public  HashMap<String, Object> SelectByFile(@RequestParam(value="leaseName",defaultValue="") String leaseName,@RequestParam(value="pn",defaultValue="1")Integer pn) throws Exception  {
		Map<String, Object> map = new HashMap<String, Object>();
        if(leaseName!=null&&!leaseName.equals("")){  
                map.put("leaseName",leaseName);     
        }
        List<LeaseCustom> lease = leaseService.selectByFild(map, pn);
        HashMap<String, Object> leases = new HashMap<String, Object>();
        leases.put("result",lease);
        System.out.println(leases);
        if(leaseService.selectByFildCount(map)%7==0  ? leaseService.selectByFildCount(map)/7<=pn : leaseService.selectByFildCount(map)/7<=pn) {
        	leases.put("code","040");
        }else {
        	leases.put("code","000");
        }
        return leases; 
	}
	/**
	 * 上传图片
	 * @param lease
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/inserLease")
	@ResponseBody
	public HashMap<String, Object> inserLease(Lease lease,HttpServletRequest request) throws IOException {
		HashMap<String, Object> leases = new HashMap<String, Object>();
		if(leaseService.IsNull(lease)!="非法访问") {
			if(lease.getLeaseIms()!=null) {
				lease.setLeaseimgs(this.doUploadFile(lease.getLeaseIms()));
			}
			if(lease.getLeaseIm1()!=null) {
				lease.setLeaseimg1(this.doUploadFile(lease.getLeaseIm1()));
			}
			if(lease.getLeaseIm2()!=null) {
				lease.setLeaseimg2(this.doUploadFile(lease.getLeaseIm2()));
			}
			if(lease.getLeaseIm3()!=null) {
				lease.setLeaseimg3(this.doUploadFile(lease.getLeaseIm3()));
			}
			if(lease.getLeaseIm4()!=null) {
				lease.setLeaseimg4(this.doUploadFile(lease.getLeaseIm4()));
			}
			leaseService.insert(lease);
			if(leaseService.insert(lease)!=0) {
				leases.put("code", "000");
				leases.put("result","添加成功");
			}else {
				leases.put("code", "040");
				leases.put("result","添加失败");
			}
		}else {
			leases.put("code","050");
			leases.put("result","非法访问");
		}
		return leases;
	}
	@RequestMapping("/test")
    @ResponseBody  
    public Lease test(Lease lease,HttpServletRequest request) throws Exception{  
		System.out.print(lease.getLeaseIms());
		leaseService.Fileupload(lease, request);
        return lease;
    }
	
	

    public String showUploadPage(){
        return "user_admin/file";
    }
    
    public String doUploadFile(MultipartFile file) throws IOException{
        if (!file.isEmpty()) {
            log.info("Process file:{}",file.getOriginalFilename());
        }
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File("E:\\",System.currentTimeMillis()+file.getOriginalFilename()));
        return System.currentTimeMillis()+file.getOriginalFilename();
    }
}
    

