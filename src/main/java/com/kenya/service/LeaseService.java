package com.kenya.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.kenya.bean.Lease;
import com.kenya.bean.LeaseCustom;

public interface LeaseService {
	/**
	 * 查询符合条件的总个数
	 */
	public int selectByFildCount(Map<String,Object> map);
	/**
	 * 查询符合条件的记录
	 * by:pc
	 */
	public List<LeaseCustom> selectByFild(Map<String,Object> map,int pn);
	/**
	 * 插入记录
	 * @throws IOException 
	 */
	public int insert(Lease lease) throws IOException;
	/**
	 * 非空验证
	 */
	public String IsNull(Lease lease);
	/**
	 * 上传图片
	 * @throws Exception 
	 */
	public Lease Fileupload(Lease lease,HttpServletRequest request) throws Exception;
}
