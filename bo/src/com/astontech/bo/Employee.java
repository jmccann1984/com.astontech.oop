package com.astontech.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Joshua.McCann on 6/22/2017.
 */
public class Employee extends Person{
    //region PARAMETERS

    private int EmployeeId;
    private Date HireDate;
    private Date TermDate;
    private Date BirthDate;
    private List<Email> Emails;
    private Date CreateDate;

    //endregion

    //region CONSTRUCTORS

    public Employee(){
        this.Emails = new ArrayList<>();
    }

    public Employee(String firstName, String lastName){
        this.Emails = new ArrayList<>();
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }

    public Employee(int employeeId, String firstName){
        this.Emails = new ArrayList<>();
        this.setFirstName(firstName);
    }

    public Employee(String lastName){
        this.Emails = new ArrayList<>();
        this.setLastName(lastName);
    }

    //endregion

    //region GETTERS / SETTERS

    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int employeeId) {
        EmployeeId = employeeId;
    }

    public Date getHireDate() {
        return HireDate;
    }

    public void setHireDate(Date hireDate) {
        HireDate = hireDate;
    }

    public Date getTermDate() {
        return TermDate;
    }

    public void setTermDate(Date termDate) {
        TermDate = termDate;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }

    public List<Email> getEmails() {
        return Emails;
    }

    public void setEmails(List<Email> emails) {
        Emails = emails;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    //endregion

}

