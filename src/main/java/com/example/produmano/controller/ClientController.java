package com.example.produmano.controller;

import com.example.produmano.entity.Client;
import com.example.produmano.enums.ClientStatus;
import com.example.produmano.enums.ServiceType;
import com.example.produmano.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/client")
@CrossOrigin(origins = "*")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @PostMapping("/createClient")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client createdClient = clientService.createClient(client);
        return ResponseEntity.ok(createdClient);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Client>> getClientById(@PathVariable Long id) {
        try{
            Optional<Client> client = clientService.getClientById(id);
            return ResponseEntity.ok(client);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client updateClient){
        Client client = clientService.updateClient( id, updateClient);
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        boolean deleted = clientService.deleteClient(id);
        if(deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search/name")
    public ResponseEntity<List<Client>> findByName(@RequestParam String name) {
        List<Client> clients = clientService.findByName(name);
        return ResponseEntity.ok(clients);
    }
    @GetMapping("/search/serviceType")
    public ResponseEntity<List<Client>> findByServiceType(@RequestParam ServiceType serviceType) {
        List<Client> clients = clientService.findByServiceType(serviceType);
        return ResponseEntity.ok(clients);
    }
    @GetMapping("/search/status")
    public ResponseEntity<List<Client>> findByStatus(@RequestParam ClientStatus status) {
        List<Client> clients = clientService.findByStatus(status);
        return ResponseEntity.ok(clients);
    }
    @GetMapping("/search/phone")
    public ResponseEntity<Client> findByPhone(@RequestParam String phone) {
        Optional<Client> client = clientService.findByPhone(phone);
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/search/email")
    public ResponseEntity<Client> findByEmail(@RequestParam String email) {
        Optional<Client> client = clientService.findByEmail(email);
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
