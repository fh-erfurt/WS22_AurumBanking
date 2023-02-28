package de.fhe.ai.aurumbanking.view.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.Map;

import de.fhe.ai.aurumbanking.storage.customer.CustomerRepository;

/**
 * Class is used as the View Model of the Login Fragment
 */
public class LogInFragmentViewModel extends AndroidViewModel {

    private final CustomerRepository customerRepository;

    public LogInFragmentViewModel(@NonNull Application application) {
        super(application);
        this.customerRepository = CustomerRepository.getRepository(application);
    }

    public LiveData<String> getCustomerEmailById(Long id){
        return this.customerRepository.getCustomerEmailByCustomerId(id);
    }

    public LiveData<Map<String, String>> getMapOfCustomerEmailAndPassword(){
        return this.customerRepository.getAllCustomerEmailAndPassword();
    }

    public LiveData<Long> getCustomerIdByEmail(String email) {
        return customerRepository.getCustomerIdByEmail(email);
    }




}
