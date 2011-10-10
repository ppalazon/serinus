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

import java.util.Date;
import java.util.UUID;

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
import org.serinus.exception.SerinusBotException;
import org.serinus.http.proxy.SerinusControlHttpProxy;
import org.slf4j.cal10n.LocLogger;

public class SerinusMessageListener implements MessageListener
{

	private LocLogger log = LoggerFactory.loggerFactory().getLogger(
			Category.BEAN);

	@Inject
	SerinusControlHttpProxy serinusControlHttpProxy;

	@Override
	public void processMessage(Chat chat, Message message)
	{
		try
		{
			Message mesg = parserTaskMessage(message);
			chat.sendMessage(mesg);
		} catch (XMPPException e)
		{
			log.error(e.getMessage());
		} catch (SerinusBotException e)
		{
			log.error(e.getMessage());
		}

	}

	public Message parserTaskMessage(Message message)
			throws SerinusBotException
	{
		if (message.getBody() == null)
		{
			throw new SerinusBotException("Message without body");
		}
		Message mesg = new Message();

		mesg.setSubject("Parser task");

		Task task = new Task();
		task.setOriginal(message.getBody());
		task.setDate(new Date());
		task.setAuthor(message.getFrom());
		task.setUuid(UUID.randomUUID().toString());

		Response postTask = serinusControlHttpProxy.getSerinusPost().postTask(
				task);

		if (postTask.getStatus() != Status.OK.getStatusCode())
		{
			log.error("Can't connect to Serinus Control");

			mesg.setBody("Error contact with control");
		} else
		{
			mesg.setBody("OK");
		}

		log.info(String.valueOf(task));

		return mesg;
	}

}
