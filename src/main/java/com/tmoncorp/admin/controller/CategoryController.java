package com.tmoncorp.admin.controller;

import com.tmoncorp.admin.domain.Category;
import com.tmoncorp.admin.file.ReadCSV;
import com.tmoncorp.admin.file.WriteCSV;
import com.tmoncorp.admin.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sk2rldnr on 2017-07-10.
 */
@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    private static final String FilePath = "./";
    private static final String FileName = "category.csv";
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Category> dataLoadFromDB() {
        return categoryRepository.findAll();
    }


    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public ResponseEntity<List<Category>> uploadFile(MultipartHttpServletRequest request) throws IOException, ParseException {

        Iterator<String> iterator = request.getFileNames();
        String fileContent = "";
        if (iterator.hasNext()) {
            MultipartFile mpf = request.getFile(iterator.next());
            fileContent = new String(mpf.getBytes(), "euc-kr");
        }

        return new ResponseEntity<>(new ReadCSV().readFromFile(fileContent), HttpStatus.OK);
    }


    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void downloadAsCSV() throws IOException {
        String csvFormatString = categoryRepository.findAll().stream()
                .map(Category::makeCsvString)
                .collect(Collectors.joining(System.getProperty("line.separator")));

        new WriteCSV().writeToCSV(csvFormatString, FilePath + FileName);
    }


    @RequestMapping(value = "/synchronize", method = RequestMethod.POST)
    public void synchronizeWithDB(@RequestBody List<Category> categoryList) {
        categoryRepository.save(categoryList);
    }

}
