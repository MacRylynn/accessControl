package com.pds.access.mapper;

import com.pds.access.entity.PdsUserManagementEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PdsUserManagementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PdsUserManagementEntity record);

    int insertSelective(PdsUserManagementEntity record);

    PdsUserManagementEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PdsUserManagementEntity record);

    int updateByPrimaryKey(PdsUserManagementEntity record);

    /**
     * 根据用户号找寻数据库中的相应一条数据
     *
     * @param userCode
     * @return
     */
    PdsUserManagementEntity selectByCode(@Param("userCode") String userCode);

    /**
     * 查询所有住户进出信息
     * @return
     */
    List<PdsUserManagementEntity> selectAll();

    /**
     * 根据住户号删除住户信息
     * @param userCode
     * @return
     */
    int deleteByUserCode(@Param("userCode") String userCode);

    /**
     * 根据前端传入进来的数字查询满足条件的住户
     * @param min
     * @param max
     * @return
     */
    List<PdsUserManagementEntity> selectBySearch(@Param("min") Integer min,@Param("max") Integer max);
}