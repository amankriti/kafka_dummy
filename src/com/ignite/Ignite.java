package com.ignite;

import java.rmi.*;

public interface Ignite extends Remote {
    void publish(String topic, String message, IgniteProducer producer) throws RemoteException;
    void subscribe(String topic, IgniteConsumer consumer) throws RemoteException;
}

