package de.fhe.ai.aurumbanking.storage.deposit;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import java.math.BigDecimal;
import java.util.List;

import de.fhe.ai.aurumbanking.model.TransactionList;
import de.fhe.ai.aurumbanking.storage.core.CustomerBankingDatabase;

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

    public LiveData<BigDecimal> getCustomerDepositByCustomerId(Long id){
        return depositDao.getCustomerDepositByCustomerId(id);
    }

    public LiveData<Boolean> getLatestOutputFlagByCustomerId(Long id){
        return depositDao.getLatestOutputFlagByCustomerId(id);
    }


    public Long insertNewTransactionListElementByCustomerId(TransactionList transactionList){
        return depositDao.insertNewTransactionListElementByCustomerId(transactionList);
    }


    public LiveData<String> getLatestMoneyValueFromTransactionListByCustomerId(Long id){
        return depositDao.getLatestMoneyValueFromTransactionListByCustomerId(id);
    }

    public void updateCustomerDeposit(Long id, BigDecimal newDeposit){
        depositDao.updateCustomerDepositByCustomerId(id, newDeposit);
    }

    public LiveData<List<TransactionList>>getAllTransactionListElementByCustomerId(Long id){
        return depositDao.getAllTransactionListElementByCustomerId(id);
    }
}
