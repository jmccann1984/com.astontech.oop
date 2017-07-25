package com.astontech.bo;

import common.helpers.*;

import javax.swing.text.html.parser.*;
import java.util.Date;

/**
 * Created by Joshua.McCann on 6/22/2017.
 */
public class Address extends BaseBO{
    //PARAMETERS
    private int AddressId;
    private Client ClientAddress;
    private Person PersonAddress;
    private EntityType AddressType;
    private String AddressNumber;
    private String Street;
    private String Street02;
    private String City;
    private Date DateCreate;

    //CONSTRUCTORS
    public Address(){
        ClientAddress = new Client();
        PersonAddress = new Person();
        AddressType = new EntityType();
    }

    public Address(String addressNumber, String street, String street02, String city){
        ClientAddress = new Client();
        PersonAddress = new Person();
        AddressType = new EntityType();

        this.setAddressNumber(addressNumber);
        this.setStreet(street);
        this.setStreet02(street02);
        this.setCity(city);
    }
    //GETTERS
    public int getAddressId(){
        return this.AddressId;
    }

    public Client getClient(){
        return this.ClientAddress;
    }

    public Person getPerson(){
        return this.PersonAddress;
    }

    public EntityType getEntityType(){
        return this.AddressType;
    }

    public String getAddressNumber(){
        return this.AddressNumber;
    }

    public String getStreet(){
        return this.Street;
    }

    public String getStreet02(){
        return this.Street02;
    }

    public String getCity(){
        return this.City;
    }

    public Date getDateCreate(){
        return this.DateCreate;
    }

    //SETTERS
    public void setAddressId(int addressId){
        this.AddressId = addressId;
    }

    public void setClient(Client clientAddress){
        this.ClientAddress = clientAddress;
    }

    public void setPerson(Person personAddress){
        this.PersonAddress = personAddress;
    }

    public void setEntityType(EntityType addressType){
        this.AddressType = addressType;
    }

    public void setAddressNumber(String addressNumber){
        this.AddressNumber = addressNumber;
    }

    public void setStreet(String street){
        this.Street = street;
    }

    public void setStreet02(String street02){
        this.Street02 = street02;
    }

    public void setCity(String city){
        this.City = city;
    }

    public void setDateCreate(Date dateCreate){
        this.DateCreate = dateCreate;
    }

    public String getFullAddress(){
        StringBuilder fullAddress = new StringBuilder();

        if (StringHelper.isNullOrEmpty(getAddressNumber()) && StringHelper.isNullOrEmpty(getStreet()) && StringHelper.isNullOrEmpty(getStreet02()) && StringHelper.isNullOrEmpty(getCity()))
            return "No Address Available";
        else {
            if (!StringHelper.isNullOrEmpty(getAddressNumber()))
                fullAddress.append(getAddressNumber());
            if (!StringHelper.isNullOrEmpty(getStreet())) {
                if (StringHelper.isNullOrEmpty(fullAddress.toString()))
                    fullAddress.append(getStreet());
                else
                    fullAddress.append(" " + getStreet());
            }
            if (!StringHelper.isNullOrEmpty(getStreet02())) {
                if (StringHelper.isNullOrEmpty(fullAddress.toString()))
                    fullAddress.append(getStreet02());
                else
                    fullAddress.append(" " + getStreet02());
            }
            if (!StringHelper.isNullOrEmpty(getCity())) {
                if (StringHelper.isNullOrEmpty(fullAddress.toString()))
                    fullAddress.append(getCity());
                else
                    fullAddress.append(", " + getCity());
            }
            return fullAddress.toString();
        }
    }
}
