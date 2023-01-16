package de.fhe.ai.aurumbanking.storage.Customer

import androidx.lifecycle.LiveData
import androidx.room.*
import de.fhe.ai.aurumbanking.model.Customer
import de.fhe.ai.aurumbanking.model.CustomerAddress
import de.fhe.ai.aurumbanking.model.CustomerCredentials
import de.fhe.ai.aurumbanking.model.Deposit

@Dao
interface CustomerDao {

    fun insertUserAccount(
        customerId: Long,
        customerAddress: CustomerAddress,
        newUserCredentials: CustomerCredentials,
        deposit: Deposit
    ) {
        customerAddress.customerId = customerId
        newUserCredentials.customerId = customerId
        deposit.customerId = customerId
        this.insertAddress(customerAddress)
        this.insertUserCredentials(newUserCredentials)
        this.insertDeposit(deposit)
    }

    @Insert
    fun insertCustomer(customer: Customer): Long

    @Insert
    fun insertAddress(customerAddress: CustomerAddress): Long

    @Insert
    fun insertUserCredentials(userCredentials: CustomerCredentials): Long

    @Insert
    fun insertDeposit(deposit: Deposit): Long


    @Update
    fun updateCustomerValues(vararg customer: Customer)

    @Delete
    fun delete(vararg customer: Customer)

    @Query("DELETE FROM Customer")
    fun deleteAll()

    @Query("SELECT c.customerId from Customer c where c.`Customer Email` = :customerEmail")
    fun getCustomerIdByEmail(customerEmail: String) : LiveData<Long>

    @Query("SELECT * from Customer")
    fun getAllCustomersData(): LiveData<List<Customer>>

    @Query("SELECT c.`Customer Email` from Customer c")
    fun getAllCustomerEmail(): LiveData<List<String>>

    @MapInfo(keyColumn = "customerEmail", valueColumn = "customerPassword")
    @Query("SELECT  c.`Customer Email` as customerEmail  , cc.`User Password` as customerPassword  from CustomerCredentials cc INNER JOIN customer c ON c.customerId = cc.customerId")
    fun getAllCustomerEmailAndPassword(): LiveData<Map<String, String>>

    @Query("SELECT c.customerId  FROM Customer c where c.`Customer Email` = :customerEmail  AND c.`Customer Phonenumber` = :customerPhonenumber")
    fun getCustomerIdByEmailndTelefonnumer(customerEmail : String, customerPhonenumber: Int ): Long

    @Transaction
    @Query("SELECT c.`Customer Email` from Customer c where c.customerId = :customerId")
    fun getCustomerEmailByCustomerId(customerId: Long): LiveData<String>

    @Query("SELECT c.Firstname  from Customer c where c.customerId = :customerId")
    fun getCustomerFirstNameByCustomerId(customerId: Long): String

    @Query("SELECT c.Lastname  from Customer c where c.customerId = :customerId")
    fun getCustomerLastNameByCustomerId(customerId: Long): String

    @Query("SELECT c.Midname  from Customer c where c.customerId = :customerId")
    fun getCustomerMidNameByCustomerId(customerId: Long): String

    @Query("SELECT c.`Customer Phonenumber` from Customer c where c.customerId = :customerId")
    fun getCustomerPhoneNumberByCustomerId(customerId: Long): Int

    @Query("SELECT ca.`Street Name` from Customer c INNER JOIN CustomerAddress ca ON ca.customerId = c.customerId where c.customerId = :customerId")
    fun getStreetnameByCustomerId(customerId: Long): String

    @Query("SELECT ca.Housenumber from Customer c INNER JOIN CustomerAddress ca ON ca.customerId = c.customerId where c.customerId = :customerId")
    fun getHousenumberByCustomerId(customerId: Long): String

    @Query("SELECT ca.City from Customer c INNER JOIN CustomerAddress ca ON ca.customerId = c.customerId where c.customerId = :customerId")
    fun getCityByCustomerId(customerId: Long): String

    @Query("SELECT ca.ZipCode from Customer c INNER JOIN CustomerAddress ca ON ca.customerId = c.customerId where c.customerId = :customerId")
    fun getZipCodebyCustomerId(customerId: Long): String

    @Query("SELECT ca.Country from Customer c INNER JOIN CustomerAddress ca ON ca.customerId = c.customerId where c.customerId = :customerId")
    fun getCountryCustomerId(customerId: Long): String

    @Query("SELECT ca.`Customer Address Id` from Customer c INNER JOIN CustomerAddress ca ON ca.customerId = c.customerId where c.customerId = :customerId")
    fun getCustomerAddressIdByCustomerId(customerId: Long): Long

    @Query("SELECT cc.`User Password` from Customer c INNER JOIN CustomerCredentials cc  ON cc.customerId = c.customerId where c.customerId = :customerId ")
    fun getCustomerCredentialsByCustomerId(customerId: Long) : String

    @Query("SELECT cc.`Customer Credentials Id` from Customer c INNER JOIN CustomerCredentials cc  ON cc.customerId = c.customerId where c.customerId = :customerId ")
    fun getCustomerCredentialsIdByCustomerId(customerId: Long) : Long

    @Query("SELECT p.`Current Depostit Value` from Customer c INNER JOIN Deposit p  ON p.customerId = c.customerId where c.customerId  = :customerId ")
    fun getCustomerDepostitByCustomerId(customerId: Long) : Float


}