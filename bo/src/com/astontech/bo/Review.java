package com.astontech.bo;

import java.util.Date;

/**
 * Created by Joshua.McCann on 6/22/2017.
 */
public class Review extends BaseBO {
    //PARAMETERS
    private int ReviewId;
    private String ReviewName;
    private Date ReviewDate;
    private Employee ReviewOfEmployee;

    //CONSTRUCTORS
    public Review(){
        this.ReviewOfEmployee = new Employee();
    }

    public Review(String reviewName) {
        this.ReviewOfEmployee = new Employee();
        ReviewName = reviewName;
    }

    //GETTERS/SETTERS
    public int getReviewId() {
        return ReviewId;
    }

    public void setReviewId(int reviewId) {
        ReviewId = reviewId;
    }

    public String getReviewName() {
        return ReviewName;
    }

    public void setReviewName(String reviewName) {
        ReviewName = reviewName;
    }

    public Date getReviewDate() {
        return ReviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        ReviewDate = reviewDate;
    }

    public Employee getReviewOfEmployee() {
        return ReviewOfEmployee;
    }

    public void setReviewOfEmployee(Employee reviewOfEmployee) {
        ReviewOfEmployee = reviewOfEmployee;
    }
}
