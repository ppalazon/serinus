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
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.api.jms.HornetQJMSClient;
import org.hornetq.integration.transports.netty.NettyConnectorFactory;
import org.serinus.data.Task;

@ApplicationScoped
public class JMSCommunication {

	private static String PORT_PROP_NAME = "localhost";
	Connection connection;
	Queue queue;
	MessageProducer messageProducer;
	Session session;

	public JMSCommunication() {

		try {
			// Step 1. Directly instantiate the JMS Queue object.
			Queue queue = HornetQJMSClient.createQueue("serinusTaskQueue");

			// Step 2. Instantiate the TransportConfiguration object which
			// contains the knowledge of what transport to use,
			// The server port etc.

			Map<String, Object> connectionParams = new HashMap<String, Object>();
			connectionParams.put("host", PORT_PROP_NAME);
			connectionParams.put("port", 5455);

			TransportConfiguration transportConfiguration = new TransportConfiguration(
					NettyConnectorFactory.class.getName(), connectionParams);

			// Step 3 Directly instantiate the JMS ConnectionFactory object
			// using that TransportConfiguration
			ConnectionFactory cf = HornetQJMSClient
					.createConnectionFactory(transportConfiguration);

			// Step 4.Create a JMS Connection
			connection = cf.createConnection();
			
			connection.start();

			// Step 5. Create a JMS Session
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Step 6. Create a JMS Message Producer
			messageProducer = session.createProducer(queue);

			

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void sendTask(Task task) throws JMSException {
		// Step 7. Create a Text Message
		ObjectMessage message = session
				.createObjectMessage(task);

		// Step 8. Send the Message
		messageProducer.send(message);
	}
}
