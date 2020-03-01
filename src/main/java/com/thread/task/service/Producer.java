package com.thread.task.service;

import com.thread.task.domain.Number;
import com.thread.task.repository.NumbersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.BlockingQueue;

@Component
public class Producer implements Runnable {

    private BlockingQueue<Number> queue;

    @Autowired
    private NumbersRepository numbersRepository;

    public Producer(BlockingQueue<Number> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
            try {
                List<Number> numbers =  numbersRepository.getNumberStatus(1);
                if (!numbers.isEmpty() && !queue.contains(numbers)) {
                    queue.addAll(numbers);
                    System.out.println("Queue : " + queue.size() +  " number added to queue : " + numbers);
                }else {
                    System.out.println("There are no numbers in queue");
                }
            } catch (Exception e) {
                e.getMessage();
            }

    }
}
