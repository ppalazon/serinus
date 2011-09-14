package org.serinus.ui;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.jboss.seam.solder.logging.Category;
import org.richfaces.application.push.MessageException;
import org.richfaces.application.push.TopicKey;
import org.richfaces.application.push.TopicsContext;
import org.serinus.cache.TaskCache;
import org.serinus.data.Task;
import org.serinus.push.PushMessage;
import org.serinus.push.TopicsInitializer;

@ManagedBean
public class TaskMonitor {
	
	@Inject
	@Category("serinus")
	private Logger log;
	
	@Inject
	private TaskCache taskCache;
	
	private static final String GENERAL = "general";

	private transient TopicsContext topicsContext;

	public String getGeneralSubtopic() {
		return GENERAL;
	}

	private TopicsContext getTopicsContext() {
		if (topicsContext == null) {
			topicsContext = TopicsContext.lookup();
		}
		return topicsContext;
	}

	public void send(String message) {
		try {
			PushMessage mesg = new PushMessage();
			mesg.setMsg(message);
			getTopicsContext().publish(new TopicKey(TopicsInitializer.TOPIC_NAME, getGeneralSubtopic()), mesg);
			log.info(message);
		} catch (MessageException e) {
			log.error(e.getMessage());
		}
	}
	
	public List<Task> getAllMessages()
	{
		return taskCache.getAllMessages();		
	}

}
