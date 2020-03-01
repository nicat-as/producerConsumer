package com.thread.task.controller;

import com.thread.task.domain.Number;
import com.thread.task.repository.NumbersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


/*Api endpoints
    /number - both for getting all numbers and adding new number to database
    /number/id - = for getting information from specific id
    /status/{status_id} - getting status information and updating status


 */
@RestController
public class Api {

    @Autowired
    private NumbersRepository numbersRepository;

    @GetMapping("/number")
    public ResponseEntity<List<Number>> getAll() {
        List<Number> numbers = numbersRepository.findAll();
        if (numbers.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Number list is empty!");
        }
        return new ResponseEntity<>(numbers, HttpStatus.OK);
    }

    @GetMapping("/number/{id}")
    public ResponseEntity<Number> getNumberById(
            @PathVariable("id") Long id
    ) {
        Optional<Number> optionalNumber = numbersRepository.findById(id);
        if (!optionalNumber.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no any number for id=" + id);
        }

        return new ResponseEntity<>(optionalNumber.get(), HttpStatus.OK);
    }

    @PostMapping("/number")
    public ResponseEntity<Number> add(@RequestBody Number number) {
        number = numbersRepository.save(number);
        if (number==null){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Number not added!");
        }
        return new ResponseEntity<>(number, HttpStatus.CREATED);
    }

    @GetMapping({"/status", "/status/{status}"})
    public ResponseEntity<List<Number>> getStatus(@PathVariable("status") Integer status) {
        if (status == null) {
            status = 0;
        }
        List<Number> numbers = numbersRepository.getNumberStatus(status);
        if (numbers.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"status id is wrong!");
        }
        return new ResponseEntity<>(numbers, HttpStatus.OK);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<Integer> updateStatus(@PathVariable(name = "id") long id) {
        int count = numbersRepository.setStatus(id);
        if (count<=0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(count, HttpStatus.CREATED);
    }


}
