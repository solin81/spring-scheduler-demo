package com.fujitsu;

import com.fujitsu.task.SimpleTask;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@ComponentScan
public class ApplicationConfig {

    @Bean(name = "task-scheduler")
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(2);
        return taskScheduler;
    }

    @Bean
    @Scope("prototype")
    public SimpleTask simpleTask(String name) {
        return new SimpleTask(name);
    }

}
