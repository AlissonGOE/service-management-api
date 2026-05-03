package com.alisson.service_management.repository;

import com.alisson.service_management.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByServiceId(Long serviceId);
    List<Item> findByServiceClientId(Long clientId);
}
