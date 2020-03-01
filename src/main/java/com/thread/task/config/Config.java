package com.thread.task.config;

import com.thread.task.domain.Number;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Configuration
public class Config {

    @Bean
    public BlockingQueue<Number> queue() {
        return new LinkedBlockingQueue<>(20);
    }
}
