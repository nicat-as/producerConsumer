package com.thread.task.service;

import com.thread.task.domain.Number;
import com.thread.task.repository.NumbersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.BlockingQueue;


//This class used for consuming data from queue
@Component
public class Consumer implements Runnable {

    //this is reference for shared queue
    private BlockingQueue<Number> queue;

    @Autowired
    private NumbersRepository numbersRepository;

    public Consumer(BlockingQueue<Number> queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        try {
            Number number = queue.take();
            Optional<Number> numberOptional = Optional.of(number);
            if (numberOptional.isPresent()) {
                System.out.println("Number is taken : " + number);
                numbersRepository.setStatus(number.getId());
            } else {
                System.out.println("There is no number in queue for consuming!!");
            }
        } catch (Exception e) {

        }
    }
}
