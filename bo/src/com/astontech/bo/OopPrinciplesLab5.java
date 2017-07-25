package com.astontech.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua.McCann on 6/27/2017.
 */
public class OopPrinciplesLab5 implements CharSequence {

    //region PROPERTIES
    private String objectString;
    //endregion

    //region CONSTRUCTORS
    public OopPrinciplesLab5() {}

    public OopPrinciplesLab5(String s){
        this.objectString = s;
    }
    //endregion

    //region GETTERS / SETTERS

    public String getObjectString() {
        return objectString;
    }

    public void setObjectString(String objectString) {
        this.objectString = objectString;
    }

    //endregion

    @Override
    public CharSequence subSequence(int start, int end) {
        if(start>end) {
            StringBuilder tempString = new StringBuilder();
            while (start >= end) {
                tempString.append(Character.toString(this.getObjectString().charAt(start)));
                start--;
            }
            return tempString;
        }
        else {
            return this.getObjectString().subSequence(start, end);
        }
    }

    @Override
    public char charAt(int index) {
        return this.getObjectString().charAt(index);
    }

    @Override
    public int length() {
        return this.getObjectString().length();
    }
}
