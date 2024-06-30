package ru.t1.HomeWork1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.t1.HomeWork1.model.Application;
import ru.t1.HomeWork1.model.Status;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    @NonNull
    List<Application> findAll();

    List<Application> getAllByStatus(Status status);
}