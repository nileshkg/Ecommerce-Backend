package com.ecom.service;

import com.ecom.entity.Category;
import com.ecom.payload.CategoryDto;

import java.util.List;

public interface CategoryService {

//    create

    CategoryDto createCategory(CategoryDto category);


//    get

    List<CategoryDto> getCategories();


//    update

    CategoryDto update(CategoryDto c, Integer cid);


//   delete

    void delete(Integer cid);
}


