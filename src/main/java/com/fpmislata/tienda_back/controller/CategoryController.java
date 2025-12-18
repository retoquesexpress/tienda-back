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

    @GetMapping("/{idCategory}")
    public ResponseEntity<CategoryDetailResponse> getById(@PathVariable Integer idCategory) {
        CategoryDto categoryDto = categoryService.getById(idCategory);
        CategoryDetailResponse categoryDetailResponse = CategoryMapper.getInstance()
                .fromCategoryDtoToCategoryDetailResponse(categoryDto);
        return new ResponseEntity<>(categoryDetailResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryDetailResponse> create(@RequestBody CategoryInsertRequest categoryInsertRequest) {
        CategoryDto categoryDto = new CategoryDto(
                categoryInsertRequest.idCategory(),
                categoryInsertRequest.name());

        CategoryDto createdCategory = categoryService.create(categoryDto);
        CategoryDetailResponse createdCategoryResponse = CategoryMapper.getInstance()
                .fromCategoryDtoToCategoryDetailResponse(createdCategory);

        return new ResponseEntity<>(createdCategoryResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{idCategory}")
    public ResponseEntity<CategoryDetailResponse> update(@PathVariable Integer idCategory,
            @RequestBody CategoryInsertRequest categoryInsertRequest) {

        CategoryDto categoryDto = new CategoryDto(
                idCategory,
                categoryInsertRequest.name()

        );

        CategoryDto updatedCategory = categoryService.update(categoryDto);
        CategoryDetailResponse updatedCategoryResponse = CategoryMapper.getInstance()
                .fromCategoryDtoToCategoryDetailResponse(updatedCategory);

        return new ResponseEntity<>(updatedCategoryResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{idCategory}")
    public ResponseEntity<Void> delete(@PathVariable Integer idCategory) {
        categoryService.delete(idCategory);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
