package com.alisson.service_management.mapper;

import com.alisson.service_management.dto.ItemDTO;
import com.alisson.service_management.model.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public Item map(ItemDTO itemDTO) {

        Item item = new Item();

        item.setId(itemDTO.getId());
        item.setDescription(itemDTO.getDescription());
        item.setName(itemDTO.getName());
        item.setServiceType(itemDTO.getServiceType());

        return item;
    }

    public ItemDTO map(Item item) {

        ItemDTO itemDTO = new ItemDTO();

        itemDTO.setId(item.getId());
        itemDTO.setDescription(item.getDescription());
        itemDTO.setName(item.getName());
        itemDTO.setServiceType(item.getServiceType());

        if (item.getService() != null) {
            itemDTO.setServiceId(item.getService().getId());
        }

        return itemDTO;
    }
}
