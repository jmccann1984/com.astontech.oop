package com.astontech.bo.interfaces;

import com.astontech.bo.Employee;

/**
 * Created by Joshua.McCann on 6/26/2017.
 */
public class Home implements ILocation {

    private String Address;
    private Employee Owner;

    //region GETTERS / SETTERS


    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Employee getOwner() {
        return Owner;
    }

    public void setOwner(Employee owner) {
        Owner = owner;
    }

    //endregion

    @Override
    public int numberOfWorkspaces() {
        return 1;
    }

    @Override
    public boolean canHaveMeetings() {
        return false;
    }

    @Override
    public String getLocationNames() {
        return this.Owner.getFirstName() + "'s Home";
    }

    @Override
    public boolean hasCoffee() {
        return true;
    }
}
