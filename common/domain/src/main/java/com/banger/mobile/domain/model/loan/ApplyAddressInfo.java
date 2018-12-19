package com.banger.mobile.domain.model.loan;

/**
 * @author zhangfp
 * @version $Id: ApplyAddressInfo v 0.1 ${} 下午2:04 zhangfp Exp $
 *
 * 记录申请人地址信息
 */
public class ApplyAddressInfo {
    private String proName;
    private String cityName;
    private String disName;
    private String proRemark;

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDisName() {
        return disName;
    }

    public void setDisName(String disName) {
        this.disName = disName;
    }

    public String getProRemark() {
        return proRemark;
    }

    public void setProRemark(String proRemark) {
        this.proRemark = proRemark;
    }
}
