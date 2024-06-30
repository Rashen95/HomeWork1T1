package ru.t1.HomeWork1.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.t1.HomeWork1.dto.ApplicationDTO;
import ru.t1.HomeWork1.model.Application;
import ru.t1.HomeWork1.model.Status;
import ru.t1.HomeWork1.repository.ApplicationRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationService {
    ApplicationRepository applicationRepository;

    public List<Application> getAll() {
        return applicationRepository.findAll();
    }

    public List<Application> getAllByStatus(Status status) {
        return applicationRepository.getAllByStatus(status);
    }

    public Optional<Application> deleteById(Long id) {
        Optional<Application> application = applicationRepository.findById(id);

        if (application.isPresent()) {
            applicationRepository.deleteById(id);
        }

        return application;
    }

    public void save(ApplicationDTO dto) {
        applicationRepository.save(
                Application.builder()
                        .name(dto.getName())
                        .description(dto.getDescription())
                        .creationData(LocalDateTime.now())
                        .status(Status.NOT_STARTED)
                        .build());
    }

    public Optional<Application> changeStatusById(Long id, Status status) {
        Optional<Application> application = applicationRepository.findById(id);

        if (application.isPresent()) {
            application.get().setStatus(status);
            applicationRepository.save(application.get());
        }

        return application;
    }
}