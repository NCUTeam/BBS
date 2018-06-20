package com.homespients.bbsbackstage.dao;

import com.homespients.bbsbackstage.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao extends JpaRepository<Category,Integer> {
}
