package com.astontech.bo;

import java.util.Date;

/**
 * Created by Joshua.McCann on 6/22/2017.
 */
public class Training extends BaseBO {
    //PARAMETERS
    private int TrainingId;
    private Employee TrainingEmployee;
    private String TrainingName;
    private Date CreateDate;

    //CONSTRUCTORS
    public Training() {
        this.TrainingEmployee = new Employee();
    }

    public Training(String trainingName) {
        this.TrainingEmployee = new Employee();
        this.setTrainingName(trainingName);

    }

    //GETTERS/SETTERS
    public int getTrainingId() {
        return TrainingId;
    }

    public void setTrainingId(int trainingId) {
        TrainingId = trainingId;
    }

    public Employee getTrainingEmployee() {
        return TrainingEmployee;
    }

    public void setTrainingEmployee(Employee trainingEmployee) {
        TrainingEmployee = trainingEmployee;
    }

    public String getTrainingName() {
        return TrainingName;
    }

    public void setTrainingName(String trainingName) {
        TrainingName = trainingName;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }
}
