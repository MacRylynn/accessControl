package com.pds.access.service.impl;


import com.pds.access.domain.request.AccessReq;
import com.pds.access.domain.request.SearchReq;
import com.pds.access.domain.request.UserReq;
import com.pds.access.domain.response.AccessRes;
import com.pds.access.domain.response.UserRes;
import com.pds.access.entity.PdsAccessManagementEntity;
import com.pds.access.entity.PdsUserManagementEntity;
import com.pds.access.mapper.PdsAccessManagementMapper;
import com.pds.access.mapper.PdsUserManagementMapper;
import com.pds.access.service.AccessManagementService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: lijiao
 * @Date: Create in 13:58 2020/2/9
 * @version: 1.0
 */
@Service
public class AccessManagementServiceImpl implements AccessManagementService {
    @Resource
    private PdsAccessManagementMapper pdsAccessManagementMapper;
    @Resource
    private PdsUserManagementMapper pdsUserManagementMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String insertUser(UserReq req) {
        PdsUserManagementEntity pdsUserManagementEntity = new PdsUserManagementEntity();
        pdsUserManagementEntity.setRegisteredTime(new Date());
        //初始化进小区的次数为0
        pdsUserManagementEntity.setTimesIn(0);
        //初始化出小区的次数为0
        pdsUserManagementEntity.setTimesOut(0);
        //初始化用户号
        pdsUserManagementEntity.setUserCode(req.getUserCode());
        //初始化用户状态 0 表示正常用户
        pdsUserManagementEntity.setUserType(0);
        //插入数据,成功则返回用户号
        int sign = 0;
        try {
            sign = pdsUserManagementMapper.insertSelective(pdsUserManagementEntity);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        if (sign == 1)
            return req.getUserCode();
        return "Duplicate registration";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean inOrOut(AccessReq req) {
        String userCode = req.getUserCode();
        // 1. 总表数据修改
        PdsUserManagementEntity pdsUserManagementEntity = pdsUserManagementMapper.selectByCode(userCode);
        // 2. 如果用户没有被禁止
        if (pdsUserManagementEntity.getUserType() == 0) {
            int mark = 0;
            if (req.getAccessType().equals("IN")) {
                int in = pdsUserManagementEntity.getTimesIn();
                pdsUserManagementEntity.setTimesIn(in + 1);
                mark = pdsUserManagementMapper.updateByPrimaryKeySelective(pdsUserManagementEntity);
            }
            if (req.getAccessType().equals("OUT")) {
                int out = pdsUserManagementEntity.getTimesOut();
                pdsUserManagementEntity.setTimesOut(out + 1);
                mark = pdsUserManagementMapper.updateByPrimaryKeySelective(pdsUserManagementEntity);
            }
            // 3. 明细表增加
            int sign = 0;
            if (mark == 1) {
                PdsAccessManagementEntity pdsAccessManagementEntity = new PdsAccessManagementEntity();
                pdsAccessManagementEntity.setAccessTime(new Date());
                pdsAccessManagementEntity.setAccessType(req.getAccessType());
                pdsAccessManagementEntity.setUserCode(req.getUserCode());
                sign = pdsAccessManagementMapper.insertSelective(pdsAccessManagementEntity);
            }
            return sign == 1;
        } else {
            return false;
        }
    }

    @Override
    public List<UserRes> getAllRecords() {
        List<PdsUserManagementEntity> pdsUserList = pdsUserManagementMapper.selectAll();
        List<UserRes> list = new ArrayList<>();
        for (PdsUserManagementEntity pdsUserManagementEntity : pdsUserList) {
            UserRes userRes = new UserRes();
            BeanUtils.copyProperties(pdsUserManagementEntity, userRes);
            list.add(userRes);
        }
        if (list.isEmpty())
            return null;
        return list;
    }

    @Override
    public List<AccessRes> getRecordByCode(String userCode) {
        List<PdsAccessManagementEntity> pdsAccessList = pdsAccessManagementMapper.selectByCode(userCode);
        List<AccessRes> list = new ArrayList<>();
        for (PdsAccessManagementEntity pdsAccessManagementEntity : pdsAccessList) {
            AccessRes accessRes = new AccessRes();
            BeanUtils.copyProperties(pdsAccessManagementEntity, accessRes);
            list.add(accessRes);
        }
        if (list.isEmpty())
            return null;
        return list;
    }

    @Override
    public Boolean deleteAccessRecords(String userCode) {
        // 1. 删除住户信息
        pdsUserManagementMapper.deleteByUserCode(userCode);
        // 2. 删除住户信息下的所有进出记录
        pdsAccessManagementMapper.deleteByUserCode(userCode);
        return true;
    }

    @Override
    public List<UserRes> selectBySearch(SearchReq req) {
        List<UserRes> list = new ArrayList<>();
        Integer min = req.getMin();
        Integer max = req.getMax();
        if (min > max) {
            return list;
        }
        List<PdsUserManagementEntity> resList = pdsUserManagementMapper.selectBySearch(min, max);
        resList.forEach(item -> {
            UserRes userRes = new UserRes();
            BeanUtils.copyProperties(item, userRes);
            list.add(userRes);
        });
        return list;
    }

    @Override
    public Boolean changeUserType(String userCode) {
        PdsUserManagementEntity pdsUserManagementEntity = pdsUserManagementMapper.selectByCode(userCode);
        if (pdsUserManagementEntity.getUserType() == 0) {
            pdsUserManagementEntity.setUserType(1);
            pdsUserManagementMapper.updateByPrimaryKeySelective(pdsUserManagementEntity);
            return true;
        }
        if (pdsUserManagementEntity.getUserType() == 1) {
            pdsUserManagementEntity.setUserType(0);
            pdsUserManagementMapper.updateByPrimaryKeySelective(pdsUserManagementEntity);
            return true;
        }
        return false;
    }

}
