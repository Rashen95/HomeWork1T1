package ru.t1.HomeWork1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.t1.HomeWork1.dto.MethodExecutionAverage;
import ru.t1.HomeWork1.dto.MethodExecutionMin;
import ru.t1.HomeWork1.dto.MethodExecutionMax;
import ru.t1.HomeWork1.model.ExecutionTime;

import java.util.List;


public interface ExecutionTimeRepository extends JpaRepository<ExecutionTime, Long> {
    @Query("SELECT e.methodName AS methodName, AVG(e.executionTime) AS averageExecutionTime " +
            "FROM ExecutionTime e " +
            "GROUP BY e.methodName")
    List<MethodExecutionAverage> findAverageExecutionTimes();

    @Query("SELECT e.methodName AS methodName, MIN(e.executionTime) AS minExecutionTime " +
            "FROM ExecutionTime e " +
            "GROUP BY e.methodName")
    List<MethodExecutionMin> findMinExecutionTimes();

    @Query("SELECT e.methodName AS methodName, MAX(e.executionTime) AS maxExecutionTime " +
            "FROM ExecutionTime e " +
            "GROUP BY e.methodName")
    List<MethodExecutionMax> findMaxExecutionTimes();
}