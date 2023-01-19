package de.fhe.ai.aurumbanking.view.overview;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import de.fhe.ai.aurumbanking.storage.customer.CustomerRepository;
import de.fhe.ai.aurumbanking.storage.deposit.DepositRepository;

public class OverviewViewModel extends AndroidViewModel {

    private final CustomerRepository customerRepository;
    private final DepositRepository depositRepository;



    public OverviewViewModel(@NonNull Application application) {
        super(application);
        this.customerRepository = CustomerRepository.getRepository(application);
        this.depositRepository = DepositRepository.getRepository(application);
    }

    public LiveData<String> getCustomerFullNameByCustomerId(Long id){
        return customerRepository.getCustomerFullNameByCustomerId(id);

    }

    public LiveData<Float> getCustomerDepositByCustomerId(Long id){
        return  depositRepository.getCustomerDepositByCustomerId(id);
    }

    public LiveData<Boolean> getLatestDeductionFlagByCustomerId(Long id){
        return depositRepository.getLatestDeductionFlagByCustomerId(id);
    }

    public LiveData<String> getLatestMoneyValueFromOrderInputByCustomerId(Long id){
        return depositRepository.getLatestMoneyValueFromOrderInputByCustomerId(id);
    }

}

