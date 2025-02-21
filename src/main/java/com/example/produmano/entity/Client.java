package com.example.produmano.entity;

import com.example.produmano.enums.ClientStatus;
import com.example.produmano.enums.ServiceType;

import com.example.produmano.validation.PhoneNumber;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(length = 50)
    private String company;
    @Column(length = 50)
    private String contactPerson;

    @Column(nullable = false, unique = true, length = 20)
    @PhoneNumber
    private String phone;

    @Column(nullable = false, unique = true)
    @Email(message = "Неправильно указан email")
    private String email;

    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ServiceType serviceType;

    private LocalDate registrationDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ClientStatus status;

    @PrePersist
    protected void onCreate() {
        this.registrationDate = LocalDate.now();
    }

    @Column(nullable = false, length = 100, unique = true)
    private String telegramChatId;
}

