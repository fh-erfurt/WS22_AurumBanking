package de.fhe.ai.aurumbanking.view.deposit;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import de.fhe.ai.aurumbanking.storage.customer.CustomerRepository;
import de.fhe.ai.aurumbanking.storage.deposit.DepositRepository;

public class DepositViewModel extends AndroidViewModel {

    private final CustomerRepository customerRepository;
    private final DepositRepository depositRepository;

    public DepositViewModel(@NonNull Application application) {
        super(application);
        this.customerRepository = CustomerRepository.getRepository(application);
        this.depositRepository = DepositRepository.getRepository(application);
    }

    public LiveData<Float> getCustomerDepositByCustomerId(Long id){
        return depositRepository.getCustomerDepositByCustomerId(id);
    }
}
