package de.fhe.ai.aurumbanking.storage.customer

import androidx.lifecycle.LiveData
import androidx.room.*
import de.fhe.ai.aurumbanking.core.Converters
import de.fhe.ai.aurumbanking.model.*

@TypeConverters(Converters::class)
@Dao
interface CustomerDao {

    fun insertUserAccount(
        customerId: Long,
        customerAddress: CustomerAddress,
        newUserCredentials: CustomerCredentials,
        deposit: Deposit,
        transactionList: TransactionList,
        orderInput: OrderInput
    ) {
        customerAddress.customerId = customerId
        newUserCredentials.customerId = customerId
        deposit.customerId = customerId
        this.insertAddress(customerAddress)
        this.insertUserCredentials(newUserCredentials)
        val depositId = this.insertDeposit(deposit)
        transactionList.depositId = depositId
        transactionList.customerId = customerId
        val transactionListId = this.insertTransactionList(transactionList)
        orderInput.transactionListId = transactionListId
        this.insertOrderInput(orderInput)
    }

    @Insert
    fun insertCustomer(customer: Customer): Long

    @Insert
    fun insertAddress(customerAddress: CustomerAddress): Long

    @Insert
    fun insertUserCredentials(userCredentials: CustomerCredentials): Long

    @Insert
    fun insertDeposit(deposit: Deposit): Long

    @Insert
    fun insertTransactionList(transactionList: TransactionList?): Long?

    @Insert
    fun insertOrderInput(orderInput: OrderInput?): Long?

    @Insert
    fun insertOrderOutput(orderOutput: OrderOutput?): Long?

    @Query("Update CustomerCredentials SET `User Password` = :newPassword  where customerId = :id" )
    fun updateNewCustomerAccountPasswordByCustomerId(id: Long, newPassword: String)


    @Update
    fun updateCustomerValues(vararg customer: Customer)

    @Delete
    fun delete(vararg customer: Customer)

    @Query("DELETE FROM Customer")
    fun deleteAll()

    //-----------------------------------------------------------------------------------------------------------------------------------------------

    @Query("SELECT cc.`Customer Credentials Id` from Customer c INNER JOIN CustomerCredentials cc  ON cc.customerId = c.customerId where c.customerId = :customerId ")
    fun getCustomerCredentialsIdByCustomerId(customerId: Long) : LiveData<Long>

    @Query("SELECT c.customerId from Customer c where c.`Customer Email` = :customerEmail")
    fun getCustomerIdByEmail(customerEmail: String) : LiveData<Long>

    @Query("SELECT cc.`User Password` from Customer c INNER JOIN CustomerCredentials cc  ON cc.customerId = c.customerId where c.customerId = :customerId ")
    fun getCustomerAccountPasswordById(customerId: Long) : LiveData<String>

    @MapInfo(keyColumn = "customerEmail", valueColumn = "customerPassword")
    @Query("SELECT  c.`Customer Email` as customerEmail  , cc.`User Password` as customerPassword  from CustomerCredentials cc INNER JOIN customer c ON c.customerId = cc.customerId")
    fun getAllCustomerEmailAndPassword(): LiveData<Map<String, String>>

    //-----------------------------------------------------------------------------------------------------------------------------------------------


    @Query("SELECT * from Customer")
    fun getAllCustomersData(): LiveData<List<Customer>>

    @Query("SELECT c.`Customer Email` from Customer c")
    fun getAllCustomerEmail(): LiveData<List<String>>

    @Query("SELECT c.customerId  FROM Customer c where c.`Customer Email` = :customerEmail  AND c.`Customer Phonenumber` = :customerPhonenumber")
    fun getCustomerIdByEmailndTelefonnumer(customerEmail : String, customerPhonenumber: Int ): LiveData<Long>

    @Transaction
    @Query("SELECT c.`Customer Email` from Customer c where c.customerId = :customerId")
    fun getCustomerEmailByCustomerId(customerId: Long): LiveData<String>

    @Query("SELECT c.Firstname || ' ' || c.Lastname from Customer c where c.customerId = :customerId")
    fun getCustomerFullNameByCustomerId(customerId: Long): LiveData<String>

    @Query("SELECT c.Firstname  from Customer c where c.customerId = :customerId")
    fun getCustomerFirstNameByCustomerId(customerId: Long): LiveData<String>

    @Query("SELECT c.Lastname from Customer c where c.customerId = :customerId")
    fun getCustomerLastNameByCustomerId(customerId: Long): LiveData<String>

    @Query("SELECT c.`Customer Phonenumber` from Customer c where c.customerId = :customerId")
    fun getCustomerPhoneNumberByCustomerId(customerId: Long): LiveData<Int>

    //-----------------------------------------------------------------------------------------------------------------------------------------------


    @Query("SELECT ca.`Street Name` || ' ' || ca.Housenumber  || ',' || ca.City || ' ' || ca.ZipCode || ',' || ca.Country AS \"Customer Address\"  from Customer c INNER JOIN CustomerAddress ca ON ca.customerId = c.customerId where c.customerId = :customerId")
    fun getCustomerFullAddressById(customerId: Long) : LiveData<String>

    @Query("SELECT ca.`Street Name` from Customer c INNER JOIN CustomerAddress ca ON ca.customerId = c.customerId where c.customerId = :customerId")
    fun getStreetnameByCustomerId(customerId: Long): LiveData<String>

    @Query("SELECT ca.Housenumber from Customer c INNER JOIN CustomerAddress ca ON ca.customerId = c.customerId where c.customerId = :customerId")
    fun getHousenumberByCustomerId(customerId: Long): LiveData<String>

    @Query("SELECT ca.City from Customer c INNER JOIN CustomerAddress ca ON ca.customerId = c.customerId where c.customerId = :customerId")
    fun getCityByCustomerId(customerId: Long): LiveData<String>

    @Query("SELECT ca.ZipCode from Customer c INNER JOIN CustomerAddress ca ON ca.customerId = c.customerId where c.customerId = :customerId")
    fun getZipCodebyCustomerId(customerId: Long): LiveData<String>

    @Query("SELECT ca.Country from Customer c INNER JOIN CustomerAddress ca ON ca.customerId = c.customerId where c.customerId = :customerId")
    fun getCountryCustomerId(customerId: Long): LiveData<String>

    @Query("SELECT ca.`Customer Address Id` from Customer c INNER JOIN CustomerAddress ca ON ca.customerId = c.customerId where c.customerId = :customerId")
    fun getCustomerAddressIdByCustomerId(customerId: Long): LiveData<Long>

    //-----------------------------------------------------------------------------------------------------------------------------------------------




}