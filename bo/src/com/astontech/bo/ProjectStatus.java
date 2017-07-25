package com.astontech.bo;

import java.util.Date;

/**
 * Created by Joshua.McCann on 6/22/2017.
 */
public class ProjectStatus extends BaseBO{
    //PARAMETERS
    private int ProjectStatusId;
    private Project StatusOfProject;
    private EntityType StatusType;
    private String Notes;
    private int PercentComplete;
    private Date StartDate;
    private Date EndDate;



    //CONSTRUCTORS
    public ProjectStatus() {
        this.StatusOfProject = new Project();
        this.StatusType = new EntityType();
    }

    public ProjectStatus(String notes, int percentComplete) {
        this.StatusOfProject = new Project();
        this.StatusType = new EntityType();
        Notes = notes;
        PercentComplete = percentComplete;
    }

    //GETTERS/SETTERS
    public int getProjectStatusId() {
        return ProjectStatusId;
    }

    public void setProjectStatusId(int projectStatusId) {
        ProjectStatusId = projectStatusId;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public int getPercentComplete() {
        return PercentComplete;
    }

    public void setPercentComplete(int percentComplete) {
        PercentComplete = percentComplete;
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

    public void setStatusOfProject(Project statusOfProject){
        this.StatusOfProject = statusOfProject;
    }

    public Project getStatusOfProject(){
        return this.StatusOfProject;
    }

    public EntityType getStatusType() {
        return StatusType;
    }

    public void setStatusType(EntityType statusType) {
        StatusType = statusType;
    }
}
