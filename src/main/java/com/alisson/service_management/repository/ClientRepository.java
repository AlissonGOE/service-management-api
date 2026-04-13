package com.alisson.service_management.repository;

import com.alisson.service_management.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
