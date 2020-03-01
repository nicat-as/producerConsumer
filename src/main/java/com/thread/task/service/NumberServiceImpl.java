package com.thread.task.service;

import com.thread.task.domain.Number;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class NumberServiceImpl implements NumberService {

    private final Consumer consumer;
    private final Producer producer;

    public NumberServiceImpl(Consumer consumer, Producer producer) {
        this.consumer = consumer;
        this.producer = producer;
    }

    @Override
    public void execute(BlockingQueue<Number> queue) {
        int count = Runtime.getRuntime().availableProcessors();
        ScheduledExecutorService producerExec = Executors.newScheduledThreadPool(count);
        ScheduledExecutorService consumerExec = Executors.newScheduledThreadPool(count);

        producerExec.scheduleAtFixedRate(producer, 5, 5, TimeUnit.SECONDS);
        consumerExec.scheduleAtFixedRate(consumer, 5, 5, TimeUnit.SECONDS);


    }
}
