package de.fhe.ai.aurumbanking.storage.deposit;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import java.math.BigDecimal;
import java.util.List;

import de.fhe.ai.aurumbanking.model.TransactionList;
import de.fhe.ai.aurumbanking.storage.core.CustomerBankingDatabase;

/**
 *
 * Singleton deposit repository is used as a abstraction layer between viewmodel-layer an dao-layer.
 * Repository functions are used in the viewmodel-layer of the app.
 * For more architecture detail, please look into the architecture documentation.
 *
 */
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

    public LiveData<List<TransactionList>>getTransactionListBySearchTerm(String searchTerm){
        return depositDao.getTransactionListBySearchTerm(searchTerm);
    }

    public LiveData<String> getTransactionDateByTransactionListId(Long id){
        return depositDao.getTransactionDateByTransactionListId(id);
    }


    public LiveData<String> getBicTransactionListIdId(Long id){
        return depositDao.getBicTransactionListIdId(id);
    }

    public LiveData<String> getIbanByTransactionListIdId(Long id){
        return depositDao.getIbanByTransactionListIdId(id);
    }

    public LiveData<String> getBanknameByTransactionListIdId(Long id){
        return depositDao.getBanknameByTransactionListIdId(id);
    }

    public LiveData<String> getPurposeOfUseTransactionListIdId(Long id){
        return depositDao.getPurposeOfUseTransactionListIdId(id);
    }

    public LiveData<Boolean> getLatestOutputFlagByTransactionListId(Long id){
        return depositDao.getLatestOutputFlagByTransactionListId(id);
    }

    public LiveData<String> getLatestMoneyValueFromTransactionListByTransactionListId(Long id){
        return depositDao.getLatestMoneyValueFromTransactionListByTransactionListId(id);
    }



}
