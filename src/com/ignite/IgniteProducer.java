 package com.ignite;

 import java.rmi.*;

 public interface IgniteProducer extends Remote {
     void onResponseReceived(String topic, String response) throws RemoteException;
 }

