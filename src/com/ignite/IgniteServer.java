package com.ignite;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.*;
import java.util.*;

public class IgniteServer implements Ignite {
	private final ConcurrentHashMap<String, List<IgniteConsumer>> subscribers = new ConcurrentHashMap<>();

	public void publish(String topic, String message, IgniteProducer producer) throws RemoteException {
		System.out.println("Published to " + topic + ": " + message);
		List<IgniteConsumer> consumerList = subscribers.get(topic);

		if (consumerList == null || consumerList.isEmpty()) {
			System.out.println("No subscribers for topic: " + topic);
			return;
		}

		for (IgniteConsumer consumer : consumerList) {
			System.out.println("Notifying consumer...");
			consumer.onMessageReceived(topic, message, producer);
		}
	}

	public void subscribe(String topic, IgniteConsumer consumer) throws RemoteException {
		subscribers.putIfAbsent(topic, new ArrayList<>());
		subscribers.get(topic).add(consumer);
		System.out.println("Consumer subscribed to topic: " + topic);
	}

	public static void main(String[] args) {
		try {
			IgniteServer server = new IgniteServer();
			// Ignite stub = (Ignite) UnicastRemoteObject.exportObject(server, 0);

			Ignite stub = (Ignite) UnicastRemoteObject.exportObject(server, 1099);

			Registry registry = LocateRegistry.createRegistry(1099);
			registry.rebind("Ignite", stub);
			System.out.println("Ignite Server is running...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
