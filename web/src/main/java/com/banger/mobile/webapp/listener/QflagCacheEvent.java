package com.banger.mobile.webapp.listener;



import java.io.Serializable;

import com.opensymphony.oscache.base.Cache;
import com.opensymphony.oscache.base.CacheEntry;
import com.opensymphony.oscache.base.events.CacheEvent;

public class QflagCacheEvent extends CacheEvent implements Serializable {
	/**
	 * The cache where the entry resides.
	 */
	private Cache map = null;

	/**
	 * The entry that the event applies to.
	 */
	private CacheEntry entry = null;

	/**
	 * Constructs a cache entry event object with no specified origin
	 * 
	 * @param map
	 *            The cache map of the cache entry
	 * @param entry
	 *            The cache entry that the event applies to
	 */
	public QflagCacheEvent(Cache map, CacheEntry entry) {
		this(map, entry, null);
	}

	/**
	 * Constructs a cache entry event object
	 * 
	 * @param map
	 *            The cache map of the cache entry
	 * @param entry
	 *            The cache entry that the event applies to
	 * @param origin
	 *            The origin of this event
	 */
	public QflagCacheEvent(Cache map, CacheEntry entry, String origin) {
		super(origin);
		this.map = map;
		this.entry = entry;
	}

	/**
	 * Retrieve the cache entry that the event applies to.
	 */
	public CacheEntry getEntry() {
		return entry;
	}

	/**
	 * Retrieve the cache entry key
	 */
	public String getKey() {
		return entry.getKey();
	}

	/**
	 * Retrieve the cache map where the entry resides.
	 */
	public Cache getMap() {
		return map;
	}

	public String toString() {
		return "key=" + entry.getKey();
	}
}