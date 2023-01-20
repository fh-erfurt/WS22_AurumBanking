package de.fhe.ai.aurumbanking.view.support;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import de.fhe.ai.aurumbanking.storage.customer.CustomerRepository;

public class SupportViewModel extends AndroidViewModel {

    private final CustomerRepository customerRepository;

    public SupportViewModel(@NonNull Application application) {
        super(application);
        this.customerRepository = CustomerRepository.getRepository(application);
    }

    public LiveData<String> getCustomerFullNameByCustomerId(Long id){
        return customerRepository.getCustomerFullNameByCustomerId(id);
    }
}
