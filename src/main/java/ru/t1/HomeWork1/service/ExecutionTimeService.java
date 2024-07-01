package ru.t1.HomeWork1.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Async;
import ru.t1.HomeWork1.model.ExecutionTime;
import ru.t1.HomeWork1.repository.ExecutionTimeRepository;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExecutionTimeService {
    ExecutionTimeRepository executionTimeRepository;

    public void saveExecutionTime(String methodName, long executionTime) {
        executionTimeRepository.save(ExecutionTime.builder()
                .methodName(methodName)
                .executionTime(executionTime)
                .build());
    }

    @Async
    public void saveExecutionTimeAsync(String methodName, long executionTime) {
        saveExecutionTime(methodName, executionTime);
    }
}