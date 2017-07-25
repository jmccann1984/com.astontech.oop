package com.astontech.bo;

/**
 * Created by Joshua.McCann on 6/22/2017.
 */
public class ClientContact extends Person{
    //region PARAMETERS
    private int ClientContactId;
    private Client ContactForClient;
    private EntityType ContactType;
    //endregion

    //region CONSTRUCTORS
    public ClientContact(){
        this.ContactForClient = new Client();
        this.ContactType = new EntityType();
    }
    //endregion

    //region GETTERS
    public int getClientContactId(){
        return this.ClientContactId;
    }

    public Client getClient(){
        return this.ContactForClient;
    }

    public EntityType getEntityType(){
        return this.ContactType;
    }
    //endregion

    //region SETTERS
    public void setClientContactId(int clientContactId){
        this.ClientContactId = clientContactId;
    }

    public void setClientId(Client contactForClient){
        this.ContactForClient = contactForClient;
    }

    public void setEntityTypeId(EntityType contactType){
        this.ContactType = contactType;
    }
    //endregion
}
