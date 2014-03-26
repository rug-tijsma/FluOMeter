import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class RabbitMQReceiver {

	private static final String QUEUE_NAME = "queue";
	private static final String HOST = "localhost";

	private ArrayList<String> messages = new ArrayList<String>();
	private long start_time = System.currentTimeMillis();
	private long end_time = System.currentTimeMillis();

	private RabbitMQReceiver() {

		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(HOST);
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			System.out.println("Waiting for messages...");
			QueueingConsumer consumer = new QueueingConsumer(channel);
			channel.basicConsume(QUEUE_NAME, true, consumer);

			while (true) {
				QueueingConsumer.Delivery delivery = consumer.nextDelivery();
				String message = new String(delivery.getBody());
				processMessage(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void sendData() {
		System.out.println("Sending data");

	}

	private void processMessage(String message) {
		messages.add(message);
	
		System.out.println("Received: " + message);

		end_time = System.currentTimeMillis();
		if (end_time - start_time >= 10000) {
			start_time = end_time;
			sendData();
		}
	}

	public static void main(String args[]) {
		new RabbitMQReceiver();
	}

}
