package com.tmoncorp.admin.file;

import com.tmoncorp.admin.domain.Category;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sk2rldnr on 2017-07-14.
 */
public class ReadCSV {

    public List<Category> readFromFile(String fileContent) throws ParseException, IOException {

        List<Category> categoryList = new ArrayList<>();
        String[] csvString = fileContent.split("\n");

        // todo : refactoring
        for (int i = 1; i < csvString.length; i++) {
            String[] line = csvString[i].split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            categoryList.add(new Category(Integer.parseInt(line[0]), line[1].replace("\"", ""), line[2].replace("\"", ""), line[3].replace("\"", ""), line[4].replace("\"", ""), line[5].replace("\"", ""), line[6].replace("\"", ""), Double.parseDouble(line[7]), Boolean.valueOf(line[8]), line[9].replace("\"", ""), stringToTimestamp(line[10])));
        }

        return categoryList;
    }

    private Timestamp stringToTimestamp(String stringTime) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date parsedTimeStamp = dateFormat.parse(stringTime);

        return new Timestamp(parsedTimeStamp.getTime());
    }

}
