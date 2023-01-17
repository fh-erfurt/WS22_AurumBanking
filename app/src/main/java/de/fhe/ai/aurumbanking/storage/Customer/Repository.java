package de.fhe.ai.aurumbanking.storage.Customer;

import android.app.Application;
import android.content.Context;
import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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

    public static Repository getRepository(Application application) {
        if (INSTANCE == null) {
            synchronized (Repository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Repository(application);
                }
            }
        }

        return INSTANCE;
    }

    public Repository(Context context) {
        CustomerBankingDatabase db = CustomerBankingDatabase.getDatabase(context);
        this.customerDao = db.customerDao();
    }


    private List<Customer> query(Callable<List<Customer>> query) {
        try {
            return CustomerBankingDatabase.query(query);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }


    public void insertCustomer(Customer customer) {
        CustomerBankingDatabase.execute(() -> customerDao.insertCustomer(customer));
    }

    public void deleteAllCustomer() {
        CustomerBankingDatabase.execute(() -> customerDao.deleteAll());
    }


    public void insertUserAccount(Customer customer, CustomerAddress customerAddress, CustomerCredentials newUserCredentials, Deposit deposit) {
        CustomerBankingDatabase.execute(() -> customerDao.insertUserAccount(customerDao.insertCustomer(customer), customerAddress, newUserCredentials, deposit));
    }

    public LiveData<List<Customer>> getAllCustomerData() {
        return customerDao.getAllCustomersData();
    }

    public LiveData<String> getCustomerEmailByCustomerId(long id) {
        return customerDao.getCustomerEmailByCustomerId(id);
    }

    public LiveData<Map<String, String>> getAllCustomerEmailAndPassword() {
        return customerDao.getAllCustomerEmailAndPassword();
    }

    public LiveData<Long> getCustomerIdByEmail(String email) {
        return customerDao.getCustomerIdByEmail(email);
    }

    public LiveData<String> getCustomerFirstNameByCustomerId(Long id) {
        return customerDao.getCustomerFirstNameByCustomerId(id);
    }

    public LiveData<String> getCustomerMidNameByCustomerId(Long id) {
        return customerDao.getCustomerMidNameByCustomerId(id);
    }

    public LiveData<String> getCustomerLastNameByCustomerId(Long id) {
        return customerDao.getCustomerLastNameByCustomerId(id);
    }

    public LiveData<String> getStreetnameByCustomerId(Long id) {
        return customerDao.getStreetnameByCustomerId(id);
    }

    public LiveData<String> getHousenumberByCustomerId(Long id) {
        return customerDao.getHousenumberByCustomerId(id);
    }

    public LiveData<String> getCityByCustomerId(Long id) {
        return customerDao.getCityByCustomerId(id);
    }

    public LiveData<String> getZipCodebyCustomerId(Long id) {
        return customerDao.getZipCodebyCustomerId(id);
    }

    public LiveData<String> getCountryCustomerId(Long id) {
        return customerDao.getCountryCustomerId(id);
    }


    public LiveData<Integer> getCustomerPhoneNumberByCustomerId(Long id) {
        return customerDao.getCustomerPhoneNumberByCustomerId(id);
    }



}

