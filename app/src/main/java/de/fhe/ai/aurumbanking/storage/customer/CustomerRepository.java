package de.fhe.ai.aurumbanking.storage.customer;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import de.fhe.ai.aurumbanking.model.Customer;
import de.fhe.ai.aurumbanking.model.CustomerAddress;
import de.fhe.ai.aurumbanking.model.CustomerCredentials;
import de.fhe.ai.aurumbanking.model.Deposit;
import de.fhe.ai.aurumbanking.model.TransactionList;
import de.fhe.ai.aurumbanking.storage.core.CustomerBankingDatabase;


public class CustomerRepository {

    public static final String LOG_TAG = "CustomerRepository";

    private CustomerDao customerDao;
    private static volatile CustomerRepository INSTANCE;

    public static CustomerRepository getRepository(Application application) {
        if (INSTANCE == null) {
            synchronized (CustomerRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CustomerRepository(application);
                }
            }
        }

        return INSTANCE;
    }

    public CustomerRepository(Context context) {
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

    //------------------------------------------------------------------------------

    public void insertCustomer(Customer customer) {
        CustomerBankingDatabase.execute(() -> customerDao.insertCustomer(customer));
    }

    public void deleteAllCustomer() {
        CustomerBankingDatabase.execute(() -> customerDao.deleteAll());
    }

    //------------------------------------------------------------------------------

    public void insertUserAccount(Customer customer, CustomerAddress customerAddress,
                                  CustomerCredentials newUserCredentials, Deposit deposit,
                                  TransactionList transactionList) {
        CustomerBankingDatabase.execute(() -> customerDao.insertUserAccount(customerDao.insertCustomer(customer), customerAddress, newUserCredentials, deposit, transactionList));
    }

    public LiveData<Long> getCustomerCredentialsIdByCustoimerId(long id){
        return customerDao.getCustomerCredentialsIdByCustomerId(id);
    }

    public void updateNewCustomerAccountPasswordByCustomerId(long id, String newPassword){
        customerDao.updateNewCustomerAccountPasswordByCustomerId(id, newPassword);
    }

    public LiveData<List<Customer>> getAllCustomerData() {
        return customerDao.getAllCustomersData();
    }

    public LiveData<String> getCustomerEmailByCustomerId(long id) {
        return customerDao.getCustomerEmailByCustomerId(id);
    }

    public LiveData<String> getCustomerAccountPasswordById(long id) {
        return customerDao.getCustomerAccountPasswordById(id);
    }

    public LiveData<Map<String, String>> getAllCustomerEmailAndPassword() {
        return customerDao.getAllCustomerEmailAndPassword();
    }

    public LiveData<Long> getCustomerIdByEmail(String email) {
        return customerDao.getCustomerIdByEmail(email);
    }

    public LiveData<String> getCustomerFullNameByCustomerId(long id){
        return customerDao.getCustomerFullNameByCustomerId(id);
    }

    public LiveData<String> getCustomerFirstNameByCustomerId(Long id) {
        return customerDao.getCustomerFirstNameByCustomerId(id);
    }

    public LiveData<String> getCustomerLastNameByCustomerId(Long id) {
        return customerDao.getCustomerLastNameByCustomerId(id);
    }

    public LiveData<Integer> getCustomerPhoneNumberByCustomerId(Long id) {
        return customerDao.getCustomerPhoneNumberByCustomerId(id);
    }

    //------------------------------------------------------------------------------

    public LiveData<String> getCustomerFullAddressById(Long id) {
        return customerDao.getCustomerFullAddressById(id);
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
}


