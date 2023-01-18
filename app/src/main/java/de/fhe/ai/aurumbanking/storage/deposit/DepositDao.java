package de.fhe.ai.aurumbanking.storage.deposit;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface DepositDao {

    @Query("SELECT p.`Current Depostit Value` from Customer c INNER JOIN Deposit p  ON p.customerId = c.customerId where c.customerId  = :customerId ")
    LiveData<Float> getCustomerDepositByCustomerId(Long customerId);

    @Query("SELECT p.depositId from Customer c INNER JOIN Deposit p  ON p.customerId = c.customerId where c.customerId  = :customerId ")
    LiveData<Long> getDepositIdByCustomerId(Long customerId);
}
