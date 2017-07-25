package com.astontech.bo;

/**
 * Created by Joshua.McCann on 6/22/2017.
 */
public class Phone extends BaseBO {
    
    //region PARAMETERS
    
    private int PhoneId;
    private EntityType PhoneType;
    private Client PhoneClient;
    private Person PhonePerson;
    private int AreaCode;
    private int PhoneNumber;
    private int PhoneNumberPost;

    //endregion

    //region CONSTRUCTORS
    
    public Phone(){
        this.PhoneType = new EntityType();
        this.PhoneClient = new Client();
        this.PhonePerson = new Person();
    }

    public Phone(int areaCode, int phoneNumber, int phoneNumberPost){
        this.PhoneType = new EntityType();
        this.PhoneClient = new Client();
        this.PhonePerson = new Person();
        this.setAreaCode(areaCode);
        this.setPhoneNumber(phoneNumber);
        this.setPhoneNumberPost(phoneNumberPost);
    }

    //endregion

    //region GETTERS/SETTERS
    
    public int getPhoneId() {
        return PhoneId;
    }

    public void setPhoneId(int phoneId) {
        PhoneId = phoneId;
    }

    public void setPhoneType(EntityType phoneType) {
        PhoneType = phoneType;
    }

    public Client getPhoneClient() {
        return PhoneClient;
    }

    public void setPhoneClient(Client phoneClient) {
        PhoneClient = phoneClient;
    }

    public Person getPhonePerson() {
        return PhonePerson;
    }

    public void setPhonePerson(Person phonePerson) {
        PhonePerson = phonePerson;
    }

    public int getAreaCode() {
        return AreaCode;
    }

    public void setAreaCode(int areaCode) {
        AreaCode = areaCode;
    }

    public int getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public int getPhoneNumberPost() {
        return PhoneNumberPost;
    }

    public void setPhoneNumberPost(int phoneNumberPost) {
        PhoneNumberPost = phoneNumberPost;
    }

    public String getAreaCodeString(){
        return "(" + Integer.toString(this.getAreaCode()) + ")";
    }

    public String getPhoneNumberString(){
        return Integer.toString(this.getPhoneNumber()).substring(0,3) + "-" + Integer.toString(this.getPhoneNumber()).substring(3);
    }

    //endregion
    
    //region CUSTOM METHODS

    public String getFullPhoneNumber(){
        return getAreaCodeString() + getPhoneNumberString();
    }

    public EntityType getPhoneType() {
        return PhoneType;
    }
    
    //endregion
   
}
