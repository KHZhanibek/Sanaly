package com.sanalyapp.sanaly.service;

import com.sanalyapp.sanaly.model.AppUser;
import com.sanalyapp.sanaly.model.Category;
import com.sanalyapp.sanaly.repository.AppUserRepository;
import com.sanalyapp.sanaly.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final AppUserRepository appUserRepository;

    public List<Category> getAppUserCategories(Long appUserId){
        return categoryRepository.getAllByUserId(appUserId);
    }

    public Category getAppUserCategory(Long appUserId, Long id){
        return categoryRepository.getCategoryByUserIdAndId(appUserId, id);
    }

    public String saveAppUserCategory(Long appUserId, Category category){
        if(categoryRepository.existsByUserIdAndTitle(appUserId, category.getTitle()))
            return "Category with such name already exists!!!";
        AppUser appUser = appUserRepository.getAppUserById(appUserId);
        category.setUser(appUser);
        categoryRepository.save(category);
        return "Successfully created Category " + category.getTitle();
    }

    public String updateAppUserCategory(Long id, Category category){
        category.setId(id);
        categoryRepository.save(category);
        return "Successfully updated category!";
    }

    @Transactional
    public String deleteAppUserCategory(Long appUserId, Long id){
        categoryRepository.deleteByUser_IdAndId(appUserId, id);
        return "Successfully deleted";
    }

}
