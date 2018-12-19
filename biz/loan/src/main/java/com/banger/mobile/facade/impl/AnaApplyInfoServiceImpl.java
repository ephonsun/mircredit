package com.banger.mobile.facade.impl;

import com.banger.mobile.dao.loan.AnaApplyInfoDao;
import com.banger.mobile.domain.model.loan.AnaApplyInfo;
import com.banger.mobile.domain.model.loan.AnaChildren;
import com.banger.mobile.domain.model.loan.ApplyAddressInfo;
import com.banger.mobile.facade.loan.AnaApplyInfoService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author zhangfp
 * @version $Id: AnaApplyInfoServiceImpl v 0.1 ${} 上午10:35 zhangfp Exp $
 *
 * 上会分析表--申请人信息service
 */
public class AnaApplyInfoServiceImpl implements AnaApplyInfoService{

    private AnaApplyInfoDao anaApplyInfoDao;

    /**
     * 查询某贷款的上会分析表的申请人信息
     *
     * @param paramMap
     * @return
     */
    public AnaApplyInfo selectApplyInfoByLoanId(Map<String, Object> paramMap){
        return anaApplyInfoDao.selectApplyInfoByLoanId(paramMap);
    }

    /**
     * 查询具体地址情况
     *
     * @param paramMap
     * @return
     */
    public List<ApplyAddressInfo> selectApplyAddressInfo(Map<String, Object> paramMap){
        return anaApplyInfoDao.selectApplyAddressInfoList(paramMap);
    }

    /**
     * 家庭成员信息
     *
     * @param applyInfoId
     * @return
     */
    public List<AnaChildren> selectChildrenInfoList(Integer applyInfoId){
        return anaApplyInfoDao.selectChildrenInfoList(applyInfoId);
    }

    /**
     *
     * @param anaApplyInfo
     */
    public void insertApplyInfo(AnaApplyInfo anaApplyInfo){
        anaApplyInfoDao.insertApplyInfo(anaApplyInfo);
    }

    /**
     * 获取下一个申请人信息id序列号
     *
     * @return
     */
    public Integer getNextApplyInfoId(){
        return anaApplyInfoDao.getNextApplyInfoId();
    }

    /**
     *
     * @param anaChildren
     */
    public void insertAnaChildren(AnaChildren anaChildren){
        anaApplyInfoDao.insertAnaChildren(anaChildren);
    }

    /**
     * 批量插入家庭成员信息
     * 
     * @param anaChildrenList
     */
    public void insertAnaChildrenBatch(List<AnaChildren> anaChildrenList){
        anaApplyInfoDao.insertAnaChildrenBatch(anaChildrenList);
    }

    /**
     * 插入申请人信息和多条申请人家庭成员信息
     * 
     * @param anaApplyInfo
     */
    @Transactional
    public void insertApplyAndChildrenBatch(AnaApplyInfo anaApplyInfo){
        Integer nextApplyInfoId = getNextApplyInfoId();
        anaApplyInfo.setApplyInfoId(nextApplyInfoId);
        this.insertApplyInfo(anaApplyInfo);
        if (anaApplyInfo.getChildrenList() != null && anaApplyInfo.getChildrenList().size() > 0){
            for (AnaChildren anaChildren : anaApplyInfo.getChildrenList()){
                anaChildren.setApplyInfoId(nextApplyInfoId);
            }
            this.insertAnaChildrenBatch(anaApplyInfo.getChildrenList());
        }
    }

    /**
     * 编辑申请人信息
     * @param anaApplyInfo
     * @param addingChildrenList
     * @param editingChildrenList
     * @param isClean 是否清除原来的家庭成员信息
     */
    public void updateApplyAndChildren(AnaApplyInfo anaApplyInfo,
                                       List<AnaChildren> addingChildrenList, List<AnaChildren> editingChildrenList, Boolean isClean){
        this.updateApplyInfo(anaApplyInfo);
        if (isClean){
            this.deleteAnaChildren(anaApplyInfo.getApplyInfoId());
        }
        if (addingChildrenList != null && addingChildrenList.size() > 0){
            for (AnaChildren anaChildren : addingChildrenList){
                anaChildren.setApplyInfoId(anaApplyInfo.getApplyInfoId());
            }
            this.insertAnaChildrenBatch(addingChildrenList);
        }
        if (editingChildrenList != null && editingChildrenList.size() > 0){
            this.updateAnaChildrenBatch(editingChildrenList);
        }
    }

    /**
     *
     * @param anaChildren
     */
    public void updateAnaChildren(AnaChildren anaChildren){
        anaApplyInfoDao.updateAnaChildren(anaChildren);
    }

    /**
     * 批量信息家底成员信息表
     * @param anaChildrenList
     */
    public void updateAnaChildrenBatch(List<AnaChildren> anaChildrenList){
        anaApplyInfoDao.updateAnaChildrenBatch(anaChildrenList);
    }

    /**
     *
     * @param anaApplyInfo
     */
    public void updateApplyInfo(AnaApplyInfo anaApplyInfo){
        anaApplyInfoDao.updateApplyInfo(anaApplyInfo);
    }

    /**
     * 删除申请人家庭成员
     * @param applyInfoId
     */
    public void deleteAnaChildren(Integer applyInfoId){
        anaApplyInfoDao.deleteAnaChildren(applyInfoId);
    }

    public AnaApplyInfoDao getAnaApplyInfoDao() {
        return anaApplyInfoDao;
    }

    public void setAnaApplyInfoDao(AnaApplyInfoDao anaApplyInfoDao) {
        this.anaApplyInfoDao = anaApplyInfoDao;
    }
}
