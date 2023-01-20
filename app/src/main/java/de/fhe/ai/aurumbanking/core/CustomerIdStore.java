package de.fhe.ai.aurumbanking.core;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class CustomerIdStore {

    private static String KEY_VALUE_STORE_FILE_NAME = "Customer_Id_Store";
    private static final long DEFAULT_LONG_VALUE = 0;

    private Application app;

    public CustomerIdStore(Application application) {
        this.app = application;
    }


    private SharedPreferences getPreferences() {
        return this.app.getSharedPreferences(KEY_VALUE_STORE_FILE_NAME, Context.MODE_PRIVATE);
    }

    public long getCustomerId(String key){
        return this.getPreferences().getLong(key, DEFAULT_LONG_VALUE);
    }

    public void setCustomerId(String key, long value){
        this.getPreferences().edit().putLong(key, value).apply();
    }


}
