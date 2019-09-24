package com.meetone.sdk.demo;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.UUID;

public class CommonUtils {

    public static String getUUID(Context context) {
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
        String identity = preference.getString("identity_android", null);
        if (identity == null) {
            //UUID uuid = new  java.util.UUID(64,64);
            //uuid.randomUUID()
            identity = UUID.randomUUID().toString();
            //identity = java.util.UUID.randomUUID().toString();
            preference.edit().putString("identity_android", identity).commit();
        }
        return identity;
    }
}
