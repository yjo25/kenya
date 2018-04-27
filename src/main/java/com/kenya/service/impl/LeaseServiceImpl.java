package com.kenya.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kenya.bean.Goods;
import com.kenya.bean.Lease;
import com.kenya.bean.LeaseCustom;
import com.kenya.bean.PageBean;
import com.kenya.bean.PageBean1;
import com.kenya.dao.FileupLoad;
import com.kenya.dao.LeaseMapper;
import com.kenya.service.LeaseService;

@Service
public class LeaseServiceImpl implements LeaseService{

	@Autowired
	LeaseMapper leasedao;
	
	public int selectByFildCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return leasedao.selectByFildCount(map);
	}

	/**
	 *	查询符合条件的记录
	 */
	public List<LeaseCustom> selectByFild(Map<String, Object> map,int pn) {
		 PageBean<Lease> pageBean = new PageBean<Lease>();
	        
	        //封装当前页数
	        pageBean.setCurrPage(pn);
	        //每页显示的数据
	        int pageSize=7;
	        pageBean.setPageSize(pageSize);

	        //封装总记录数
	        int totalCount = this.selectByFildCount(map);
	        pageBean.setTotalCount(totalCount);

	        //封装总页数
	        double tc = totalCount;
	        Double num =Math.ceil(tc/pageSize);//向上取整
	        pageBean.setTotalPage(num.intValue());
	        int page = pageBean.getCurrPage();
	        map.put("start",(page-1)*pageSize);
	        map.put("size", pageBean.getPageSize());
	        //封装每页显示的数据
	        List<LeaseCustom> leases = leasedao.selectByFild(map);
			return leases;
	}
	/**
	 * 添加记录
	 * 
	 */
	public int insert(Lease lease) throws IOException{
		return leasedao.insert(lease);
	}
	/**
	 * 图片上传
	 * 
	 */
	public Lease Fileupload(Lease lease,HttpServletRequest request) throws Exception{
		FileupLoad fileupload = new FileupLoad();
		
		if(lease.getLeaseIms()!=null) {
			lease.setLeaseimgs(fileupload.upload(lease.getLeaseIms(),request));
		}
		if(lease.getLeaseIm1()!=null) {
			lease.setLeaseimg1(fileupload.upload(lease.getLeaseIm1(),request));
		}
		if(lease.getLeaseIm2()!=null) {
			lease.setLeaseimg2(fileupload.upload(lease.getLeaseIm2(),request));
		}
		if(lease.getLeaseIm3()!=null) {
			lease.setLeaseimg3(fileupload.upload(lease.getLeaseIm3(),request));
		}
		if(lease.getLeaseIm4()!=null) {
			lease.setLeaseimg4(fileupload.upload(lease.getLeaseIm4(),request));
		}
		return lease;
	}
	/**
	 * 验证非空
	 */
	public String IsNull(Lease lease) {
		if(lease.getLeaseaddress()==""||lease.getLeaseaddress().equals("")) {
			return "非法访问";
		}
		if(lease.getLeasename()==""||lease.getLeasename().equals("")) {
			return "非法访问";
		}
		if(lease.getLeasedesc()==""||lease.getLeasedesc().equals("")) {
			return "非法访问";
		}
		if(lease.getLeasephone()==""||lease.getLeasephone().equals("")) {
			return "非法访问";
		}
		if(lease.getLeaseprice()==0) {
			return "非法访问";
		}
		if(lease.getLeasesquare()==""||lease.getLeasesquare().equals("")){
			return "非法访问";
		}
		if(lease.getLeaseIms()==null) {
			return "非法访问";
		}
		if(lease.getUserid()==0||lease.getLeaseIms()==null) {
			return "非法访问";
		}
		return "";
	}


	
}
