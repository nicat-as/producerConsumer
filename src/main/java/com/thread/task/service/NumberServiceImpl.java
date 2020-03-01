package com.thread.task.service;

import com.thread.task.domain.Number;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

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

        producerExec.scheduleAtFixedRate(producer,5,5, TimeUnit.SECONDS);
        consumerExec.scheduleAtFixedRate(consumer,5,5,TimeUnit.SECONDS);



    }
}
