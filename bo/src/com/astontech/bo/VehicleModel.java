package com.astontech.bo;

/**
 * Created by Joshua.McCann on 6/22/2017.
 */
public class VehicleModel extends VehicleMake {
    //PARAMETERS
    private int VehicleModelId;
    private String VehicleModelName;

    //CONSTRUCTORS
    public VehicleModel() {}

    public VehicleModel(String vehicleModelName) {
        this.setVehicleModelName(vehicleModelName);
    }

    public VehicleModel(VehicleMake vehicleMake){
        this.setVehicleMakeId(vehicleMake.getVehicleMakeId());
        this.setVehicleMakeName(vehicleMake.getVehicleMakeName());
    }

    public VehicleModel(int vehicleModelId, String vehicleModelName, int vehicleMakeId) {
        this.setVehicleModelId(vehicleModelId);
        this.setVehicleModelName(vehicleModelName);
        this.setVehicleMakeId(vehicleMakeId);
    }

    //GETTERS/SETTERS
    public int getVehicleModelId() {
        return VehicleModelId;
    }

    public void setVehicleModelId(int vehicleModelId) {
        VehicleModelId = vehicleModelId;
    }

    public String getVehicleModelName() {
        return VehicleModelName;
    }

    public void setVehicleModelName(String vehicleModelName) {
        VehicleModelName = vehicleModelName;
    }
}
