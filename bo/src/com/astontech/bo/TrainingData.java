package com.astontech.bo;

import java.util.Date;

/**
 * Created by Joshua.McCann on 6/22/2017.
 */
public class TrainingData extends BaseBO{
    //PARAMETERS
    private int TrainingDateId;
    private Training DataOfTraining;
    private EntityType TrainingDataType;
    private String TrainingDataValue;
    private Date TrainingDate;

    //CONSTRUCTORS
    public TrainingData() {
        this.DataOfTraining = new Training();
        this.TrainingDataType = new EntityType();
    }

    public TrainingData(String trainingDataValue) {
        this.DataOfTraining = new Training();
        this.TrainingDataType = new EntityType();
        this.setTrainingDataValue(trainingDataValue);
    }

    //GETTERS/SETTERS
    public int getTrainingDateId() {
        return TrainingDateId;
    }

    public void setTrainingDateId(int trainingDateId) {
        TrainingDateId = trainingDateId;
    }

    public Training getDataOfTraining() {
        return DataOfTraining;
    }

    public void setDataOfTraining(Training dataOfTraining) {
        DataOfTraining = dataOfTraining;
    }

    public EntityType getTrainingDataType() {
        return TrainingDataType;
    }

    public void setTrainingDataType(EntityType trainingDataType) {
        TrainingDataType = trainingDataType;
    }

    public String getTrainingDataValue() {
        return TrainingDataValue;
    }

    public void setTrainingDataValue(String trainingDataValue) {
        TrainingDataValue = trainingDataValue;
    }

    public Date getTrainingDate() {
        return TrainingDate;
    }

    public void setTrainingDate(Date trainingDate) {
        TrainingDate = trainingDate;
    }
}
