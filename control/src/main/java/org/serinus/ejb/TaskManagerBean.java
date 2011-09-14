package org.serinus.ejb;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.jboss.seam.solder.logging.Category;
import org.serinus.cache.TaskCache;
import org.serinus.data.Task;
import org.serinus.ejb.TaskManager;

public @Stateless class TaskManagerBean implements TaskManager {
	
	@Inject
	@Category("serinus-ejb")
	private Logger log;
	
	@Inject
	TaskCache taskCache;

	@Override
	public void addTask(Task task) {
		log.info(String.valueOf(task));		
		taskCache.addTask(task);
	}

	@Override
	public void removeTask(String uuid) {
		// TODO Auto-generated method stub
		
	}

}
