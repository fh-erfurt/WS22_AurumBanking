package de.fhe.ai.aurumbanking.storage.deposit;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import de.fhe.ai.aurumbanking.model.OrderInput;
import de.fhe.ai.aurumbanking.model.OrderOutput;
import de.fhe.ai.aurumbanking.model.TransactionList;

@Dao
public interface DepositDao {

    @Query("Select  tr.transactionListId from TransactionList tr INNER JOIN Customer c ON tr.customerId = c.customerId WHERE c.customerId = :customerId ORDER BY transactionListId DESC LIMIT 1;")
    LiveData<Long> getLatestTransactionListIdByCustomerId(Long customerId);

    @Query("SELECT p.`Current Depostit Value` from Customer c INNER JOIN Deposit p  ON p.customerId = c.customerId where c.customerId  = :customerId ")
    LiveData<Float> getCustomerDepositByCustomerId(Long customerId);

    @Query("SELECT p.depositId from Customer c INNER JOIN Deposit p  ON p.customerId = c.customerId where c.customerId  = :customerId ")
    LiveData<Long> getDepositIdByCustomerId(Long customerId);

    @Query("Select  `Deduction Flag` from TransactionList tr INNER JOIN Customer c ON tr.customerId = c.customerId WHERE c.customerId = :customerId ORDER BY `Deduction Flag` DESC LIMIT 1;")
    LiveData<Boolean> getLatestDeductionFlagByCustomerId(Long customerId);

    @Query("Select oi.`Shipper Name`||','||oi.`Money Value` from OrderInput oi \n" +
            "INNER JOIN TransactionList tr ON oi.transactionListId = tr.transactionListId  \n" +
            "INNER JOIN Customer c ON tr.customerId = c.customerId\n" +
            "WHERE c.customerId = :customerId ORDER BY oi.`Money Value` DESC LIMIT 1\n")
    LiveData<String> getLatestMoneyValueFromOrderInputByCustomerId(Long customerId);


}
