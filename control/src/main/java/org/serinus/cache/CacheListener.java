package org.serinus.cache;

import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryCreated;
import org.infinispan.notifications.cachelistener.event.CacheEntryCreatedEvent;

@Listener
public class CacheListener {

	@CacheEntryCreated
	public void print(CacheEntryCreatedEvent event) {
		System.out.println("New entry " + event.getKey()
				+ " created in the cache");
	}

}
