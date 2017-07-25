package com.astontech.bo;

import com.astontech.bo.interfaces.IGetFullName;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by Joshua.McCann on 6/22/2017.
 */

public class Vehicle extends VehicleModel implements Comparable, IGetFullName{
    //region PARAMETERS
    private int VehicleId;
    private int Year;
    private String LicensePlate;
    private String VIN;
    private String Color;
    private boolean IsPurchase;
    private int PurchasePrice;
    private Date PurchaseDate;
    private List<VehicleStatus> VehicleStatuses;
    //endregion

    //region CONSTRUCTORS
    public Vehicle() {
        this.VehicleStatuses = new ArrayList<>();
    }

    public Vehicle(String vehicleMake, String vehicleModel){
        this.VehicleStatuses = new ArrayList<>();
        this.setVehicleMakeName(vehicleMake);
        this.setVehicleModelName(vehicleModel);
    }

    public Vehicle(VehicleModel vehicleModel){
        this.setVehicleModelName(vehicleModel.getVehicleModelName());
        this.setVehicleMakeId(vehicleModel.getVehicleMakeId());
        this.setVehicleModelId(vehicleModel.getVehicleModelId());
        this.setVehicleMakeName(vehicleModel.getVehicleMakeName());
    }

    public Vehicle( int year, String licensePlate, String vin, String color, boolean isPurchase, int purchasePrice) {
        this.VehicleStatuses = new ArrayList<>();
        this.setYear(year);
        this.setLicensePlate(licensePlate);
        this.setVIN(vin);
        this.setColor(color);
        this.setIsPurchase(isPurchase);
        this.setPurchasePrice(purchasePrice);
    }
    //endregion

    //region GETTERS/SETTERS
    public int getVehicleId() {
        return VehicleId;
    }

    public void setVehicleModel(VehicleModel model){
        this.setVehicleModelId(model.getVehicleModelId());
        this.setVehicleMakeId(model.getVehicleMakeId());
        this.setVehicleModelName(model.getVehicleModelName());
        this.setVehicleMakeName(model.getVehicleMakeName());
    }

    public void setVehicleId(int vehicleId) {
        VehicleId = vehicleId;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public String getLicensePlate() {
        return LicensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        LicensePlate = licensePlate;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public boolean getIsPurchase() {
        return IsPurchase;
    }

    public void setIsPurchase(boolean isPurchase) {
        IsPurchase = isPurchase;
    }

    public int getPurchasePrice() {
        return PurchasePrice;
    }

    public void setPurchasePrice(int purchasePrice) {
        PurchasePrice = purchasePrice;
    }

    public Date getPurchaseDate() {
        return PurchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        PurchaseDate = purchaseDate;
    }

    public List<VehicleStatus> getVehicleStatuses() {
        return VehicleStatuses;
    }

    public void setVehicleStatuses(List<VehicleStatus> vehicleStatuses) {
        VehicleStatuses = vehicleStatuses;
    }
    //endregion


    public String ToString(){
        return "\nColor: " +this.getColor() + "\nYear: " + Integer.toString(this.getYear()) + "\nVehicle: " + this.getVehicleMakeName() + " " + this.getVehicleModelName() + "\nVIN: " + this.getVIN() + "\nLicense Plate: " + this.getLicensePlate() + "\nAvailable: " + Boolean.toString(!this.getIsPurchase()) + "\nPurchase Price: $" + Integer.toString(this.getPurchasePrice());
    }

    @Override
    public String getFullName(){
        return this.getVehicleMakeName() + " " + this.getVehicleModelName();
    }

    @Override
    public int compareTo(Object o) {
        Vehicle temp = (Vehicle) o;

        if(temp.getVehicleMakeName().toLowerCase().charAt(0) < this.getVehicleMakeName().toLowerCase().charAt(0))
            return 1;
        else if(temp.getVehicleMakeName().toLowerCase().charAt(0) > this.getVehicleMakeName().toLowerCase().charAt(0))
            return -1;
        else
            return 0;
    }
}
