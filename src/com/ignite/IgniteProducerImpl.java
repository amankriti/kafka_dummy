package com.ignite;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class IgniteProducerImpl extends UnicastRemoteObject implements IgniteProducer {
	protected IgniteProducerImpl() throws RemoteException {
		super();
	}

	public void onResponseReceived(String topic, String response) throws RemoteException {
		System.out.println("[Producer] Received response from " + topic + ": " + response);
	}

	public static void main(String[] args) {
		try {

			Registry registry = LocateRegistry.getRegistry("localhost", 1099);
			Ignite ignite = (Ignite) registry.lookup("Ignite");

			// Initialize producer
			IgniteProducerImpl producer = new IgniteProducerImpl();
			Scanner scanner = new Scanner(System.in);

			while (true) {
				System.out.print("Enter topic: ");
				String topic = scanner.nextLine();
				System.out.print("Enter message: ");
				String message = scanner.nextLine();

				ignite.publish(topic, message, producer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
