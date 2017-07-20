package com.tmoncorp.admin.repository;

import com.tmoncorp.admin.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sk2rldnr on 2017-07-10.
 */
@Component
public class DatabaseSeeder implements CommandLineRunner {
    private CategoryRepository categoryRepository;

    @Autowired
    public DatabaseSeeder(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        List<Category> categoryData = new ArrayList<>();
    }
}
