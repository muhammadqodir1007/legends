package com.fazo.esm.service.impl;

import com.fazo.esm.repository.CategoryRepository;
import com.fazo.esm.repository.ItemTypeRepository;
import com.fazo.esm.repository.MaterialCategoryRepository;
import com.fazo.esm.repository.MaterialTypeRepository;
import com.fazo.esm.service.HomeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class HomeServiceImpl implements HomeService {

    private final ItemTypeRepository itemTypeRepository;
    private final MaterialTypeRepository materialTypeRepository;
    private final CategoryRepository categoryRepository;
    private final MaterialCategoryRepository materialCategoryRepository;

    @Override
    public Map<String, Long> count() {
        Map<String, Long> count = new HashMap<>();
        long items = itemTypeRepository.count();
        long materials = materialTypeRepository.count();
        long itemCategories = categoryRepository.count();
        long materialCategories = materialCategoryRepository.count();
        count.put("items", items);
        count.put("materials", materials);
        count.put("itemCategories", itemCategories);
        count.put("materialCategories", materialCategories);
        return count;
    }
}
