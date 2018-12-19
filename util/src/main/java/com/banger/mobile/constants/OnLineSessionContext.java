/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :全局Session...
 * Author     :yangy
 * Create Date:2012-8-22
 */
package com.banger.mobile.constants;

import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpSession;
import com.banger.mobile.domain.model.user.UserInfo;

/**
 * @author yangyang
 * @version $Id: OnLineSessionContext.java,v 0.1 2012-8-22 下午7:44:36 yangyong Exp $
 */
public class OnLineSessionContext {

    private static OnLineSessionContext instance;
    private HashMap                     sessionmap; //session存储

    private HashMap                     onlinemap;  //用户在线列表存储

    private HashMap                     kicklinemap; //用户踢线列表存储

    /*
     * 默认构造方法，初始化对应HashMap
     */
    private OnLineSessionContext() {
        sessionmap = new HashMap();
        onlinemap = new HashMap();
        kicklinemap = new HashMap();
    }

    /**
     * 维护此类只有一个实例
     * @return
     */
    public static OnLineSessionContext getInstance() {
        if (instance == null) {
            instance = new OnLineSessionContext();
        }
        return instance;
    }

    /**
     * 添加一个session
     * @param session
     */
    public synchronized void addSession(HttpSession session) {
        if (session != null) {
            sessionmap.put(session.getId(), session);
        }
    }

    /**
     * 删除一个session
     * @param session
     */
    public synchronized void delSession(HttpSession session) {
        if (session != null) {
            sessionmap.remove(session.getId());
        }
    }

    /**
     * 得到一个session
     * @param session_id
     * @return
     */
    public synchronized HttpSession getSession(String session_id) {
        if (session_id == null)
            return null;
        return (HttpSession) sessionmap.get(session_id);
    }

    /**
     * 返回全部session对象集合   
     * @return
     */
    public synchronized Iterator getAllSession() {
        return sessionmap.values().iterator();
    }

    /**
     * 添加一个用户在在线列表
     * @param userInfo
     */
    public synchronized void addUserOnLine(UserInfo userInfo) {
        if (userInfo != null) {
            onlinemap.put(String.valueOf(userInfo.getUserId()), userInfo);
        }
    }

    /**
     * 从在线用户列表里面删除一个用户
     * @param userInfo
     */
    public synchronized void delUserOnLine(String userId) {
        onlinemap.remove(userId);

    }

    /**
     * 从在线用户列表里面得到一个用户信息
     * @param session_id
     * @return
     */
    public synchronized UserInfo getUserOnLine(String userId) {
        return (UserInfo) onlinemap.get(userId);
    }

    /**
     * 得到在线用户列表Map
     * @return
     */
    public synchronized HashMap getUserOnLineMap() {
        return this.onlinemap;
    }

    /**
     * 添加一个用户在踢线列表
     * @param userInfo
     */
    public synchronized void addKickLineMap(UserInfo userInfo) {
        if (userInfo != null) {
            kicklinemap.put(String.valueOf(userInfo.getUserId()), userInfo);
        }
    }

    /**
     * 从踢线列表里面删除一个用户
     * @param userInfo
     */
    public synchronized void delUserKickLine(String userId) {

        kicklinemap.remove(userId);

    }

    /**
     * 从踢线列表里面得到一个用户信息
     * @param userId
     * @return
     */
    public synchronized UserInfo getUserKickLine(String userId) {
        return (UserInfo) kicklinemap.get(userId);
    }

    /**
     * 得到踢线列表Map
     * @return
     */
    public synchronized HashMap getUserKickLineMap() {
        return this.kicklinemap;
    }

}
