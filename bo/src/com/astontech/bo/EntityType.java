package com.astontech.bo;

/**
 * Created by Joshua.McCann on 6/22/2017.
 */
public class EntityType extends Entity {

    //region PROPERTIES

    private int EntityTypeId;
    private String EntityTypeName;

    //endregion

    //region CONSTRUCTORS

    public EntityType() {}

    public EntityType(String entityTypeName) {
        this.EntityTypeName = entityTypeName;
    }

    //endregion

    //region GETTERS / SETTERS

    public int getEntityTypeId() {
        return EntityTypeId;
    }

    public void setEntityTypeId(int entityTypeId) {
        EntityTypeId = entityTypeId;
    }

    public String getEntityTypeName() {
        return EntityTypeName;
    }

    public void setEntityTypeName(String entityTypeName) {
        EntityTypeName = entityTypeName;
    }

    //endregion

    //region METHODS

    @Override
    public String test_method() {
        return "sub method";
    }

    //endregion
}
