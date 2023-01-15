package de.fhe.ai.aurumbanking.storage.Customer;

import android.app.Application;
import android.content.Context;
import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import de.fhe.ai.aurumbanking.model.Customer;
import de.fhe.ai.aurumbanking.model.CustomerAddress;
import de.fhe.ai.aurumbanking.model.CustomerCredentials;
import de.fhe.ai.aurumbanking.model.Deposit;


public class Repository {

    public static final String LOG_TAG = "CustomerRepository";

    private CustomerDao customerDao;
    private static volatile Repository INSTANCE;

    public static Repository getRepository( Application application )
    {
        if( INSTANCE == null ) {
            synchronized ( Repository.class ) {
                if( INSTANCE == null ) {
                    INSTANCE = new Repository( application );
                }
            }
        }

        return INSTANCE;
    }

    public Repository(Context context ) {
        CustomerBankingDatabase db = CustomerBankingDatabase.getDatabase( context );
        this.customerDao = db.customerDao();
    }



    private List<Customer> query( Callable<List<Customer>> query )
    {
        try {
            return CustomerBankingDatabase.query( query );
        }
        catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }


    public void insertCustomer(Customer customer) {
        CustomerBankingDatabase.execute( () -> customerDao.insertCustomer(customer) );
    }

    public void deleteAllCustomer() {
        CustomerBankingDatabase.execute( () -> customerDao.deleteAll() );
    }


    public void insertUserAccount(Customer customer, CustomerAddress customerAddress, CustomerCredentials newUserCredentials, Deposit deposit) {
        CustomerBankingDatabase.execute( () -> customerDao.insertUserAccount( customerDao.insertCustomer(customer) ,customerAddress, newUserCredentials, deposit));
    }

    public LiveData<List<Customer>> getAllCustomerData(){
        return customerDao.getAllCustomersData();
    }

    public LiveData<String> getCustomerEmailByCustomerId(long id){

        // TODO: Always get testValue = null, but in DB there are some values
        // Query is working
        String testValue = customerDao.getCustomerEmailByCustomerId(id).getValue();

        // Transformations.map(getAllCustomerData(), cus -> {
        //     cus.stream().filter(Customer-> Customer.getEmail().contains("@")).collect(Collectors.toList())
        // });

        return customerDao.getCustomerEmailByCustomerId(id);
    }
}

