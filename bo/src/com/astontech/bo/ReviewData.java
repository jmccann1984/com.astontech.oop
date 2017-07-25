package com.astontech.bo;

import java.util.Date;

/**
 * Created by Joshua.McCann on 6/22/2017.
 */
public class ReviewData extends BaseBO{
    private int ReviewDataId;
    private Review DataOfReview;
    private EntityType ReviewDataType;
    private String ReviewDataValue;
    private Date CreateDate;

    //CONSTRUCTORS
    public ReviewData() {
        this.DataOfReview = new Review();
        this.ReviewDataType = new EntityType();
    }

    public ReviewData(String ReviewDataValue) {
        this.DataOfReview = new Review();
        this.ReviewDataType = new EntityType();
        this.setReviewDataValue(ReviewDataValue);
    }

    //GETTERS/SETTERS
    public int getReviewDataId() {
        return ReviewDataId;
    }

    public void setReviewDataId(int reviewDataId) {
        ReviewDataId = reviewDataId;
    }

    public Review getDataOfReview() {
        return DataOfReview;
    }

    public void setDataOfReview(Review dataOfReview) {
        DataOfReview = dataOfReview;
    }

    public EntityType getReviewDataTypeId() {
        return ReviewDataType;
    }

    public void setReviewDataTypeId(EntityType reviewDataTypeId) {
        ReviewDataType = reviewDataTypeId;
    }

    public String getReviewDataValue() {
        return ReviewDataValue;
    }

    public void setReviewDataValue(String reviewDataValue) {
        ReviewDataValue = reviewDataValue;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }
}
