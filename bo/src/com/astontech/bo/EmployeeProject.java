package com.astontech.bo;

import java.util.Date;

/**
 * Created by Joshua.McCann on 6/22/2017.
 */
public class EmployeeProject extends Project {
    //PARAMETERS
    private int EmployeeProjectId;
    private Employee EmployeeOnProject;
    private EntityType EmployeeProjectType;
    private Vehicle EmployeeProjectVehicle;
    private Date EmployeeProjectStartDate;
    private Date EmployeeProjectEndDate;
    private String Notes;

    //CONSTRUCTORS
    public EmployeeProject(){
        EmployeeOnProject = new Employee();
        EmployeeProjectType = new EntityType();
        EmployeeProjectVehicle = new Vehicle();

    }

    public EmployeeProject(String notes){
        EmployeeOnProject = new Employee();
        EmployeeProjectType = new EntityType();
        EmployeeProjectVehicle = new Vehicle();
        this.setNotes(notes);
    }



    //GETTERS / SETTERS

    public int getEmployeeProjectId() {
        return EmployeeProjectId;
    }

    public void setEmployeeProjectId(int employeeProjectId) {
        EmployeeProjectId = employeeProjectId;
    }

    public Employee getEmployeeOnProject() {
        return EmployeeOnProject;
    }

    public void setEmployeeOnProject(Employee employeeOnProject) {
        EmployeeOnProject = employeeOnProject;
    }

    public EntityType getEmployeeProjectType() {
        return EmployeeProjectType;
    }

    public void setEmployeeProjectType(EntityType employeeProjectType) {
        EmployeeProjectType = employeeProjectType;
    }

    public Vehicle getEmployeeProjectVehicle() {
        return EmployeeProjectVehicle;
    }

    public void setEmployeeProjectVehicle(Vehicle employeeProjectVehicle) {
        EmployeeProjectVehicle = employeeProjectVehicle;
    }

    public Date getEmployeeProjectStartDate() {
        return EmployeeProjectStartDate;
    }

    public void setEmployeeProjectStartDate(Date employeeProjectEndDate) {
        EmployeeProjectStartDate = employeeProjectEndDate;
    }

    public Date getEmployeeProjectEndDate() {
        return EmployeeProjectEndDate;
    }

    public void setEmployeeProjectEndDate(Date employeeProjectEndDate) {
        EmployeeProjectEndDate = employeeProjectEndDate;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

}
