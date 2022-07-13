package com.ecom.service.impl;

import com.ecom.entity.Category;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.payload.CategoryDto;
import com.ecom.repository.CategoryRepo;
import com.ecom.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

//        dto -> entity

        Category category = this.modelMapper.map(categoryDto, Category.class);

        Category savedCategory = this.categoryRepo.save(category);

//        entity -> dto

        CategoryDto savedCategoryDto = this.modelMapper.map(savedCategory, CategoryDto.class);
        return savedCategoryDto;
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories = this.categoryRepo.findAll();
        List<CategoryDto> dtos = categories.stream().map(cat -> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public CategoryDto update(CategoryDto c, Integer cid) {
        Category category = this.categoryRepo.findById(cid).orElseThrow(() -> new ResourceNotFoundException("category", " category id ", cid + ""));
        category.setTitle(c.getTitle());
        category.setDesc(c.getDesc());
        category.setBannerName(c.getBannerName());
        Category updatedCat = this.categoryRepo.save(category);
        return this.modelMapper.map(updatedCat, CategoryDto.class);
    }

    @Override
    public void delete(Integer cid) {

    }
}
