package ru.t1.HomeWork1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.t1.HomeWork1.model.ExecutionTime;

import java.util.List;

public interface ExecutionTimeRepository extends JpaRepository<ExecutionTime, Long> {
    List<ExecutionTime> findByMethodName(String methodName);
}