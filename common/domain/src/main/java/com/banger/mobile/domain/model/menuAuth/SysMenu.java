/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-5-31
 */
package com.banger.mobile.domain.model.menuAuth;

import com.banger.mobile.domain.model.base.menu.BaseSysMenu;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author cheny
 * @version $Id: SysMenu.java,v 0.1 2012-5-31 上午11:43:14 cheny Exp $
 */
public class SysMenu extends BaseSysMenu{

    private static final long serialVersionUID = -1094832743475382011L;

    private String menuType;
    
    
    
    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public SysMenu(){
        
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(239795297, -573118371).appendSuper(super.hashCode())
            .toHashCode();
    }
    
    
}
