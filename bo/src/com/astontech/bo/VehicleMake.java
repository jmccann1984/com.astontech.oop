package com.astontech.bo;

/**
 * Created by Joshua.McCann on 6/22/2017.
 */
public class VehicleMake extends BaseBO {
    //PARAMETERS
    private int VehicleMakeId;
    private String VehicleMakeName;

    //CONSTRUCTORS
    public VehicleMake() {}

    public VehicleMake(String vehicleMakeName) {
        this.setVehicleMakeName(vehicleMakeName);
    }

    //GETTERS/SETTERS
    public int getVehicleMakeId() {
        return VehicleMakeId;
    }

    public void setVehicleMakeId(int vehicleMakeId) {
        VehicleMakeId = vehicleMakeId;
    }

    public String getVehicleMakeName() {
        return VehicleMakeName;
    }

    public void setVehicleMakeName(String vehicleMakeName) {
        VehicleMakeName = vehicleMakeName;
    }
}
