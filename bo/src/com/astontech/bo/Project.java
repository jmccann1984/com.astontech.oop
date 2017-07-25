package com.astontech.bo;

import java.util.Date;

/**
 * Created by Joshua.McCann on 6/22/2017.
 */
public class Project extends BaseBO {
    //PARAMETERS
    private int ProjectId;
    private Client ProjectClient;
    private EntityType ProjectType;
    private int Rate;
    private Date StartDate;
    private Date EndDate;
    private String ProjectName;

    //CONSTRUCTORS
    public Project() {
        this.ProjectClient = new Client();
        this.ProjectType = new EntityType();
    }

    public Project(int rate, String projectName) {
        this.ProjectClient = new Client();
        this.ProjectType = new EntityType();
        this.setRate(rate);
        this.setProjectName(projectName);
    }

    //GETTERS/SETTERS
    public int getProjectId() {
        return ProjectId;
    }

    public void setProjectId(int projectId) {
        ProjectId = projectId;
    }

    public Client getProjectClient() {
        return ProjectClient;
    }

    public void setProjectClient(Client projectClient) {
        ProjectClient = projectClient;
    }

    public EntityType getProjectType() {
        return ProjectType;
    }

    public void setProjectType(EntityType projectType) {
        ProjectType = projectType;
    }

    public int getRate() {
        return Rate;
    }

    public void setRate(int rate) {
        Rate = rate;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }
}
