package com.astontech.bo;

import java.io.Serializable;

/**
 * Created by Joshua.McCann on 6/21/2017.
 */
public class BaseBO implements Serializable {

    private int Id;


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String test_method() {
        return "Super Method";
    }
}
