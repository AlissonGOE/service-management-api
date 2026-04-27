package com.alisson.service_management.mapper;

import com.alisson.service_management.dto.ServiceOrderDTO;
import com.alisson.service_management.model.ServiceOrder;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ServiceOrderMapper {

    private final ItemMapper itemMapper = new ItemMapper();

    public ServiceOrder map(ServiceOrderDTO serviceOrderDTO) {

        ServiceOrder serviceOrder = new ServiceOrder();

        serviceOrder.setId(serviceOrderDTO.getId());
        serviceOrder.setDescription(serviceOrderDTO.getDescription());
        serviceOrder.setTotalValue(serviceOrderDTO.getTotalValue());
        serviceOrder.setStatus(serviceOrderDTO.getStatus());
        serviceOrder.setServicePriority(serviceOrderDTO.getServicePriority());
        serviceOrder.setEntryDate(serviceOrderDTO.getEntryDate());
        serviceOrder.setExitDate(serviceOrderDTO.getExitDate());

        if (serviceOrderDTO.getItems() != null) {
            serviceOrder.setItems(
                    serviceOrderDTO.getItems().stream()
                            .map(itemMapper::map)
                            .collect(Collectors.toList())
            );
        }

        return serviceOrder;
    }

    public ServiceOrderDTO map(ServiceOrder serviceOrder) {

        ServiceOrderDTO serviceOrderDTO = new ServiceOrderDTO();

        serviceOrderDTO.setId(serviceOrder.getId());
        serviceOrderDTO.setDescription(serviceOrder.getDescription());
        serviceOrderDTO.setTotalValue(serviceOrder.getTotalValue());
        serviceOrderDTO.setStatus(serviceOrder.getStatus());
        serviceOrderDTO.setServicePriority(serviceOrder.getServicePriority());
        serviceOrderDTO.setEntryDate(serviceOrder.getEntryDate());
        serviceOrderDTO.setExitDate(serviceOrder.getExitDate());

        if (serviceOrder.getItems() != null) {
            serviceOrderDTO.setItems(
                    serviceOrder.getItems().stream()
                            .map(itemMapper::map)
                            .collect(Collectors.toList())
            );
        }

        return serviceOrderDTO;
    }
}
