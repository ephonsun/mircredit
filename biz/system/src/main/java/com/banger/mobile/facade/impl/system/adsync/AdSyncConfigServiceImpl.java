package com.banger.mobile.facade.impl.system.adsync;


import com.banger.mobile.dao.adsync.AdSyncConfigDao;
import com.banger.mobile.domain.model.adsync.SyncAdPcUsersSetting;
import com.banger.mobile.facade.adsync.AdSyncConfigService;
import com.banger.mobile.util.AesEncrypt;
import com.banger.mobile.util.DesEncrypt;
import com.banger.mobile.util.StringUtil;

public class AdSyncConfigServiceImpl implements AdSyncConfigService {

	public static final String DEFAULT_PASSWORD = "12345678";
	private AdSyncConfigDao 					adSyncConfigDao;
	
	/**
	 * 保存AD域配置
	 */
	public void save(SyncAdPcUsersSetting syncAdPcUsersSetting){
		//新增或编辑
		if(syncAdPcUsersSetting.getAdId()!=null&&syncAdPcUsersSetting.getAdId()>0){
			updateAdSyncConfig(syncAdPcUsersSetting);
		}else{
			insertAdSyncConfig(syncAdPcUsersSetting);
		}
	}
	/**
	 * 获取AD配置
	 */
	public SyncAdPcUsersSetting getAdSyncConfig(){
		//解密
		SyncAdPcUsersSetting setting = adSyncConfigDao.getAdSyncConfig();
		decryptPassWord(setting);
		return setting;
	}
	
	private void insertAdSyncConfig(SyncAdPcUsersSetting syncAdPcUsersSetting){
		if(syncAdPcUsersSetting.getAdActived()==null){
			syncAdPcUsersSetting.setAdActived(0);
		}
		//加密
		encryptPassWord(syncAdPcUsersSetting);
		adSyncConfigDao.insertAdSyncConfig(syncAdPcUsersSetting);
	}

	private void updateAdSyncConfig(SyncAdPcUsersSetting syncAdPcUsersSetting){
		if(syncAdPcUsersSetting.getAdActived()==null){
			syncAdPcUsersSetting.setAdActived(0);
		}
		//加密
		encryptPassWord(syncAdPcUsersSetting);
		adSyncConfigDao.updateAdSyncConfig(syncAdPcUsersSetting);
	}
	
	private void encryptPassWord(SyncAdPcUsersSetting syncAdPcUsersSetting){
		//DesEncrypt des = new DesEncrypt( "1234567" );  
		
		if(syncAdPcUsersSetting!=null){
			String pwd = syncAdPcUsersSetting.getAdAdminPassword();
			String pwEnctypt = syncAdPcUsersSetting.getAdAdminPwEnctypt();
			if(StringUtil.isEmpty(pwd)||StringUtil.isEmpty(pwEnctypt)){
				return;
			}
			if(pwEnctypt.equalsIgnoreCase("AES")){
//				byte[] encryptResult = AesEncrypt.encrypt(pwd, DEFAULT_PASSWORD);
//				String encryptResultStr = AesEncrypt.parseByte2HexStr(encryptResult); 
//				syncAdPcUsersSetting.setAdAdminPassword(encryptResultStr);
				//用des加密代替 Linux下aes有问题
//				String s = des.encryptStr(pwd);
				syncAdPcUsersSetting.setAdAdminPassword(pwd);
			}else if(pwEnctypt.equalsIgnoreCase("DES")){
//				String s = des.encryptStr(pwd);
				syncAdPcUsersSetting.setAdAdminPassword(pwd);
			}
		}
	}
	
	public void decryptPassWord(SyncAdPcUsersSetting syncAdPcUsersSetting){
//		DesEncrypt des = new DesEncrypt( "1234567" );  

		if(syncAdPcUsersSetting!=null){
			String pwd = syncAdPcUsersSetting.getAdAdminPassword();
			String pwEnctypt = syncAdPcUsersSetting.getAdAdminPwEnctypt();
			if(StringUtil.isEmpty(pwd)||StringUtil.isEmpty(pwEnctypt)){
				return;
			}
			if(pwEnctypt.equalsIgnoreCase("AES")){
//				byte[] decryptFrom = AesEncrypt.parseHexStr2Byte(pwd); 
//				byte[] decryptResult  = AesEncrypt.decrypt(decryptFrom, DEFAULT_PASSWORD);
//				String s = new String(decryptResult);  
//				syncAdPcUsersSetting.setAdAdminPassword(s);
				//用des加密代替 Linux下aes有问题
				try {
//					String s = des.decryptStr(pwd);
					syncAdPcUsersSetting.setAdAdminPassword(pwd);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(pwEnctypt.equalsIgnoreCase("DES")){
				try {
//					String s = des.decryptStr(pwd);
					syncAdPcUsersSetting.setAdAdminPassword(pwd);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void setAdSyncConfigDao(AdSyncConfigDao adSyncConfigDao) {
		this.adSyncConfigDao = adSyncConfigDao;
	}
}
