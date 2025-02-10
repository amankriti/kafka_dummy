Prerequisites

Ensure you have the following installed:

Java 8 or higher (Check with java -version)

Eclipse IDE (or any Java-supported IDE)

Project Setup in Eclipse

Step 1: Create a Java Project

Open Eclipse IDE.

Click on File → New → Java Project.

Name it IgniteMessaging and click Finish.

Step 2: Add Java RMI Support

Right-click the project → Properties.

Navigate to Java Build Path → Libraries.

Ensure JRE System Library is set to Java 8 or higher.

Step 3: Add a module-info.java (For Java 9+)

Create module-info.java inside src/ and add:

module IgniteMessaging {
    requires java.rmi;
    exports com.ignite;
    opens com.ignite;
}

Running the Application

The system consists of three components:

Ignite Server (Handles message passing)

Ignite Consumer (Listens for messages dynamically)

Ignite Producer (Publishes messages)

Step 1: Start the Ignite Server

Open Eclipse.

Run IgniteServer.java.

Console output should show: Ignite Server is running....

Step 2: Start the Ignite Consumer

Open a new terminal/console in Eclipse.

Run IgniteConsumerImpl.java.

Console output should show: Consumer subscribed to topic: dynamic_topic.

Step 3: Start the Ignite Producer

Open another new terminal/console.

Run IgniteProducerImpl.java.

Enter a topic and message when prompted.

The consumer should receive the message, process it, and send a response back to the producer.

Example:

Enter topic: orders
Enter message: Order ID 123
[Consumer] Received message from orders: Order ID 123
[Producer] Received response from orders: Processed: Order ID 123




Troubleshooting(Optinal, if you are geeting errors)

java.rmi.NotBoundException: Ignite

Ensure IgniteServer is running before starting the producer or consumer.

java.rmi.AccessException

Check if the module-info.java file includes:

requires java.rmi;
exports com.ignite;
opens com.ignite;

The package java.rmi is not accessible

Run with:

java --add-modules java.rmi -cp . IgniteServer




