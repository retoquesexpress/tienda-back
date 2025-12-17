package com.fpmislata.tienda_back.controller;

import com.fpmislata.tienda_back.controller.webModel.request.CategoryInsertRequest;
import com.fpmislata.tienda_back.controller.webModel.response.CategoryDetailResponse;
import com.fpmislata.tienda_back.domain.service.CategoryService;
import com.fpmislata.tienda_back.domain.service.dto.CategoryDto;
import com.fpmislata.tienda_back.mapper.CategoryMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryCategory) {
        this.categoryService = categoryCategory;
    }

    @GetMapping
    public ResponseEntity<CategoryDetailResponse> findAll() {
        List<CategoryDetailResponse> categoryDetailResponse = categoryService.findAll()
                .stream()
                .map(CategoryMapper.getInstance()::fromCategoryDtoToCategoryDetailResponse)
                .toList();
        return new ResponseEntity(categoryDetailResponse, HttpStatus.OK);
    }

    @GetMapping("/{id_category}")
    public ResponseEntity<CategoryDetailResponse> getById(@PathVariable Integer id_category) {
        CategoryDto categoryDto = categoryService.getById(id_category);
        CategoryDetailResponse categoryDetailResponse = CategoryMapper.getInstance()
                .fromCategoryDtoToCategoryDetailResponse(categoryDto);
        return new ResponseEntity<>(categoryDetailResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryDetailResponse> create(@RequestBody CategoryInsertRequest categoryInsertRequest) {
        CategoryDto categoryDto = new CategoryDto(
                categoryInsertRequest.id_category(),
                categoryInsertRequest.name());

        CategoryDto createdCategory = categoryService.create(categoryDto);
        CategoryDetailResponse createdCategoryResponse = CategoryMapper.getInstance()
                .fromCategoryDtoToCategoryDetailResponse(createdCategory);

        return new ResponseEntity<>(createdCategoryResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id_category}")
    public ResponseEntity<CategoryDetailResponse> update(@PathVariable Integer id_category,
            @RequestBody CategoryInsertRequest categoryInsertRequest) {

        CategoryDto categoryDto = new CategoryDto(
                id_category,
                categoryInsertRequest.name()

        );

        CategoryDto updatedCategory = categoryService.update(categoryDto);
        CategoryDetailResponse updatedCategoryResponse = CategoryMapper.getInstance()
                .fromCategoryDtoToCategoryDetailResponse(updatedCategory);

        return new ResponseEntity<>(updatedCategoryResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id_category}")
    public ResponseEntity<Void> delete(@PathVariable Integer id_category) {
        categoryService.delete(id_category);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
