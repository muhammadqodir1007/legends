package com.fazo.esm.service;

import com.fazo.esm.entity.ItemType;
import com.fazo.esm.payload.dto.ItemTypeDto;

import java.util.List;

public interface ItemTypeService {
    List<ItemType> getAllItemTypes();

    List<ItemType> getAllItemTypesByCategoryId(int categoryId);

    ItemType getItemTypeById(int id);

    ItemType createItemType(ItemTypeDto itemTypeDto);

    ItemType updateItemType(int id, ItemTypeDto itemTypeDto);

    void deleteItemType(int id);
}
