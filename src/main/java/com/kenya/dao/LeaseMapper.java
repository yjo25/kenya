package com.kenya.dao;

import com.kenya.bean.Lease;
import com.kenya.bean.LeaseCustom;
import com.kenya.bean.LeaseExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface LeaseMapper {
    long countByExample(LeaseExample example);

    int deleteByExample(LeaseExample example);

    int deleteByPrimaryKey(Integer leaseid);

    int insert(Lease record);

    int insertSelective(Lease record);

    List<Lease> selectByExampleWithBLOBs(LeaseExample example);

    List<Lease> selectByExample(LeaseExample example);

    Lease selectByPrimaryKey(Integer leaseid);

    int updateByExampleSelective(@Param("record") Lease record, @Param("example") LeaseExample example);

    int updateByExampleWithBLOBs(@Param("record") Lease record, @Param("example") LeaseExample example);

    int updateByExample(@Param("record") Lease record, @Param("example") LeaseExample example);

    int updateByPrimaryKeySelective(Lease record);

    int updateByPrimaryKeyWithBLOBs(Lease record);

    int updateByPrimaryKey(Lease record);
    
    /**
	 * ��ѯ�����������ܸ���
	 */
	public int selectByFildCount(Map<String,Object> map);
	/**
	 * ��ѯ���������ļ�¼
	 * by:pc
	 */
	public List<LeaseCustom> selectByFild(Map<String,Object> map);
}