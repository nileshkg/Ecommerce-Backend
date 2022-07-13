package com.ecom.controller;

import com.ecom.entity.Category;
import com.ecom.payload.CategoryDto;
import com.ecom.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(
            @Valid @RequestBody CategoryDto categoryDto
    ) {
        CategoryDto category1 = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(category1, HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('NORMAL')")
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getCategories(

    ) {
        List<CategoryDto> categories =
                this.categoryService.getCategories();
        return new ResponseEntity<List<CategoryDto>>(categories, HttpStatus.OK);
    }


    //update category

    @PutMapping("/{cid}")
    public ResponseEntity<CategoryDto> update(
            @RequestBody CategoryDto categoryDto,
            @PathVariable Integer cid
    ) {
        CategoryDto update = this.categoryService.update(categoryDto, cid);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }


    //delete funtionality
    //TODO
}
