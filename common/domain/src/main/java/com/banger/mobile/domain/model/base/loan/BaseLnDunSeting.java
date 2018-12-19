package com.banger.mobile.domain.model.base.loan;

import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Time: 2013/3/4
 * @author xuhj
 * 贷款催收设置
 */
public class BaseLnDunSeting {
	private Integer dunSetingId;					//主键ID
	private Integer isActived;						//是否启用到期前提醒
	private Integer dunDays;						//催收日期
	private Integer dunType;						//催收方式
	private String dunVoiceFile;					//催讨语音文件全路径
	private Date dunTime;							//催讨时间
	private String smsContent;						//短信内容
	private Date sendTime;							//发送时间
	private String account;							//增值业务账号
	private String passWord;						//增值业务密码
	private Integer flag;							//到期前 后 标示
	private Date createDate;           				//创建时间
    private Date updateDate;           				//更新时间
    private Integer createUser;        				//创建用户
    private Integer updateUser;        				//更新用户
    
	public BaseLnDunSeting() {
		super();
	}

	public BaseLnDunSeting(Integer dunSetingId, Integer isActived,
			Integer dunDays, Integer dunType, String dunVoiceFile,
			Date dunTime, String smsContent, Date sendTime, String account,
			String passWord, Integer flag, Date createDate, Date updateDate,
			Integer createUser, Integer updateUser) {
		super();
		this.dunSetingId = dunSetingId;
		this.isActived = isActived;
		this.dunDays = dunDays;
		this.dunType = dunType;
		this.dunVoiceFile = dunVoiceFile;
		this.dunTime = dunTime;
		this.smsContent = smsContent;
		this.sendTime = sendTime;
		this.account = account;
		this.passWord = passWord;
		this.flag = flag;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.createUser = createUser;
		this.updateUser = updateUser;
	}

	public Integer getDunSetingId() {
		return dunSetingId;
	}
	public void setDunSetingId(Integer dunSetingId) {
		this.dunSetingId = dunSetingId;
	}
	public Integer getIsActived() {
		return isActived;
	}
	public void setIsActived(Integer isActived) {
		this.isActived = isActived;
	}
	public Integer getDunDays() {
		return dunDays;
	}
	public void setDunDays(Integer dunDays) {
		this.dunDays = dunDays;
	}
	public Integer getDunType() {
		return dunType;
	}
	public void setDunType(Integer dunType) {
		this.dunType = dunType;
	}
	public String getDunVoiceFile() {
		return dunVoiceFile;
	}
	public void setDunVoiceFile(String dunVoiceFile) {
		this.dunVoiceFile = dunVoiceFile;
	}
	public Date getDunTime() {
		return dunTime;
	}
	public void setDunTime(Date dunTime) {
		this.dunTime = dunTime;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Integer getCreateUser() {
		return createUser;
	}
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	public Integer getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}
	public String getSmsContent() {
		return smsContent;
	}
	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof BaseLnDunSeting)) {
			return false;
		}
		BaseLnDunSeting rhs = (BaseLnDunSeting) object;
		return new EqualsBuilder().appendSuper(super.equals(object))
				.append(this.smsContent, rhs.smsContent)
				.append(this.sendTime, rhs.sendTime)
				.append(this.dunType, rhs.dunType)
				.append(this.dunSetingId, rhs.dunSetingId)
				.append(this.passWord, rhs.passWord)
				.append(this.updateDate, rhs.updateDate)
				.append(this.dunTime, rhs.dunTime)
				.append(this.createUser, rhs.createUser)
				.append(this.flag, rhs.flag).append(this.account, rhs.account)
				.append(this.isActived, rhs.isActived)
				.append(this.dunVoiceFile, rhs.dunVoiceFile)
				.append(this.createDate, rhs.createDate)
				.append(this.updateUser, rhs.updateUser)
				.append(this.dunDays, rhs.dunDays).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-2111951573, 1602739887)
				.appendSuper(super.hashCode()).append(this.smsContent)
				.append(this.sendTime).append(this.dunType)
				.append(this.dunSetingId).append(this.passWord)
				.append(this.updateDate).append(this.dunTime)
				.append(this.createUser).append(this.flag).append(this.account)
				.append(this.isActived).append(this.dunVoiceFile)
				.append(this.createDate).append(this.updateUser)
				.append(this.dunDays).toHashCode();
	}

}
