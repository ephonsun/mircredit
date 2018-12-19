package com.banger.mobile.facade.loan;

import com.banger.mobile.domain.model.loan.LnLoanSubType;
import com.banger.mobile.domain.model.loan.LnLoanType;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-2-6
 * Time: 下午3:35
 * To change this template use File | Settings | File Templates.
 */
public interface LnLoanTypeService {
    /**
     * 取得所有启用的贷款类型
     * @return
     */
    public List<LnLoanType> getLoanTypeList();

    /**
     * 取得子类型
     * @param loanTypeId
     * @return
     */
    public List<LnLoanSubType> getLoanSubTypeList(Integer loanTypeId);

    public void moveTypeItems(int id,String moveType);

    public LnLoanType getLnLoanTypeBySortNo(int id);
    
    public LnLoanType getMaxSortNoLoanType();

    public void updateLoanType(LnLoanType loanType);

    public LnLoanType getMinSortNoLoanType();

    public LnLoanType getLoanTypeById(int id);

    public void insertLoanType(LnLoanType loanType);

    List<LnLoanType> getAllActivedType(Map<String, Object> paramMap);
    
    public LnLoanType getLoanTypeByName(String name);
    
    public List<LnLoanType> getLoanTypesByIdAndName(LnLoanType ln);
}
