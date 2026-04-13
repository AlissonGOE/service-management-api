package com.alisson.service_management.repository;

import com.alisson.service_management.model.ServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {
}
