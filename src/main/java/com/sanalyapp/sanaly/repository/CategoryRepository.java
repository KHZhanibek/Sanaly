package com.sanalyapp.sanaly.repository;

import com.sanalyapp.sanaly.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> getAllByUserId(Long appUserId);
    Category getCategoryByUserIdAndId(Long appUserId, Long id);
    Category getByUserIdAndTitle(Long userId, String titile);
    boolean existsByUserIdAndTitle(Long appUserId, String title);
    void deleteByUser_IdAndId(Long appUserId, Long id);


}