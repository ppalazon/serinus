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
import org.serinus.data.Task;

@ApplicationScoped
public class JMSCommunication {

	private static String PORT_PROP_NAME = "localhost";

//	public JMSCommunication() {
//
//		try {
//			Hashtable<String, String> env = new Hashtable<String, String>();
//			env.put(Context.PROVIDER_URL, "jnp://localhost:1099");
//			env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
//			env.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
//			Context ctx = new InitialContext(env);
//
//			// Step 2. Lookup the connection factory
//			ConnectionFactory cf = (ConnectionFactory)ctx.lookup("/ConnectionFactory");
//
//			// Step 3. Lookup the JMS queue
//			Queue queue = (Queue)ctx.lookup("/queue/serinusTaskQueue");
//
//			// Step 4. Create the JMS objects to connect to the server and manage a session
//			Connection connection = cf.createConnection();
//			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//
//			// Step 5. Create a JMS Message Producer to send a message on the queue
//			MessageProducer producer = session.createProducer(queue);
//
//			// Step 6. Create a Text Message and send it using the producer
//			TextMessage message = session.createTextMessage("Hello, HornetQ!");
//			producer.send(message);
//			System.out.println("Sent message: " + message.getText());
//
//			// now that the message has been sent, let's receive it
//
//			// Step 7. Create a JMS Message Consumer to receive message from the queue
//			MessageConsumer messageConsumer = session.createConsumer(queue);
//
//			// Step 8. Start the Connection so that the server starts to deliver messages
//			connection.start();
//
//			// Step 9. Receive the message
//			TextMessage messageReceived = (TextMessage)messageConsumer.receive(5000);
//			System.out.println("Received message: " + messageReceived.getText());
//
//			// Finally, we clean up all the JMS resources
//			connection.close();
//			
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
////			if (connection != null) {
////				try {
////					connection.close();
////				} catch (JMSException e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				}
////			}
//		}
//	}

	public void sendTask(Task task) throws HornetQException {
	}
}
