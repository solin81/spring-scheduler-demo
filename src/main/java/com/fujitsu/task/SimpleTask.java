package com.fujitsu.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class SimpleTask implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleTask.class);
    private final String name;

    public SimpleTask(String name) {
        this.name = name;
    }

    @Override
    public void run() {
//        System.out.println("Thread: " + Thread.currentThread().getName() + " with name: " + name + " working ... time - " + new Date());
        LOGGER.info("Thread: " + Thread.currentThread().getName() + " with name: " + name + " working ... time - " + new Date());
    }

}
