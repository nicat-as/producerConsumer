package com.thread.task.repository;

import com.thread.task.domain.Number;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface NumbersRepository extends JpaRepository<Number, Long> {

    @Query("from Number where status=:status ")
    List<Number> getNumberStatus(@Param("status") Integer status);

    @Transactional
    @Modifying
    @Query("update Number set status = 0 where id = :id ")
    int setStatus(@Param("id") long id);


}
