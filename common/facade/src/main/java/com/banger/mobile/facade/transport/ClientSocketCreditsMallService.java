package com.banger.mobile.facade.transport;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yangy
 * Date: 13-8-14
 * Time: 上午8:57
 * To change this template use File | Settings | File Templates.
 */
public interface ClientSocketCreditsMallService {

    public String getMessage(Map<String, Object> headMap, Map<String, Object> bodyMap, List<Map<String, Object>> row);
}
