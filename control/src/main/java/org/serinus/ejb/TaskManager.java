package org.serinus.ejb;

import javax.ejb.Remote;

import org.serinus.data.Task;

@Remote
public interface TaskManager {
	
	public void addTask(Task task);
	public void removeTask(String uuid);

}
