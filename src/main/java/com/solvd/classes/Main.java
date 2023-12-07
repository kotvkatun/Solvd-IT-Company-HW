package com.solvd.classes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    private final static Logger LOGGER = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        //MenuSwitch menu = new MenuSwitch();
        //menu.mainMenuSwitch();
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Runnable connectionGet = () -> {
            try {
                Connection connection = connectionPool.getConnection();
                LOGGER.info("{} got connection {}", Thread.currentThread().getName(), connection.connectionID);
                Thread.sleep(2000);
                connectionPool.releaseConnection(connection);
                LOGGER.info("Connection {} released", connection.connectionID);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        Runnable connectionGet1 = () -> {
            try {
                Connection connection = connectionPool.getConnection();
                LOGGER.info("{} got connection {}", Thread.currentThread().getName(), connection.connectionID);
                Thread.sleep(1000);
                connectionPool.releaseConnection(connection);
                LOGGER.info("Connection {} released", connection.connectionID);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        ArrayList<Runnable> taskList = new ArrayList<>(List.of(
                connectionGet,
                connectionGet,
                connectionGet,
                connectionGet,
                connectionGet));
        Thread thread1 = new Thread(connectionGet1);
        Thread thread2 = new Thread(connectionGet1);
        try {
            thread1.start();
            thread2.start();
            for(Runnable task : taskList) {
                executorService.execute(task);
            }
            thread1.join();
            thread2.join();
            executorService.shutdown();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

