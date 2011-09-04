package org.serinus.cache;

import javax.enterprise.inject.Produces;

import org.infinispan.cdi.Infinispan;
import org.infinispan.config.Configuration;
import org.infinispan.eviction.EvictionStrategy;

public class ConfigCache {
	
	@Infinispan("serinus-cache")
    @SerinusCache
    @Produces
    public Configuration serinusCacheConfiguration() {
        return new Configuration().fluent()
                    .eviction().strategy(EvictionStrategy.FIFO).maxEntries(4)
                    .build();
    }

}
