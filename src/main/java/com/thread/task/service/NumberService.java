package com.thread.task.service;


import com.thread.task.domain.Number;

import java.util.concurrent.BlockingQueue;

public interface NumberService {
    void execute(BlockingQueue<Number> queue);
}
