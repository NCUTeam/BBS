package com.homework.bbs.bbsdemo.dao;

import com.homework.bbs.bbsdemo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao extends JpaRepository<Category,Integer> {
}
