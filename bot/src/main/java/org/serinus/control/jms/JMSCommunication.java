package org.serinus.control.jms;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.api.jms.HornetQJMSClient;
import org.hornetq.integration.transports.netty.NettyConnectorFactory;

@ApplicationScoped
public class JMSCommunication {

	private static String PORT_PROP_NAME = "localhost";
	Connection connection = null;

	public JMSCommunication() {

		try {
			// Step 1. Directly instantiate the JMS Queue object.
			Queue queue = HornetQJMSClient.createQueue("exampleQueue");

			// Step 2. Instantiate the TransportConfiguration object which
			// contains the knowledge of what transport to use,
			// The server port etc.

			Map<String, Object> connectionParams = new HashMap<String, Object>();
			connectionParams.put(PORT_PROP_NAME, 5445);

			TransportConfiguration transportConfiguration = new TransportConfiguration(
					NettyConnectorFactory.class.getName(), connectionParams);

			// Step 3 Directly instantiate the JMS ConnectionFactory object
			// using that TransportConfiguration
			ConnectionFactory cf = HornetQJMSClient
					.createConnectionFactory(transportConfiguration);

			// Step 4.Create a JMS Connection
			connection = cf.createConnection();

			// Step 5. Create a JMS Session
			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);

			// Step 6. Create a JMS Message Producer
			MessageProducer producer = session.createProducer(queue);

			// Step 7. Create a Text Message
			TextMessage message = session
					.createTextMessage("This is a text message");

			System.out.println("Sent message: " + message.getText());

			// Step 8. Send the Message
			producer.send(message);

			// Step 9. Create a JMS Message Consumer
			MessageConsumer messageConsumer = session.createConsumer(queue);

			// Step 10. Start the Connection
			connection.start();

			// Step 11. Receive the message
			TextMessage messageReceived = (TextMessage) messageConsumer
					.receive(5000);

			System.out
					.println("Received message: " + messageReceived.getText());
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
}
