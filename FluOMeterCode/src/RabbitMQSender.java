import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


public class RabbitMQSender {
	
	private final static String QUEUE_NAME = "queue";
	private static final String HOST = "localhost";
	
	public RabbitMQSender(String message) {
		
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(HOST);
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
			System.out.println("Sent: " + message);
			channel.close();
			connection.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		for(int i = 0; i < 5; i++) {
			new RabbitMQSender("Hello: " + i);
		}
	}

}
