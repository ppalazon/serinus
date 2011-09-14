package org.serinus.ejb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.jboss.logging.Logger;
import org.jboss.seam.solder.logging.Category;
import org.serinus.cache.TaskCache;
import org.serinus.data.Task;

@MessageDriven(mappedName = "queue/serinusTaskQueue", activationConfig = {
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })
public class TaskListener implements MessageListener {

	@Inject
	@Category("serinus-jms")
	private Logger log;

	@Inject
	TaskCache taskCache;

	@Override
	public void onMessage(Message arg0) {
		try {
			taskCache.addTask(((Task) arg0.getObjectProperty("task")));
		} catch (JMSException e) {

		}
	}

}
