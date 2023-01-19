package de.fhe.ai.aurumbanking.storage.deposit;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import de.fhe.ai.aurumbanking.model.Customer;
import de.fhe.ai.aurumbanking.model.CustomerAddress;
import de.fhe.ai.aurumbanking.model.CustomerCredentials;
import de.fhe.ai.aurumbanking.model.Deposit;
import de.fhe.ai.aurumbanking.storage.core.CustomerBankingDatabase;
import de.fhe.ai.aurumbanking.storage.customer.CustomerDao;
import de.fhe.ai.aurumbanking.storage.customer.CustomerRepository;

public class DepositRepository {

    public static final String LOG_TAG = "DepositRepository";

    private DepositDao depositDao;
    private static volatile DepositRepository INSTANCE;

    public static DepositRepository getRepository(Application application) {
        if (INSTANCE == null) {
            synchronized (DepositRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DepositRepository(application);
                }
            }
        }
        return INSTANCE;
    }

    public DepositRepository(Context context) {
        CustomerBankingDatabase db = CustomerBankingDatabase.getDatabase(context);
        this.depositDao = db.depositDao();
    }

    public LiveData<Float> getCustomerDepositByCustomerId(Long id){
        return depositDao.getCustomerDepositByCustomerId(id);
    }

    public LiveData<Boolean> getLatestDeductionFlagByCustomerId(Long id){
        return depositDao.getLatestDeductionFlagByCustomerId(id);
    }

    public LiveData<String> getLatestMoneyValueFromOrderInputByCustomerId(Long id){
        return depositDao.getLatestMoneyValueFromOrderInputByCustomerId(id);
    }

}
