package org.serinus.xmpp;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.weld.logging.Category;
import org.jboss.weld.logging.LoggerFactory;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.serinus.data.Task;
import org.serinus.http.proxy.SerinusControlHttpProxy;
import org.serinus.parser.SerinusParser;
import org.slf4j.cal10n.LocLogger;


public class SerinusMessageListener implements MessageListener {
	
	private LocLogger log = LoggerFactory.loggerFactory().getLogger(Category.BEAN);
	
	@Inject
	SerinusParser serinusParser;
	
	@Inject
	SerinusControlHttpProxy serinusControlHttpProxy;
	
	@Override
	public void processMessage(Chat chat, Message message) {
		
		Message mesg = parserTaskMessage(message);
		
		try {
			chat.sendMessage(mesg);
		} catch (XMPPException e) {
			log.error(e.getMessage());
		}
		
	}
	
	public Message parserTaskMessage(Message message)
	{
		Message mesg = new Message();
		
		mesg.setSubject("Parser task");
		mesg.setBody("OK");
		
		Task task = serinusParser.parser(message.getBody());
		
		Response postTask = serinusControlHttpProxy.getSerinusPost().postTask(task);
		
		if(postTask.getStatus()!=Status.OK.getStatusCode())
		{
			log.error("Can't connect to Serinus Control");
		}
		
		log.info(String.valueOf(task));
		
		return mesg;
	}

}
