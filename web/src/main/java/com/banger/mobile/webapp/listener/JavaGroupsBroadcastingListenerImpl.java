package com.banger.mobile.webapp.listener;

import com.opensymphony.oscache.base.events.CacheEntryEvent;
import com.opensymphony.oscache.plugins.clustersupport.ClusterNotification;
import com.opensymphony.oscache.plugins.clustersupport.JavaGroupsBroadcastingListener;
import org.apache.log4j.Logger;

public class JavaGroupsBroadcastingListenerImpl extends JavaGroupsBroadcastingListener {
    private static final Logger logger = Logger.getLogger(JavaGroupsBroadcastingListenerImpl.class);

    public void handleClusterNotification(ClusterNotification message) {

        switch (message.getType()) {
            case CacheConstants.CLUSTER_ENTRY_ADD:
                logger.info("集群新增:" + message.getData());
                if (message.getData() instanceof QflagCacheEvent) {
                    QflagCacheEvent event = (QflagCacheEvent) message.getData();
                    cache.putInCache(event.getKey(), event.getEntry().getContent(), null, null,
                        CLUSTER_ORIGIN);
                }
                break;
            case CacheConstants.CLUSTER_ENTRY_UPDATE:
                logger.info("集群更新:" + message.getData());
                if (message.getData() instanceof QflagCacheEvent) {
                    QflagCacheEvent event = (QflagCacheEvent) message.getData();
                    // cache.flushEntry(event.getKey());
                    cache.putInCache(event.getKey(), event.getEntry().getContent(), null, null,
                        CLUSTER_ORIGIN);
                }
                break;
            case CacheConstants.CLUSTER_ENTRY_DELETE:
                logger.info("集群删除:" + message.getData());
                if (message.getData() instanceof QflagCacheEvent) {
                    QflagCacheEvent event = (QflagCacheEvent) message.getData();
                    // cache.removeEntry(event.getKey(),event.getOrigin());
                    cache.removeEntry(event.getKey());
                }
                break;
        }

    }

    public void cacheEntryAdded(CacheEntryEvent event) {
        super.cacheEntryAdded(event);
        logger.info("属性添加");
        if (!CLUSTER_ORIGIN.equals(event.getOrigin())) {
            sendNotification(new ClusterNotification(CacheConstants.CLUSTER_ENTRY_ADD,
                new QflagCacheEvent(event.getMap(), event.getEntry(), CLUSTER_ORIGIN)));
        }
    }

    // @Override
    // public void cacheEntryFlushed(CacheEntryEvent event) {
    //
    // super.cacheEntryFlushed(event);
    // if(!CLUSTER_ORIGIN.equals(event.getOrigin())) {
    // sendNotification(new
    // ClusterNotification(CacheConstants.CLUSTER_ENTRY_ADD, new
    // UcallCacheEvent(event.getMap(),event.getEntry(),CLUSTER_ORIGIN)));
    // }
    // }

    @Override
    public void cacheEntryRemoved(CacheEntryEvent event) {
        logger.info("属性移除");
        super.cacheEntryRemoved(event);
        if (!CLUSTER_ORIGIN.equals(event.getOrigin())) {
            sendNotification(new ClusterNotification(CacheConstants.CLUSTER_ENTRY_DELETE,
                new QflagCacheEvent(event.getMap(), event.getEntry(), CLUSTER_ORIGIN)));
        }
    }

    @Override
    public void cacheEntryUpdated(CacheEntryEvent event) {
        logger.info("属性更新");
        super.cacheEntryUpdated(event);
        if (!CLUSTER_ORIGIN.equals(event.getOrigin())) {
            sendNotification(new ClusterNotification(CacheConstants.CLUSTER_ENTRY_UPDATE,
                new QflagCacheEvent(event.getMap(), event.getEntry(), CLUSTER_ORIGIN)));
        }
    }

}
