package org.serinus.cache;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.infinispan.Cache;
import org.infinispan.manager.CacheContainer;
import org.serinus.cache.data.CacheKey;
import org.serinus.cache.data.CacheType;
import org.serinus.data.Task;

public class TaskCache {

	@Resource(name = "java:jboss/infinispan/web")
	private CacheContainer container;
	private Cache<CacheKey, List<String>> cache;

	@PostConstruct
	public void create() {
		this.cache = this.container.getCache();
	}
	
	public CacheKey addTask(Task task)
	{
		CacheKey ck = new CacheKey(CacheType.AUTHOR, task.getUser()); 
		if(!cache.containsKey(ck))
		{
			List<String> tasks = new ArrayList<String>();
			cache.put(ck, tasks);
		}
		List<String> list = cache.get(ck);
		list.add(task.getTask());
		
		return ck;
	}

}
