package de.fhe.ai.aurumbanking.storage.deposit;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;

import java.math.BigDecimal;
import java.util.List;

import de.fhe.ai.aurumbanking.core.Converters;
import de.fhe.ai.aurumbanking.model.TransactionList;

@TypeConverters(Converters.class)
@Dao
public interface DepositDao {

    @Query("Select  tr.transactionListId from TransactionList tr INNER JOIN Customer c ON tr.customerId = c.customerId WHERE c.customerId = :customerId ORDER BY transactionListId DESC LIMIT 1;")
    LiveData<Long> getLatestTransactionListIdByCustomerId(Long customerId);


    @Query("SELECT p.`Current Depostit Value` from Customer c INNER JOIN Deposit p  ON p.customerId = c.customerId where c.customerId  = :customerId ")
    LiveData<BigDecimal> getCustomerDepositByCustomerId(Long customerId);

    @Query("SELECT p.depositId from Customer c INNER JOIN Deposit p  ON p.customerId = c.customerId where c.customerId  = :customerId ")
    LiveData<Long> getDepositIdByCustomerId(Long customerId);

    @Query("Select `Output Flag` from TransactionList tr INNER JOIN Customer c ON tr.customerId = c.customerId WHERE c.customerId = :customerId ORDER BY `Output Flag` DESC LIMIT 1")
    LiveData<Boolean> getLatestOutputFlagByCustomerId(Long customerId);

    @Insert
    Long insertNewTransactionListElementByCustomerId(TransactionList transactionList);


    @Query("Select tr.Beneficiary||','||tr.`Money Value` from TransactionList tr  INNER Join customer c ON tr.customerId = c.customerId WHERE c.customerId = :customerId ORDER BY tr.transactionListId DESC LIMIT 1")
    LiveData<String> getLatestMoneyValueFromTransactionListByCustomerId(Long customerId);


    @Query("Update Deposit SET `Current Depostit Value` = :newDepositValue  where customerId = :id" )
    void updateCustomerDepositByCustomerId( Long id, BigDecimal newDepositValue);

    @Query("Select * from TransactionList where customerId = :id")
    LiveData<List<TransactionList>>getAllTransactionListElementByCustomerId(Long id);

    @Query("Select * from TransactionList WHERE `Money Value` LIKE '%' || :searchTerm || '%' OR `Output Flag` LIKE '%' || :searchTerm || " +
            "'%' OR `Purpose Of Use` LIKE '%' || :searchTerm || '%' OR Bankname LIKE '%' || :searchTerm || '%' OR IBAN LIKE '%' || :searchTerm || " +
            "'%' OR `Tranaction Date` LIKE '%' || :searchTerm || '%' OR IBAN LIKE '%' || :searchTerm || '%' OR BIC LIKE '%' || :searchTerm || '%' ")
    LiveData<List<TransactionList>>getTransactionListBySearchTerm(String searchTerm);
}
