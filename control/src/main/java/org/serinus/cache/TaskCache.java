package org.serinus.cache;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.infinispan.Cache;
import org.serinus.cache.data.CacheKey;
import org.serinus.cache.data.CacheType;
import org.serinus.data.Task;

public class TaskCache {

	@Inject
	@Default
	private Cache<CacheKey, MessageList> cache;
	
	public CacheKey addTask(Task task)
	{
		CacheKey ck = new CacheKey(CacheType.AUTHOR, task.getAuthor()); 
		if(!cache.containsKey(ck))
		{
			MessageList ml = new MessageList();
			cache.put(ck, ml);
		}
		MessageList ml = cache.get(ck);
		ml.getMessages().add(task.getOriginal());
		
		return ck;
	}

}
