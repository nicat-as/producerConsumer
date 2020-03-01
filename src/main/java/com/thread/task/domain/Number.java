package com.thread.task.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
public class Number {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "varchar(13) not null")
    private String number;

    @Column(columnDefinition = "int not null")
    private Integer status = 1;

    public Number(String number) {
        this.number = number;
    }

    public Number() {
    }
}
