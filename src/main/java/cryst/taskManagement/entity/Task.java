package cryst.taskManagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String status; // PENDING, COMPLETED
    private String priority; // LOW, MEDIUM, HIGH
    private LocalDateTime createdAt;
}