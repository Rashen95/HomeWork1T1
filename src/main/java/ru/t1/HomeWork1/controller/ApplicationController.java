package ru.t1.HomeWork1.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.t1.HomeWork1.annotation.TrackAsyncTime;
import ru.t1.HomeWork1.dto.ApplicationDTO;
import ru.t1.HomeWork1.model.Application;
import ru.t1.HomeWork1.model.Status;
import ru.t1.HomeWork1.service.ApplicationService;

import java.util.Optional;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationController {
    ApplicationService applicationService;

    @TrackAsyncTime
    @PostMapping("/create")
    public void create(@RequestBody ApplicationDTO dto) {
        if (dto.getName() == null
                || dto.getName().isBlank()
                || dto.getDescription() == null
                || dto.getDescription().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Неверный запрос");
        }

        applicationService.save(dto);
    }

    @TrackAsyncTime
    @GetMapping("/list")
    public Object listApplications() {
        return applicationService.getAll();
    }

    @TrackAsyncTime
    @GetMapping("/list_by_status")
    public Object listApplicationsByStatus(@RequestParam("status") String status) {
        Status s = Status.fromString(status);

        if (s == null) {
            return new ResponseEntity<>("Введен неверный статус", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(applicationService.getAllByStatus(s), HttpStatus.OK);
    }

    @TrackAsyncTime
    @PutMapping("/change/{id}")
    public Object changeStatusById(@PathVariable("id") Long id, @RequestParam("status") String status) {
        Status s = Status.fromString(status);

        if (s == null) {
            return new ResponseEntity<>("Введен неверный статус", HttpStatus.CONFLICT);
        }

        Optional<Application> application = applicationService.changeStatusById(id, s);

        if (application.isEmpty()) {
            return new ResponseEntity<>(String.format("Заявка с id = %d не найдена", id), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(String.format("Статус заявки с id = %d успешно изменен на %s", id, s), HttpStatus.OK);
    }

    @TrackAsyncTime
    @DeleteMapping("/delete/{id}")
    public Object deleteById(@PathVariable("id") Long id) {
        Optional<Application> application = applicationService.deleteById(id);

        if (application.isEmpty()) {
            return new ResponseEntity<>(String.format("Заявка с id = %d не найдена", id), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(String.format("Заявка с id = %d успешно удалена", id), HttpStatus.OK);
    }
}