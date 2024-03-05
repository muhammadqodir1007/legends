package com.fazo.esm.service.impl;

import com.fazo.esm.entity.ItemType;
import com.fazo.esm.payload.dto.ItemTypeDto;
import com.fazo.esm.repository.ItemTypeRepository;
import com.fazo.esm.service.ItemTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemTypeServiceImpl implements ItemTypeService {

    private final ItemTypeRepository itemTypeRepository;

    @Autowired
    public ItemTypeServiceImpl(ItemTypeRepository itemTypeRepository) {
        this.itemTypeRepository = itemTypeRepository;
    }

    @Override
    public List<ItemType> getAllItemTypes() {
        return itemTypeRepository.findAll();
    }

    @Override
    public ItemType getItemTypeById(int id) {
        return itemTypeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Item type not found with id: " + id));
    }

    @Override
    public ItemType createItemType(ItemTypeDto itemTypeDto) {
        ItemType itemType = new ItemType();
        itemType.setName(itemTypeDto.getName());
        return itemTypeRepository.save(itemType);
    }

    @Override
    public ItemType updateItemType(int id, ItemTypeDto itemTypeDto) {
        ItemType itemType = getItemTypeById(id);
        itemType.setName(itemTypeDto.getName());
        return itemTypeRepository.save(itemType);
    }

    @Override
    public void deleteItemType(int id) {
        itemTypeRepository.deleteById(id);
    }
}
