package de.fhe.ai.aurumbanking.core;

import android.app.Application;
import android.util.Log;

import de.fhe.ai.aurumbanking.model.Customer;
import de.fhe.ai.aurumbanking.model.CustomerAddress;
import de.fhe.ai.aurumbanking.model.CustomerCredentials;
import de.fhe.ai.aurumbanking.model.Deposit;
import de.fhe.ai.aurumbanking.model.OrderInput;
import de.fhe.ai.aurumbanking.model.TransactionList;
import de.fhe.ai.aurumbanking.storage.customer.CustomerRepository;
import de.fhe.ai.aurumbanking.storage.deposit.DepositRepository;

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


        // ----------------------------- Initialize Customer Personal And Account Data ----------------------------
        // Create Repo instance - which in turn will init the Contact DB
        CustomerRepository customerRepository = new CustomerRepository(this);

        Faker faker = Faker.instance();

        customerRepository.deleteAllCustomer();

        Customer newCustomer = new Customer(faker.name().lastName(),
                faker.name().firstName(), "t@t.de", generatePhonenumber());

        Address addressFaker = new Faker().address();

        CustomerAddress customerAddress = new CustomerAddress(addressFaker.streetName(),addressFaker.streetAddressNumber(), addressFaker.city(),
                addressFaker.zipCode(), addressFaker.country());

        CustomerCredentials newUserCredentials = new CustomerCredentials("123");


        // ----------------------------- Initialize Customer Deposit and Transactions Data ----------------------------

        // TODO: Fix Float Value
        Deposit deposit = new Deposit((float) (Math.round(25000.00F *100)/100));

        TransactionList transactionList = new TransactionList(false);

        OrderInput orderInput = new OrderInput("Mainzer Werke GmBH","Deutsche Bank", "DE65069037901417863654",
                "HELEDEF1CEM", (float) (Math.round(2800F *100)/100), "Gehalt"  );

        // ----------------------------- Create Test Customer Account ----------------------------
        customerRepository.insertUserAccount(newCustomer, customerAddress, newUserCredentials, deposit, transactionList, orderInput);

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
