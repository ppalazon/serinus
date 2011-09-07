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
		
		
		Task task = serinusParser.parser(message);
		
		Response postTask = serinusControlHttpProxy.getSerinusPost().postTask(task);
		
		if(postTask.getStatus()!=Status.OK.getStatusCode())
		{
			log.error("Can't connect to Serinus Control");
			
			mesg.setBody("Error contact with control");
		}
		else
		{
			mesg.setBody("OK");
		}
		
		log.info(String.valueOf(task));
		
		return mesg;
	}

}
