package de.fhe.ai.aurumbanking.core;

import android.app.Application;
import android.util.Log;

import de.fhe.ai.aurumbanking.model.Customer;
import de.fhe.ai.aurumbanking.model.CustomerAddress;
import de.fhe.ai.aurumbanking.model.CustomerCredentials;
import de.fhe.ai.aurumbanking.model.Deposit;
import de.fhe.ai.aurumbanking.model.TransactionList;
import de.fhe.ai.aurumbanking.storage.customer.CustomerRepository;
import de.fhe.ai.aurumbanking.storage.deposit.DepositRepository;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class is defined at startpoint of the app. The class contains predefined and essential data and functionalities for the database and logs.
 */
public class MyApplication extends Application {


    private static final String LOG_TAG = "Banking App Init";

    @Override
    public void onCreate() {
        super.onCreate();
        testDatabase();

        Log.i(LOG_TAG, "On Create finished.");
    }


    /**
     * Initialize database with customer data, login credentials, deposit and transaction list.
     * For the test of the app there is only one user predefined.
     * Customerdata are generated with Faker Library
     */
    private void testDatabase() {


        // ----------------------------- Initialize Customer Personal And Account Data ----------------------------
        // Create Repo instance - which in turn will init the Contact DB
        CustomerRepository customerRepository = new CustomerRepository(this);
        DepositRepository depositRepository = new DepositRepository(this);

        Faker faker = Faker.instance();

        customerRepository.deleteAllCustomer();

        Customer newCustomer = new Customer(faker.name().lastName(),
                faker.name().firstName(), "t@t.de", generatePhonenumber());

        Address addressFaker = new Faker().address();

        CustomerAddress customerAddress = new CustomerAddress(addressFaker.streetName(),addressFaker.streetAddressNumber(), addressFaker.city(),
                addressFaker.zipCode(), addressFaker.country());

        CustomerCredentials newUserCredentials = new CustomerCredentials("123");


        // ----------------------------- Initialize Customer Deposit and Transactions Data ----------------------------

        Deposit deposit = new Deposit(new BigDecimal("7500.00"));

        TransactionList transactionList = new TransactionList(false, "01.02.2023", "Gehalt",
                "DE65069037901417863654", "HELEDEF1CEM", "Deutsche Bank",  (new BigDecimal("2400.00")) ,"Gehalt" );

        // ----------------------------- Create Test Customer Account ----------------------------
        customerRepository.insertUserAccount(newCustomer, customerAddress, newUserCredentials, deposit, transactionList);
        //depositRepository

    }


    public String getUniqueId() {
        return String.format("%s_%s", UUID.randomUUID().toString().substring(0, 5), System.currentTimeMillis() / 1000);
    }

    /**
     * generate random email in a certain regular expression
     * @return String
     */
    public String generateRandomEmail() {
        return String.format("%s@%s", getUniqueId(), "yourHostName.com");
    }

    /**
     *  generate random phone number in a certain regular expression
     * @return int
     */
    public int generatePhonenumber() {
        return ThreadLocalRandom.current().nextInt(10000000, 99999999);
    }


}
