package de.fhe.ai.aurumbanking.view.moneytransfer;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.math.BigDecimal;
import java.util.Date;

import de.fhe.ai.aurumbanking.model.OrderOutput;
import de.fhe.ai.aurumbanking.model.TransactionList;
import de.fhe.ai.aurumbanking.storage.customer.CustomerRepository;
import de.fhe.ai.aurumbanking.storage.deposit.DepositRepository;

public class MoneyTransferViewModel extends AndroidViewModel {

    private final CustomerRepository customerRepository;
    private final DepositRepository depositRepository;

    public MoneyTransferViewModel(@NonNull Application application) {
        super(application);
        this.customerRepository = CustomerRepository.getRepository(application);
        this.depositRepository = DepositRepository.getRepository(application);
    }

    public Long insertNewTransactionListFlagByCustomerId(TransactionList transactionList){
        return depositRepository.insertNewTransactionListFlagByCustomerId(transactionList);
    }

    private void insertNewOutputTransactionByTransactionListId(Long id, OrderOutput orderOutput){
        depositRepository.insertNewOutputTransactionByTransactionListId(orderOutput);
    }

    public void prepareNewOutputTransactionByTransactionListId(Date date, String beneficiary, String iBAN, String destinationBankname,
                                                               BigDecimal moneyValue, String purposeOfUse , Long transactionListId ){

    }
}
