package de.fhe.ai.aurumbanking.storage.Customer

import androidx.room.*
import de.fhe.ai.aurumbanking.model.Customer

@Dao
interface CustomerDao {
    @Insert
    fun insert(customer: Customer)

    @Update
    fun update(vararg customer: Customer)

    @Delete
    fun delete(vararg customer: Customer)

    @Query("DELETE FROM Customer")
    fun deleteAll()

   // @Query("SELECT count(*) FROM Customer")
   // fun count(): Int
//
   // @Query("SELECT cus.id from Customer as Cus where lastname LIKE :search")
   // fun getCustomerIdByLastname(): List<Customer?>?
//
   // @Query("SELECT * from Customer as cus ORDER BY cus.lastname ASC")
   // fun getCustomerSortedByLastname(): List<Customer?>?

}