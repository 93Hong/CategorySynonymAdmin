package com.tmoncorp.admin.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by sk2rldnr on 2017-07-11.
 */
@Entity
public class Category {

    @Id
    private int catNo;

    private String firstCatName;
    private String secondCatName;
    private String thirdCatName;
    private String fourthCatName;

    private String sameWords;
    private String similarWords;

    private double weight;
    private boolean availability;

    private String worker;
    private Timestamp workDate;

    public Category() {
    }

    public Category(int catNo, String firstCatName, String secondCatName, String thirdCatName, String fourthCatName, String sameWords, String similarWords, double weight, boolean availability, String worker, Timestamp workDate) {
        this.catNo = catNo;
        this.firstCatName = firstCatName;
        this.secondCatName = secondCatName;
        this.thirdCatName = thirdCatName;
        this.fourthCatName = fourthCatName;
        this.sameWords = sameWords;
        this.similarWords = similarWords;
        this.weight = weight;
        this.availability = availability;
        this.worker = worker;
        this.workDate = workDate;
    }

    // todo : refactoring
    public String makeCsvString() {

        return (catNo + "") + ","
                + nullToEmptyString(firstCatName) + ","
                + nullToEmptyString(secondCatName) + ","
                + nullToEmptyString(thirdCatName) + ","
                + nullToEmptyString(fourthCatName) + ","
                + nullToEmptyString(sameWords) + ","
                + nullToEmptyString(similarWords) + ","
                + (weight + "") + ","
                + (availability + "") + ","
                + nullToEmptyString(worker) + ","
                + new SimpleDateFormat("MM/dd/yyyy").format(workDate);
    }

    private String nullToEmptyString(String s) {
        return s == null ? "" : "\"" + s + "\"";
    }

    public int getCatNo() {
        return catNo;
    }

    public String getFirstCatName() {
        return firstCatName;
    }

    public String getSecondCatName() {
        return secondCatName;
    }

    public String getThirdCatName() {
        return thirdCatName;
    }

    public String getFourthCatName() {
        return fourthCatName;
    }

    public String getSameWords() {
        return sameWords;
    }

    public String getSimilarWords() {
        return similarWords;
    }

    public double getWeight() {
        return weight;
    }

    public boolean getAvailability() {
        return availability;
    }

    public String getWorker() {
        return worker;
    }

    public Timestamp getWorkDate() {
        return workDate;
    }

    public void setSameWords(String sameWords) {
        this.sameWords = sameWords;
    }

    public void setSimilarWords(String similarWords) {
        this.similarWords = similarWords;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public void setWorkDate(Timestamp workDate) {
        this.workDate = workDate;
    }
}
