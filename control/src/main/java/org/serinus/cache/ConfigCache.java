/*
 * Copyright 2011. Pablo Palazon (pablo.palazon@gmail.com)
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.serinus.cache;

import javax.enterprise.inject.Produces;

import org.infinispan.cdi.Infinispan;
import org.infinispan.config.Configuration;
import org.infinispan.config.Configuration.CacheMode;
import org.infinispan.eviction.EvictionStrategy;

public class ConfigCache {
	
	@Infinispan("serinus-cache")
    @SerinusCache
    @Produces
    public Configuration serinusCacheConfiguration() {
        return new Configuration().fluent()
			.mode(CacheMode.LOCAL).dataContainer().storeAsBinary()
            .eviction().strategy(EvictionStrategy.FIFO).maxEntries(4)
            .build();
    }

}
