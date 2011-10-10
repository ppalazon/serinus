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

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.infinispan.Cache;
import org.serinus.cache.data.CacheKey;
import org.serinus.cache.data.CacheType;
import org.serinus.data.Task;

public class TaskCache
{

	@Inject
	@Default
	private Cache<CacheKey, MessageList> cache;

	public CacheKey addTask(Task task)
	{
		CacheKey ck = new CacheKey(CacheType.AUTHOR, task.getAuthor());
		if (!cache.containsKey(ck))
		{
			MessageList ml = new MessageList();
			cache.put(ck, ml);
		}
		MessageList ml = cache.get(ck);
		ml.getMessages().add(task);

		CacheKey gen = new CacheKey(CacheType.GENERAL, "all");
		if (!cache.containsKey(gen))
		{
			MessageList mg = new MessageList();
			cache.put(gen, mg);
		}
		MessageList mg = cache.get(gen);
		mg.getMessages().add(task);

		return ck;
	}

	public List<Task> getAllMessages()
	{
		CacheKey gen = new CacheKey(CacheType.GENERAL, "all");
		if (cache.containsKey(gen))
		{
			return cache.get(gen).getMessages();
		}

		return new ArrayList<Task>();

	}

}
