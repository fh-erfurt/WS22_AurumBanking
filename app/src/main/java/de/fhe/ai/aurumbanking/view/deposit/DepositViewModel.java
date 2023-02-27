package de.fhe.ai.aurumbanking.view.deposit;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.math.BigDecimal;
import java.util.List;

import de.fhe.ai.aurumbanking.core.Helper;
import de.fhe.ai.aurumbanking.model.TransactionList;
import de.fhe.ai.aurumbanking.storage.customer.CustomerRepository;
import de.fhe.ai.aurumbanking.storage.deposit.DepositRepository;

/**
 * ViewModel class for the Deposit View.
 * Contains all needed function for process and display the data into the Deposit View
 */
public class DepositViewModel extends AndroidViewModel {

    private final CustomerRepository customerRepository;
    private final DepositRepository depositRepository;
    private final LiveData<List<TransactionList>> transactionList;
    private final MutableLiveData<String> searchTerm = new MutableLiveData<>("");
    private Helper helper = Helper.getHelperInstance();
    private Long customerId;

    public DepositViewModel(@NonNull Application application ) {
        super(application);
        this.customerRepository = CustomerRepository.getRepository(application);
        this.depositRepository = DepositRepository.getRepository(application);
        this.customerId = helper.getCustomerId(application);

        /**
         * check if input in the searchbar is set to return all the transaction in the transaction list on only the used once
         */
        this.transactionList = Transformations.switchMap(this.searchTerm, input -> {
            if(input.isEmpty()){
                return getAllTransactionListElementByCustomerId(this.customerId);
            }else{
                return getTransactionListBySearchTerm(input);
            }
        });
    }

    public LiveData<BigDecimal> getCustomerDepositByCustomerId(Long id){
        return depositRepository.getCustomerDepositByCustomerId(id);
    }

    public LiveData<List<TransactionList>> getAllTransactionListElementByCustomerId(Long id){
        return depositRepository.getAllTransactionListElementByCustomerId(id);
    }


    public LiveData<List<TransactionList>> getTransactionListBySearchTerm(String searchTerm){
        return depositRepository.getTransactionListBySearchTerm(searchTerm);
    }

    public LiveData<List<TransactionList>> getTransactionList(){
        return this.transactionList;
    }

    public void setSearchTerm(String searchTerm){
        this.searchTerm.setValue(searchTerm);
    }
}
