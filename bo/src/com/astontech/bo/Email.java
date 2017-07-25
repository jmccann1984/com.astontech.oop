package com.astontech.bo;

/**
 * Created by Joshua.McCann on 6/22/2017.
 */
public class Email extends BaseBO {
    //region PARAMETERS

    private int EmailId;
    private String EmailAddress;
    private Employee EmailEmployee;
    private EntityType EmailType;

    //endregion

    //region CONSTRUCTORS

    public Email(){
        this.EmailType = new EntityType();
    }

    public Email(String emailAddress){
        this.EmailType = new EntityType();
        this.setEmailAddress(emailAddress);
    }

    //endregion

    //region GETTERS / SETTERS

    public void setEmailId(int emailId) {
        EmailId = emailId;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public EntityType getEmailType() {
        return EmailType;
    }

    public void setEmailType(EntityType emailType) {
        EmailType = emailType;
    }

    public int getEmailId() {
        return EmailId;
    }

    public Employee getEmailEmployee() {
        return EmailEmployee;
    }

    public void setEmailEmployee(Employee emailEmployee) {
        EmailEmployee = emailEmployee;
    }

    //endregion
}
