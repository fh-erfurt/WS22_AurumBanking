package de.fhe.ai.aurumbanking.core;

import android.app.Application;
import android.net.wifi.hotspot2.pps.Credential;
import android.util.Log;

import de.fhe.ai.aurumbanking.model.Customer;
import de.fhe.ai.aurumbanking.model.CustomerAddress;
import de.fhe.ai.aurumbanking.model.CustomerCredentials;
import de.fhe.ai.aurumbanking.model.Deposit;
import de.fhe.ai.aurumbanking.storage.Customer.Repository;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;


public class MyApplication extends Application {


    private static final String LOG_TAG = "Banking App Init";

    // private SettingsHandler settingsHandler;

    @Override
    public void onCreate() {
        super.onCreate();

        // this.settingsHandler = new SettingsHandler(this);

        testDatabase();

        Log.i(LOG_TAG, "On Create finished.");
    }


    private void testDatabase() {

        // Create Repo instance - which in turn will init the Contact DB
        Repository customerRepository = new Repository(this);

        Faker faker = Faker.instance();

        customerRepository.deleteAllCustomer();


        //for(int index = 0; index < 3; index++){
        //    Customer newCustomer = new Customer(faker.name().lastName(),
        //            "", faker.name().firstName(),generateRandomEmail(), generatePhonenumber());
        //
        //    customerRepository.insertCustomer(newCustomer);
        //}

        Customer newCustomer = new Customer(faker.name().lastName(),
                         "", faker.name().firstName(),generateRandomEmail(), generatePhonenumber());

        Address addressFaker = new Faker().address();

        CustomerAddress customerAddress = new CustomerAddress(addressFaker.streetName(),addressFaker.streetAddressNumber(), addressFaker.city(),
                addressFaker.zipCode(), addressFaker.country());

        CustomerCredentials newUserCredentials = new CustomerCredentials("123");

        Deposit deposit = new Deposit(25000.00F);

        // TODO: erstmal rausgelassen, bei Erstellung der Datenbank wird ein Kunde mit Addresse angelegt
        // customerRepository.insertCustomer(newCustomer);

        customerRepository.insertUserAccount(newCustomer, customerAddress, newUserCredentials, deposit);



    }


    public String getUniqueId() {
        return String.format("%s_%s", UUID.randomUUID().toString().substring(0, 5), System.currentTimeMillis() / 1000);
    }

    public String generateRandomEmail() {
        return String.format("%s@%s", getUniqueId(), "yourHostName.com");
    }


    public int generatePhonenumber() {
        return ThreadLocalRandom.current().nextInt(10000000, 99999999);
    }


}