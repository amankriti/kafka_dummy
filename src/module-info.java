module IgniteMessaging {
    requires java.rmi;
    exports com.ignite; // Exposes your package to Java RMI
    opens com.ignite; // Allows reflection access to RMI classes
}
