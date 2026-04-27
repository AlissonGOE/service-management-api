package com.alisson.service_management.service;

import com.alisson.service_management.dto.ClientDTO;
import com.alisson.service_management.dto.ServiceOrderDTO;
import com.alisson.service_management.mapper.ClientMapper;
import com.alisson.service_management.mapper.ServiceOrderMapper;
import com.alisson.service_management.model.Client;
import com.alisson.service_management.model.ServiceOrder;
import com.alisson.service_management.repository.ClientRepository;
import com.alisson.service_management.repository.ServiceOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceOrderService {

    private final ServiceOrderRepository serviceOrderRepository;
    private final ServiceOrderMapper serviceOrderMapper;
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ServiceOrderService(ServiceOrderRepository serviceOrderRepository, ServiceOrderMapper serviceOrderMapper, ClientRepository clientRepository, ClientMapper clientMapper) {
        this.serviceOrderRepository = serviceOrderRepository;
        this.serviceOrderMapper = serviceOrderMapper;
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    // Create Service order by client
    public ServiceOrderDTO createServiceOrder(Long clientId, ServiceOrderDTO serviceOrderDTO) {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client == null) {
            return null;
        } else {
            ServiceOrder serviceOrder = serviceOrderMapper.map(serviceOrderDTO);
            serviceOrder.setClient(client);

            ServiceOrder saved = serviceOrderRepository.save(serviceOrder);

            ServiceOrderDTO savedDTO = serviceOrderMapper.map(saved);
            savedDTO.setClient(clientMapper.mapToSummary(client));

            return savedDTO;
        }
    }

    // List service order
    public List<ServiceOrderDTO> findAll() {
        List<ServiceOrder> serviceOrders = serviceOrderRepository.findAll();
        return serviceOrders.stream()
                .map(order -> {
                    ServiceOrderDTO serviceOrderDTO = serviceOrderMapper.map(order);
                    serviceOrderDTO.setClient(clientMapper.mapToSummary(order.getClient()));
                    return serviceOrderDTO;
                })
                .collect(Collectors.toList());
    }

    // List service order per Id
    public ServiceOrderDTO findById(Long id) {
        Optional<ServiceOrder> serviceOrderPerId = serviceOrderRepository.findById(id);
        return serviceOrderPerId
                .map(order -> {
                    ServiceOrderDTO serviceOrderDTO = serviceOrderMapper.map(order);
                    serviceOrderDTO.setClient(clientMapper.mapToSummary(order.getClient()));
                    return serviceOrderDTO;
                })
                .orElse(null);
    }

    // Update service order by Id
    public ServiceOrderDTO updateById(Long id, ServiceOrderDTO serviceOrderDTO) {
        Optional<ServiceOrder> existingServiceOrder = serviceOrderRepository.findById(id);
        if (existingServiceOrder.isPresent()) {
            ServiceOrder updatedServiceOrder = serviceOrderMapper.map(serviceOrderDTO);
            updatedServiceOrder.setId(id);
            ServiceOrder saveServiceOrder = serviceOrderRepository.save(updatedServiceOrder);
            return serviceOrderMapper.map(saveServiceOrder);
        }
        return null;
    }

    // Delete service order by Id
    public void deleteById(Long id) {
        serviceOrderRepository.deleteById(id);
    }
}
