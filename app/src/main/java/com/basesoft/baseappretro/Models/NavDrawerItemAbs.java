package com.basesoft.baseappretro.Models;

/**
 * Created by yamil.marques on 9/17/15.
 */
public abstract class NavDrawerItemAbs {

    protected int type;
    protected String label;
    protected int id;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
