package com.example.produmano.repository;

import com.example.produmano.entity.Client;
import com.example.produmano.enums.ClientStatus;
import com.example.produmano.enums.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findById(Long id);

    Optional<Client> findByEmail(String email);

    Optional<Client> findByPhone(String phone);

    List<Client> findByStatus(ClientStatus status);

    List<Client> findByServiceType(ServiceType serviceType);

    List<Client> findByNameIgnoreCase(String name);


}
