package org.serinus.graph.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.serinus.data.Task;
import org.serinus.graph.GraphManager;
import org.serinus.graph.domain.Doing;
import org.serinus.graph.interceptor.GraphTransaction;

@ApplicationScoped
@GraphTransaction
public class SerinusDao
{
    @Inject
    private GraphManager graphManager;

    public void createSerinusTask(Task task)
    {	
	Doing doing = new Doing(graphManager.getGraphDatabaseService().createNode());
	doing.setCreated(task.getDate());
	doing.setUuid(task.getUuid());
	doing.setDoing(task.getOriginal());
    }

}
