package de.inces.nearcon.app.util;

import android.content.Context;

public class DynamicResources {

    private Context context;

    public DynamicResources(Context context) {
        this.context = context;
    }

    public int findDrawableByName(String name) {
        name = "@drawable/icon_" + name;
        return this.context.getResources().getIdentifier(name, null, context.getPackageName());
    }

}
