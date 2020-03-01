package com.thread.task;

import com.thread.task.domain.Number;
import com.thread.task.repository.NumbersRepository;
import com.thread.task.service.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@SpringBootApplication
public class TaskApplication {
    @Autowired
    private NumbersRepository numbersRepository;

    @Autowired
    private NumberService numberService;

    @Autowired
    private BlockingQueue<Number> queue;


    @PostConstruct
    public void add(){
        //These are dump data for testing
        numbersRepository.save(new Number("0553669898"));
        numbersRepository.save(new Number("0504748124"));
        numbersRepository.save(new Number("0775452131"));
        numbersRepository.save(new Number("0708089933"));
        numbersRepository.save(new Number("0513343669"));
        numbersRepository.save(new Number("0778893311"));
        numbersRepository.save(new Number("0556631524"));
        numbersRepository.save(new Number("0556632233"));
        numbersRepository.save(new Number("0556894412"));
        numbersRepository.save(new Number("0774563542"));
        numbersRepository.save(new Number("0513327757"));
        numbersRepository.save(new Number("0774115665"));
        numbersRepository.save(new Number("0701264588"));
        numbersRepository.save(new Number("0518003591"));
        numbersRepository.save(new Number("0551131145"));
        numbersRepository.save(new Number("0772568832"));

        //this is our producer and consumer service
        numberService.execute(queue);
    }

    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
    }



}
