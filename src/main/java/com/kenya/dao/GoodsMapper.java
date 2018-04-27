package com.kenya.dao;

import com.kenya.bean.Goods;
import com.kenya.bean.GoodsCustom;
import com.kenya.bean.GoodsExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface GoodsMapper {
    long countByExample(GoodsExample example);

    int deleteByExample(GoodsExample example);

    int deleteByPrimaryKey(Integer goodsid);

    int insert(Goods record);

    int insertSelective(Goods record);

    List<Goods> selectByExampleWithBLOBs(GoodsExample example);

    List<Goods> selectByExample(GoodsExample example);

    Goods selectByPrimaryKey(Integer goodsid);

    int updateByExampleSelective(@Param("record") Goods record, @Param("example") GoodsExample example);

    int updateByExampleWithBLOBs(@Param("record") Goods record, @Param("example") GoodsExample example);

    int updateByExample(@Param("record") Goods record, @Param("example") GoodsExample example);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKeyWithBLOBs(Goods record);

    int updateByPrimaryKey(Goods record);
    
    

    List<GoodsCustom> selectByFild(Map<String, Object> map);
	    /**
	     * 查询符合条件的个数
	     * @param goodsid
	     * @return
	     */
	int selectByFildCount(Map<String,Object> map);

}