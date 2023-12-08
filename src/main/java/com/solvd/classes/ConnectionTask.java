package com.solvd.classes;

import org.apache.logging.log4j.Logger;

public class ConnectionTask {
    public static Runnable getConnectionTask (int threadSleep, Logger LOGGER, ConnectionPool connectionPool) {
        return () -> {
            try {
                Connection connection = connectionPool.getConnection();
                LOGGER.info("{} got connection {}", Thread.currentThread().getName(), connection.connectionID);
                Thread.sleep(threadSleep);
                connectionPool.releaseConnection(connection);
                LOGGER.info("Connection {} released", connection.connectionID);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
