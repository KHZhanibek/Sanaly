package com.sanalyapp.sanaly.api;

import com.sanalyapp.sanaly.model.Category;
import com.sanalyapp.sanaly.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor

@RestController
@RequestMapping("/api/v1/users/{user_id}/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getAllCategoriesOfAppUser(@PathVariable(value = "user_id") Long appUserId){
        List<Category> category = categoryService.getAppUserCategories(appUserId);
        return new ResponseEntity<>(category, HttpStatus.OK);
//        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryOfAppUser(@PathVariable(value = "user_id") Long appUserId, @PathVariable(value = "id") Long id){
        Category category = categoryService.getAppUserCategory(appUserId, id);
        return new ResponseEntity<>(category, HttpStatus.OK);
//        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveCategoryOfAppUser(@PathVariable(value = "user_id") Long appUserId, @RequestBody Category category){
        String message = categoryService.saveAppUserCategory(appUserId, category);
        return new ResponseEntity<>(category, HttpStatus.OK);
//        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategoryOfAppUser(@PathVariable(value = "id") Long id, @RequestBody Category category){
        String message = categoryService.updateAppUserCategory(id, category);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryOfAppUser(@PathVariable(value = "user_id") Long appUserId, @PathVariable(value = "id") Long id){
        String message = categoryService.deleteAppUserCategory(appUserId, id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
