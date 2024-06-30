package ru.t1.HomeWork1.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "execution_times", schema = "my_schema")
public class ExecutionTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "method_name")
    String methodName;

    @Column(name = "execution_time")
    long executionTime;
}