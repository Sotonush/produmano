package com.example.produmano.service;

import com.example.produmano.entity.Client;
import com.example.produmano.enums.ClientStatus;
import com.example.produmano.enums.ServiceType;
import com.example.produmano.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    @Transactional
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Transactional
    public Client updateClient(Long id, Client updatedClient) {
        return clientRepository.findById(id)
                .map(existingClient -> {
                    if (updatedClient.getName() != null) {
                        existingClient.setName(updatedClient.getName());
                    }
                    if (updatedClient.getCompany() != null) {
                        existingClient.setCompany(updatedClient.getCompany());
                    }
                    if (updatedClient.getContactPerson() != null) {
                        existingClient.setContactPerson(updatedClient.getContactPerson());
                    }
                    if (updatedClient.getPhone() != null) {
                        existingClient.setPhone(updatedClient.getPhone());
                    }
                    if (updatedClient.getEmail() != null) {
                        existingClient.setEmail(updatedClient.getEmail());
                    }
                    if (updatedClient.getAddress() != null) {
                        existingClient.setAddress(updatedClient.getAddress());
                    }
                    if (updatedClient.getServiceType() != null) {
                        existingClient.setServiceType(updatedClient.getServiceType());
                    }
                    if (updatedClient.getStatus() != null) {
                        existingClient.setStatus(updatedClient.getStatus());
                    }
                    return clientRepository.save(existingClient);
                })
                .orElseThrow(() -> new RuntimeException("Client not found with id " + id));
    }

    public boolean deleteClient(Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()) {
            clientRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Client> findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    public Optional<Client> findByPhone(String phone) {
        return clientRepository.findByPhone(phone);
    }

    public List<Client> findByStatus(ClientStatus status) {
        return clientRepository.findByStatus(status);
    }

    public List<Client> findByServiceType(ServiceType serviceType){
        return clientRepository.findByServiceType(serviceType);
    }

    public List<Client> findByName(String name) {
        return clientRepository.findByNameIgnoreCase(name);
    }
}
