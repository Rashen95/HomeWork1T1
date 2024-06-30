package ru.t1.HomeWork1.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.t1.HomeWork1.model.ExecutionTime;
import ru.t1.HomeWork1.repository.ExecutionTimeRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExecutionTimeController {
    ExecutionTimeRepository executionTimeRepository;

    @GetMapping("/execution-times")
    public List<ExecutionTime> getExecutionTimes(@RequestParam String methodName) {
        return executionTimeRepository.findByMethodName(methodName);
    }

    @GetMapping("/execution-times/stats")
    public ExecutionTimeStats getExecutionTimeStats(@RequestParam String methodName) {
        List<ExecutionTime> times = executionTimeRepository.findByMethodName(methodName);
        long total = times.stream().mapToLong(ExecutionTime::getExecutionTime).sum();
        long average = (long) times.stream().mapToLong(ExecutionTime::getExecutionTime).average().orElse(0);
        return new ExecutionTimeStats(methodName, total, average);
    }

    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class ExecutionTimeStats {
        String methodName;
        long totalExecutionTime;
        long averageExecutionTime;
    }
}