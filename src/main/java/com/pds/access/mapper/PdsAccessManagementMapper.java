package com.pds.access.mapper;

import com.pds.access.entity.PdsAccessManagementEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PdsAccessManagementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PdsAccessManagementEntity record);

    int insertSelective(PdsAccessManagementEntity record);

    PdsAccessManagementEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PdsAccessManagementEntity record);

    int updateByPrimaryKey(PdsAccessManagementEntity record);

    /**
     * 根据用户号找寻数据库中的一系列数据
     *
     * @param userCode
     * @return
     */
    List<PdsAccessManagementEntity> selectByCode(@Param("userCode") String userCode);


    /**
     * 根据住户号删除住户进出记录
     * @param userCode
     * @return
     */
    int deleteByUserCode(@Param("userCode") String userCode);
}