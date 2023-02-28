package de.fhe.ai.aurumbanking.storage.deposit;


import androidx.core.widget.ListViewAutoScrollHelper;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;

import java.math.BigDecimal;
import java.util.List;

import de.fhe.ai.aurumbanking.core.Converters;
import de.fhe.ai.aurumbanking.model.TransactionList;

/**
 * DepositDao layer contains database CRUD functions that is need to operated in the app functionalities.
 * For more architecture detail, please look into the architecture documentation.
 */
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

    @Query("Select `Tranaction Date` from TransactionList where transactionListId = :id")
    LiveData<String>getTransactionDateByTransactionListId(Long id);

    @Query("Select IBAN from TransactionList where transactionListId = :id")
    LiveData<String>getIbanByTransactionListIdId(Long id);

    @Query("Select Bankname from TransactionList where transactionListId = :id")
    LiveData<String>getBanknameByTransactionListIdId(Long id);

    @Query("Select BIC from TransactionList where transactionListId = :id")
    LiveData<String>getBicTransactionListIdId(Long id);

    @Query("Select `Purpose Of Use` from TransactionList where transactionListId = :id")
    LiveData<String>getPurposeOfUseTransactionListIdId(Long id);


    @Query("Select `Output Flag` from TransactionList tr  WHERE tr.transactionListId = :transactionListId ORDER BY `Output Flag` DESC LIMIT 1")
    LiveData<Boolean> getLatestOutputFlagByTransactionListId(Long transactionListId);

    @Query("Select `Money Value` from TransactionList tr  WHERE tr.transactionListId = :transactionListId ORDER BY `Money Value` DESC LIMIT 1")
    LiveData<String> getLatestMoneyValueFromTransactionListByTransactionListId(Long transactionListId);

}
