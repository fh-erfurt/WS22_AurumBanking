package de.fhe.ai.aurumbanking.core;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Helper extends Fragment {

    private static final Helper helper = new Helper();
    private final String STORE_KEY_COUNTER = "CustomerId";



    public static Helper getHelperInstance() {
        return helper;
    }

    public Long getCustomerId(Application application) {
        CustomerIdStore customerIdStore  = new CustomerIdStore(application);
        Log.i("Check Helper getID", "Info:" + customerIdStore.getCustomerId(STORE_KEY_COUNTER));
        return customerIdStore.getCustomerId(STORE_KEY_COUNTER);

    }


    @RequiresApi(Build.VERSION_CODES.O)
    public String getDate(Boolean newLineFlag ) {

        ZoneId zone = ZoneId.of("Europe/Berlin");
        LocalDateTime current = LocalDateTime.now(zone);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        if (newLineFlag) {
            return current.format(formatter).replace(" ", "\n");
        }
        return current.format(formatter);
    }
}
