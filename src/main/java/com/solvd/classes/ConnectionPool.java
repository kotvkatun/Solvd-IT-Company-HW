package com.solvd.classes;

import java.util.concurrent.ConcurrentLinkedDeque;

public enum ConnectionPool {
    INSTANCE;
    private static class ConnectionPoolService {
        private static final int POOL_SIZE = 5;
        private final ConcurrentLinkedDeque<Connection> connectionsDeque;
        private ConnectionPoolService() {
            connectionsDeque = new ConcurrentLinkedDeque<>();
            for (int i = 0; i < POOL_SIZE; i++) {
                connectionsDeque.add(new Connection());
            }
        }
        public synchronized Connection getConnection() throws InterruptedException {
            if (connectionsDeque.peekLast() == null) {
                wait();
            }
            return connectionsDeque.pop();
        }
        public synchronized void releaseConnection(Connection connection){
            connectionsDeque.add(connection);
            notify();
        }
    }
    private final ConnectionPoolService connectionPoolService = new ConnectionPoolService();

    public Connection getConnection() throws InterruptedException{
        return connectionPoolService.getConnection();
    }
    public void releaseConnection(Connection connection) {
        connectionPoolService.releaseConnection(connection);
    }
}
