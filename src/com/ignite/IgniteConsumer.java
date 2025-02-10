package com.ignite;

import java.rmi.*;

public interface IgniteConsumer extends Remote {
	void onMessageReceived(String topic, String message, IgniteProducer producer) throws RemoteException;
}
