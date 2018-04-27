package com.kenya.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kenya.bean.Goods;
import com.kenya.bean.GoodsCustom;
import com.kenya.bean.PageBean;
import com.kenya.bean.PageBean1;
import com.kenya.dao.GoodsMapper;
import com.kenya.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	 private GoodsMapper goodsdao;
	/**
	 * 按照条件查询产品列表,没有条件默认全部
	 */
	public List<GoodsCustom> selectByfild(Map<String, Object> map,int pn) {
        PageBean<Goods> pageBean = new PageBean<Goods>();
        
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
        List<GoodsCustom> goods = goodsdao.selectByFild(map);
		return goods;
	}

	public int selectByFildCount(Map<String, Object> map) {
		return goodsdao.selectByFildCount(map);
	}

	@Override
	//发布服务
	public int creatPublishGoods(Goods usedGoods) {
		// TODO Auto-generated method stub
		return goodsdao.insert(usedGoods);
	}
	
	
	
	
}
