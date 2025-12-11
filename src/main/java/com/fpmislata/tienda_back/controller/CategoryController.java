package com.fpmislata.tienda_back.controller;

import com.fpmislata.tienda_back.controller.webModel.request.CategoryInsertRequest;
import com.fpmislata.tienda_back.controller.webModel.response.CategoryDetailResponse;
import com.fpmislata.tienda_back.domain.service.CategoryService;
import com.fpmislata.tienda_back.domain.service.dto.CategoryDto;
import com.fpmislata.tienda_back.mapper.CategoryMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id_category}")
    public ResponseEntity<CategoryDetailResponse> getById(@PathVariable String id_category) {
        CategoryDto categoryDto = categoryService.getById(id_category);
        CategoryDetailResponse categoryDetailResponse = CategoryMapper.fromCategoryDtoToCategoryDetailResponse(categoryDto);
        return new ResponseEntity<>(categoryDetailResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryDetailResponse> create(@RequestBody CategoryInsertRequest categoryInsertRequest) {
        CategoryDto categoryDto = new CategoryDto(
                categoryInsertRequest.id_category(),
                categoryInsertRequest.name()
        );

        CategoryDto createdCategory = categoryService.create(categoryDto);
        CategoryDetailResponse createdCategoryResponse =
                CategoryMapper.fromCategoryDtoToCategoryDetailResponse(createdCategory);

        return new ResponseEntity<>(createdCategoryResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id_category}")
    public ResponseEntity<CategoryDetailResponse> update(@PathVariable String id_category,
                                                     @RequestBody CategoryInsertRequest categoryInsertRequest) {

        CategoryDto categoryDto = new CategoryDto(
                id_category,
                categoryInsertRequest.name()

        );

        CategoryDto updatedCategory = categoryService.update(categoryDto);
        CategoryDetailResponse updatedCategoryResponse =
                CategoryMapper.fromCategoryDtoToCategoryDetailResponse(updatedCategory);

        return new ResponseEntity<>(updatedCategoryResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id_category}")
    public ResponseEntity<Void> delete(@PathVariable String id_category) {
        categoryService.delete(id_category);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
