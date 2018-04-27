package com.kenya.service;

import java.util.List;
import java.util.Map;

import com.kenya.bean.Goods;
import com.kenya.bean.GoodsCustom;

public interface GoodsService {
	/**
	 * fild条件查询
	 * @return
	 */
	public List<GoodsCustom> selectByfild(Map<String, Object> map,int pn);
	/**
	 * 查询符合条件的记录个数
	 */
	public int selectByFildCount(Map<String,Object> map);
	
	int creatPublishGoods(Goods usedGoods);
}
