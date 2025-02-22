package com.example.produmano.entity;

import com.example.produmano.enums.EmployeeStatus;
import com.example.produmano.enums.PositionEmployee;
import com.example.produmano.enums.RoleEmployee;
import com.example.produmano.validation.PhoneNumber;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String username;

    @Column(nullable = false, length = 100)
    private String fio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private RoleEmployee roleEmployee;

    @Column(nullable = false, unique = true, length = 20)
    @PhoneNumber
    private String phone;

    @Email(message = "Неправильно указан email")
    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private PositionEmployee position;

    private LocalDate startDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private EmployeeStatus status;

    @PrePersist
    protected void onCreate() {
        this.startDate = LocalDate.now();
    }

    @Column(nullable = false, length = 100, unique = true)
    private String telegramChatId;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;
}