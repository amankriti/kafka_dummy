package com.ignite;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class IgniteConsumerImpl extends UnicastRemoteObject implements IgniteConsumer {
	protected IgniteConsumerImpl() throws RemoteException {
		super();
	}

	public void onMessageReceived(String topic, String message, IgniteProducer producer) throws RemoteException {
		System.out.println("[Consumer] Message received on topic '" + topic + "': " + message);
		String response = "Processed: " + message;
		producer.onResponseReceived(topic, response);
	}

	public static void main(String[] args) {
		try {

			Registry registry = LocateRegistry.getRegistry("localhost", 1099);
			Ignite ignite = (Ignite) registry.lookup("Ignite");

			IgniteConsumerImpl consumer = new IgniteConsumerImpl();
			Scanner scanner = new Scanner(System.in);

			System.out.print("Enter topic to subscribe: ");
			String topic = scanner.nextLine();

			ignite.subscribe(topic, consumer);
			System.out.println("[Consumer] Subscribed to topic: " + topic);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
