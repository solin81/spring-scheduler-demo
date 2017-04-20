package com.fujitsu;

import com.fujitsu.task.ApacheHttpClientRequestTask;
import com.fujitsu.task.SimpleTask;
import com.fujitsu.task.SpringHttpRequestTask;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.TaskScheduler;

public class Application {

    // Create app config
    private static final ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
    // Get first bean - the scheduler
    private static final TaskScheduler taskScheduler = (TaskScheduler) context.getBean("task-scheduler");


    public static void main(String[] args) {
//        runSimpleTasks();
//        runSpringHttpTasks();

        ApacheHttpClientRequestTask task = context.getBean(ApacheHttpClientRequestTask.class);
        taskScheduler.scheduleAtFixedRate(task, 5000L);
    }

    // Helper method for investigation purpose
    // it launches 2 simple tasks writing
    private static void runSimpleTasks() {
        // Get beans with tasks - the same class but different constructor argument
        SimpleTask simpleTask1 = context.getBean(SimpleTask.class, "MS1");
        SimpleTask simpleTask2 = context.getBean(SimpleTask.class, "MS2");

        // Schedule the tasks
        taskScheduler.scheduleAtFixedRate(simpleTask1, 5000L);
        taskScheduler.scheduleAtFixedRate(simpleTask2, 3000L);
    }

    // Helper method scheduling HTTP task
    private static void runSpringHttpTasks() {
        // Get second bean - task hitting web page
        SpringHttpRequestTask springHttpRequestTask = context.getBean(SpringHttpRequestTask.class);

        // Schedule task
        taskScheduler.scheduleAtFixedRate(springHttpRequestTask, 5000L);
    }

}
