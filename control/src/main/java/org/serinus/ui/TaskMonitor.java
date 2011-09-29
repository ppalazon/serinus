package org.serinus.ui;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import org.serinus.cache.TaskCache;
import org.serinus.data.Task;

@ManagedBean
public class TaskMonitor
{

    @Inject
    TaskCache taskCache;

    public List<Task> getAllMessages()
    {
	return taskCache.getAllMessages();
    }

}
