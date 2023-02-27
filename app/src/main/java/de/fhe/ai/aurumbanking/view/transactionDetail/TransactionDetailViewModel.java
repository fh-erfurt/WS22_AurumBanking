package de.fhe.ai.aurumbanking.view.transactionDetail;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.math.BigDecimal;

import de.fhe.ai.aurumbanking.storage.customer.CustomerRepository;
import de.fhe.ai.aurumbanking.storage.deposit.DepositRepository;

/**
 * View model class for the Transaction Detail View Fragment.
 */
public class TransactionDetailViewModel extends AndroidViewModel {

    private final CustomerRepository customerRepository;
    private final DepositRepository depositRepository;

    public TransactionDetailViewModel(@NonNull Application application) {
        super(application);
        this.customerRepository = CustomerRepository.getRepository(application);
        this.depositRepository = DepositRepository.getRepository(application);
    }


    public LiveData<String> getTransactionDateByTransactionListId(Long id) {
        return depositRepository.getTransactionDateByTransactionListId(id);
    }

    public LiveData<String> getBicTransactionListIdId(Long id) {
        return depositRepository.getBicTransactionListIdId(id);
    }

    public LiveData<String> getIbanByTransactionListIdId(Long id) {
        return depositRepository.getIbanByTransactionListIdId(id);
    }

    public LiveData<String> getBanknameByTransactionListIdId(Long id) {
        return depositRepository.getBanknameByTransactionListIdId(id);
    }

    public LiveData<String> getPurposeOfUseTransactionListIdId(Long id) {
        return depositRepository.getPurposeOfUseTransactionListIdId(id);
    }

    public LiveData<Boolean> getLatestOutputFlagByTransactionListId(Long id) {
        return depositRepository.getLatestOutputFlagByTransactionListId(id);
    }

    public LiveData<String> getLatestMoneyValueFromTransactionListByTransactionListId(Long id) {
        return depositRepository.getLatestMoneyValueFromTransactionListByTransactionListId(id);
    }


}
