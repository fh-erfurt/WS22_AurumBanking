package de.fhe.ai.aurumbanking.view.moneytransfer;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.math.BigDecimal;
import java.util.Date;

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

    public Long insertNewTransactionListElementByCustomerId(Long customerId, Long depositId , Date date, String beneficiary, String iban, String bankName,
                                                            BigDecimal moneyValue, String purposeOfUse){

        TransactionList transactionList = new TransactionList();
        transactionList.setOutputFlag(true);
        transactionList.setCustomerId(customerId);
        transactionList.setDepositId(depositId);
        transactionList.setTransactionDate(date);
        transactionList.setBeneficiary(beneficiary);
        transactionList.setIban(iban);
        transactionList.setBankname(bankName);
        transactionList.setMoneyValue(moneyValue);
        transactionList.setPurposeOfUse(purposeOfUse);

        return depositRepository.insertNewTransactionListElementByCustomerId(transactionList);
    }

}
