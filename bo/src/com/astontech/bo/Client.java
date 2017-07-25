package com.astontech.bo;

import java.util.Date;

/**
 * Created by Joshua.McCann on 6/22/2017.
 */
public class Client extends BaseBO {
    //PARAMETERS
    private int ClientId;
    private String ClientName;
    private Date CreateDate;

    //CONSTRUCTORS
    public Client(){}

    public Client(String clientName){
        this.setClientName(clientName);
    }

    //GETTERS
    public int getClientId(){
        return this.ClientId;
    }

    public String getClientName(){
        return this.ClientName;
    }

    public Date getCreateDate(){
        return this.CreateDate;
    }

    //SETTERS
    public void setClientId(int clientId){
        this.ClientId = clientId;
    }

    public void setClientName(String clienName){
        this.ClientName = clienName;
    }

    public void setCreateDate(Date createDate){
        this.CreateDate = createDate;
    }

}
