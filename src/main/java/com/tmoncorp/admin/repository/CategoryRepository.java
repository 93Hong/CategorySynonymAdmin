package com.tmoncorp.admin.repository;

import com.tmoncorp.admin.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sk2rldnr on 2017-07-10.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    //List<Category> findByPricePerNightLessThan (double price);
}
