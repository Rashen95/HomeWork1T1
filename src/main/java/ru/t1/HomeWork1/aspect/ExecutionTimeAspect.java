package ru.t1.HomeWork1.aspect;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.t1.HomeWork1.annotation.TrackAsyncTime;
import ru.t1.HomeWork1.annotation.TrackTime;
import ru.t1.HomeWork1.service.ExecutionTimeService;

import java.util.concurrent.CompletableFuture;

@Aspect
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExecutionTimeAspect {
    ExecutionTimeService executionTimeService;

    @Around("@annotation(trackTime)")
    public Object aroundTrackTime(ProceedingJoinPoint joinPoint, TrackTime trackTime) {
        long startTime = System.currentTimeMillis();
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        long endTime = System.currentTimeMillis();
        executionTimeService.saveExecutionTime(joinPoint.getSignature().toString(), endTime - startTime);
        return result;
    }

    @Async
    @Around("@annotation(trackAsyncTime)")
    public CompletableFuture<Object> aroundTrackAsyncTime(
            ProceedingJoinPoint joinPoint,
            TrackAsyncTime trackAsyncTime
    ) {
        long startTime = System.currentTimeMillis();
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        long endTime = System.currentTimeMillis();
        executionTimeService.saveExecutionTimeAsync(joinPoint.getSignature().toString(), endTime - startTime);
        return CompletableFuture.completedFuture(result);
    }
}