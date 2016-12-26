package com.basesoft.baseappretro.Models;

import com.basesoft.baseappretro.Common.Constants;

/**
 * Created by yamil.marques on 9/17/15.
 */
public class NavItemNormal extends NavDrawerItemAbs {

    private int iconId;

    public NavItemNormal(String label, int icon, int id){
        this.label = label;
        this.iconId = icon;
        this.type = Constants.NAV_ITEM_TYPE_NORMAL;
        this.id = id;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
}
