package com.astontech.bo;

/**
 * Created by Joshua.McCann on 6/22/2017.
 */
public class Entity extends BaseBO {
    //PARAMETERS
    private int EntityId;
    private String EntityName;

    //CONSTRUCTORS
    public Entity(){}

    public Entity(String entityName){
        this.setEntityName(entityName);
    }

    //GETTERS
    public int getEntityId(){
        return this.EntityId;
    }

    public String getEntityName(){
        return this.EntityName;
    }

    //SETTERS
    public void setEntityId(int entityId){
        this.EntityId = entityId;
    }

    public void setEntityName(String entityName){
        this.EntityName = entityName;
    }
}
