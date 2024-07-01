package ru.t1.HomeWork1.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.t1.HomeWork1.dto.MethodExecutionAverage;
import ru.t1.HomeWork1.dto.MethodExecutionMin;
import ru.t1.HomeWork1.dto.MethodExecutionMax;
import ru.t1.HomeWork1.repository.ExecutionTimeRepository;

import java.util.List;

@RequiredArgsConstructor
@RestController("/timer")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExecutionTimeController {
    ExecutionTimeRepository executionTimeRepository;

    @GetMapping("/average")
    public List<MethodExecutionAverage> getAverageExecutionTime() {
        return executionTimeRepository.findAverageExecutionTimes();
    }

    @GetMapping("/min")
    public List<MethodExecutionMin> getMinExecutionTime() {
        return executionTimeRepository.findMinExecutionTimes();
    }

    @GetMapping("/max")
    public List<MethodExecutionMax> getMaxExecutionTime() {
        return executionTimeRepository.findMaxExecutionTimes();
    }
}