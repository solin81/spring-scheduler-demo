package com.fujitsu;

import com.fujitsu.task.SimpleTask;
import com.fujitsu.task.SpringHttpRequestTask;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.TaskScheduler;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        TaskScheduler taskScheduler = (TaskScheduler) context.getBean("task-scheduler");

        //runSimpleTasks(context, taskScheduler);

        SpringHttpRequestTask springHttpRequestTask = context.getBean(SpringHttpRequestTask.class);
        taskScheduler.scheduleAtFixedRate(springHttpRequestTask, 5000L);

    }

    private static void runSimpleTasks(ApplicationContext context, TaskScheduler taskScheduler) {
        SimpleTask simpleTask1 = context.getBean(SimpleTask.class, "MS1");
        SimpleTask simpleTask2 = context.getBean(SimpleTask.class, "MS2");

        taskScheduler.scheduleAtFixedRate(simpleTask1, 5000L);
        taskScheduler.scheduleAtFixedRate(simpleTask2, 3000L);
    }

}
