package com.astontech.bo;

import com.astontech.bo.interfaces.IGetFullName;
import common.helpers.*;

import java.util.Date;

/**
 * Created by Joshua.McCann on 6/21/2017.
 */
public class Person extends BaseBO implements Comparable, IGetFullName{

    //region PROPERTIES

    private int PersonId;
    private String Title;
    private transient String FirstName;
    private String LastName;
    private Date CreateDate;
    private String DisplayFirstName;
    private transient byte IsDeleted;
    private char Gender;

    private static final long serialVersionUID = 5462223360056l;

    //endregion

    //region CONSTRUCTORS

    public Person() {

    }

    //endregion

    //region GETTERS / SETTERS

    public void setPersonId(int personid){
        this.PersonId = personid;
    }

    public void setTitle(String title){
        this.Title = title;
    }

    public void setFirstName(String firstname){
        this.FirstName = firstname;
    }

    public void setLastName(String lastname){
        this.LastName = lastname;
    }

    public void setDisplayFirstName(String displayfirstname){
        this.DisplayFirstName = displayfirstname;
    }

    public int getPersonId(){
        return this.PersonId;
    }

    public String getTitle(){
        return this.Title;
    }

    public String getFirstName(){
        return this.FirstName;
    }

    public String getLastName(){
        return this.LastName;
    }

    public String getDisplayFirstName(){
        return this.DisplayFirstName;
    }

    public char getGender(){
        return this.Gender;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    public byte getIsDeleted() {
        return IsDeleted;
    }

    public void setIsDeleted(byte isDeleted) {
        IsDeleted = isDeleted;
    }

    public void setGender(char gender) {
        Gender = gender;
    }

    //endregion

    //region CUSTOM METHODS

    //notes:    this will become an extension method.

    @Override
    public String getFullName(){
        if(StringHelper.isNullOrEmpty(this.FirstName) && StringHelper.isNullOrEmpty(this.LastName)){
            return "No Name Set";
        }
        else{
            if(StringHelper.isNullOrEmpty(this.FirstName))
                return this.LastName;
            else if (StringHelper.isNullOrEmpty(this.LastName))
                return this.FirstName;
            else
                return this.FirstName + " " + this.LastName;
        }

    }

    @Override
    public int compareTo(Object o) {

        Person temp = (Person) o;

        if (temp.getLastName().toLowerCase().charAt(0) < this.getLastName().toLowerCase().charAt(0))
            return -1;
        else if (temp.getLastName().toLowerCase().charAt(0) > this.getLastName().toLowerCase().charAt(0))
            return 1;
        else
            return 0;
    }

    public String ToString() {
        return "PersonId=" + this.PersonId + " Full Name="  + this.getFullName() + " Gender=" + this.Gender + " Active=" + this.IsDeleted;
    }

    //endregion
}
