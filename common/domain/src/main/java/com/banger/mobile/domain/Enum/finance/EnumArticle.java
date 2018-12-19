package com.banger.mobile.domain.Enum.finance;
/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 11, 2012 2:04:05 PM
 * 类说明
 */
public enum EnumArticle {	
	ART_ADD_COLLECT("art_add_collect","收藏"),
	ART_DEL_COLLECT("art_del_collect","删除收藏"),
	ART_DISCESS("art_discess","评论"),
	ART_PARTAKE("srt_partake","参与");
	
  private String code;

    private String name;

    EnumArticle(String code, String name) {
        this.code = code;
        this.name = name;
    }
    
    public String getKey() {
        return this.code;
    }

    public String getValue() {
        return this.name;
    }

}



