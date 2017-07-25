package com.astontech.bo;

import java.util.Date;

/**
 * Created by Joshua.McCann on 6/22/2017.
 */
public class VehicleStatus extends BaseBO {
    //PARAMETERS
    private int VehicleStatusId;
    private Vehicle StatusOfVehicle;
    private EntityType VehicleStatusType;
    private String Notes;
    private Date CreateDate;

    //CONSTRUCTORS
    public VehicleStatus() {
        this.StatusOfVehicle = new Vehicle();
        this.VehicleStatusType = new EntityType();
    }

    public VehicleStatus(String notes) {
        this.StatusOfVehicle = new Vehicle();
        this.VehicleStatusType = new EntityType();
        this.setNotes(notes);
    }

    //GETTERS/SETTERS
    public int getVehicleStatusId() {
        return VehicleStatusId;
    }

    public void setVehicleStatusId(int vehicleStatusId) {
        VehicleStatusId = vehicleStatusId;
    }

    public Vehicle getStatusOfVehicle() {
        return StatusOfVehicle;
    }

    public void setStatusOfVehicle(Vehicle statusOfVehicle) {
        StatusOfVehicle = statusOfVehicle;
    }

    public EntityType getVehicleStatusType() {
        return VehicleStatusType;
    }

    public void setVehicleStatusType(EntityType vehicleStatusType) {
        VehicleStatusType = vehicleStatusType;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }
}
