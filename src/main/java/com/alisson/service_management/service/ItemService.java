package com.alisson.service_management.service;

import com.alisson.service_management.dto.ItemDTO;
import com.alisson.service_management.mapper.ItemMapper;
import com.alisson.service_management.mapper.ServiceOrderMapper;
import com.alisson.service_management.model.Item;
import com.alisson.service_management.model.ServiceOrder;
import com.alisson.service_management.repository.ItemRepository;
import com.alisson.service_management.repository.ServiceOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ServiceOrderRepository serviceOrderRepository;
    private final ServiceOrderMapper serviceOrderMapper;
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public ItemService(ServiceOrderRepository serviceOrderRepository, ServiceOrderMapper serviceOrderMapper, ItemRepository itemRepository, ItemMapper itemMapper) {
        this.serviceOrderRepository = serviceOrderRepository;
        this.serviceOrderMapper = serviceOrderMapper;
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    // Create Item by Service Order
    public ItemDTO createItem(Long serviceOrderId, ItemDTO itemDTO) {
        ServiceOrder serviceOrder = serviceOrderRepository.findById(serviceOrderId).orElse(null);
        if (serviceOrder == null) {
            return null;
        } else {
            Item item = itemMapper.map(itemDTO);
            item.setService(serviceOrder);

            Item saved = itemRepository.save(item);
            return itemMapper.map(saved);
        }
    }

    // List item by Id
    public ItemDTO findById(Long id) {
        Optional<Item> itemById = itemRepository.findById(id);
        return itemById
                .map(itemMapper::map)
                .orElse(null);
    }

    // List item per Id service order
    public List<ItemDTO> findByServiceId(Long serviceId) {
        List<Item> items = itemRepository.findByServiceId(serviceId);
        return items.stream()
                .map(itemMapper::map)
                .collect(Collectors.toList());
    }

    // List item per Id client
    public List<ItemDTO> findByClientId(Long clientId) {
        List<Item> items = itemRepository.findByServiceClientId(clientId);
        return items.stream()
                .map(itemMapper::map)
                .collect(Collectors.toList());
    }

    // Update item by Id
    public ItemDTO updateById(Long id, ItemDTO itemDTO) {
        Optional<Item> existingItem = itemRepository.findById(id);
        if (existingItem.isPresent()) {
            Item existing = existingItem.get();

            existing.setName(itemDTO.getName());
            existing.setDescription(itemDTO.getDescription());
            existing.setServiceType(itemDTO.getServiceType());

            Item saved = itemRepository.save(existing);
            return itemMapper.map(saved);
        } else {
            return null;
        }
    }

    // Delete item by Id
    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }
}
