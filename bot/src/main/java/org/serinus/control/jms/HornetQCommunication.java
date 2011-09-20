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

package org.serinus.control.jms;

import java.util.HashMap;
import java.util.Hashtable;

import javax.enterprise.context.ApplicationScoped;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.hornetq.api.core.HornetQException;
import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.api.core.client.ClientMessage;
import org.hornetq.api.core.client.ClientProducer;
import org.hornetq.api.core.client.ClientSession;
import org.hornetq.api.core.client.ClientSessionFactory;
import org.hornetq.api.core.client.HornetQClient;
import org.hornetq.api.core.client.ServerLocator;
import org.hornetq.core.remoting.impl.netty.NettyConnectorFactory;
import org.serinus.data.Task;

@ApplicationScoped
public class HornetQCommunication {

	private static String PORT_PROP_NAME = "localhost";

	public HornetQCommunication() {

		try {
			
			HashMap<String, Object> tcconfiguration = new HashMap<String, Object>();
			tcconfiguration.put("host", PORT_PROP_NAME);
			tcconfiguration.put("port", 5445);
			
			TransportConfiguration tc = new TransportConfiguration(NettyConnectorFactory.class.getName(), tcconfiguration);
			
			ServerLocator locator = HornetQClient.createServerLocatorWithoutHA(tc);
			
			ClientSessionFactory factory = locator.createSessionFactory(tc);
			
			ClientSession session = factory.createSession();
			
			session.createQueue("", "serinusTaskQueue");
			
			ClientProducer producer = session.createProducer("serinusTaskQueue");
			
			ClientMessage message = session.createMessage(true);
			
			message.getBodyBuffer().writeString("hello");
			
			producer.send(message);
			
			session.start();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
//			if (connection != null) {
//				try {
//					connection.close();
//				} catch (JMSException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
		}
	}

	public void sendTask(Task task) throws HornetQException {
	}
}
