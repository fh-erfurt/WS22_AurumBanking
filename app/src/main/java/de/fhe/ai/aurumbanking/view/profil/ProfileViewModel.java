package de.fhe.ai.aurumbanking.view.profil;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import de.fhe.ai.aurumbanking.storage.customer.CustomerRepository;

public class ProfileViewModel extends AndroidViewModel {

    private final CustomerRepository customerRepository;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        this.customerRepository = CustomerRepository.getRepository(application);
    }

    public LiveData<String> getCustomerEmailByCustomerId( Long id) {
        return customerRepository.getCustomerEmailByCustomerId(id);
    }


    public LiveData<String> getCustomerAccountPasswordById( Long id) {
        return customerRepository.getCustomerAccountPasswordById(id);
    }

    public LiveData<String> getCustomerFullNameByCustomerId(Long id){
        return customerRepository.getCustomerFullNameByCustomerId(id);
    }

    public LiveData<String> getCustomerFirstNameByCustomerId(Long id){
        return this.customerRepository.getCustomerFirstNameByCustomerId(id);
    }

    public LiveData<String> getCustomerLastNameByCustomerId(Long id) {
        return customerRepository.getCustomerLastNameByCustomerId(id);
    }


    public LiveData<String> getCustomerFullAddressById(Long id) {
        return customerRepository.getCustomerFullAddressById(id);
    }

    public LiveData<String> getStreetnameByCustomerId(Long id) {
        return customerRepository.getStreetnameByCustomerId(id);
    }

    public LiveData<String> getHousenumberByCustomerId(Long id) {
        return customerRepository.getHousenumberByCustomerId(id);
    }

    public LiveData<String> getCityByCustomerId(Long id) {
        return customerRepository.getCityByCustomerId(id);
    }

    public LiveData<String> getZipCodebyCustomerId(Long id) {
        return customerRepository.getZipCodebyCustomerId(id);
    }

    public LiveData<String> getCountryCustomerId(Long id) {
        return customerRepository.getCountryCustomerId(id);
    }


    public LiveData<Integer> getCustomerPhoneNumberByCustomerId(Long id) {
        return customerRepository.getCustomerPhoneNumberByCustomerId(id);
    }

    public LiveData<Long> getCustomerCredentialsIdByCustoimerId(Long id){
        return customerRepository.getCustomerCredentialsIdByCustoimerId(id);
    }

    public void updateNewCustomerAccountPasswordByCustomerId(Long id, String newPassword){

        customerRepository.updateNewCustomerAccountPasswordByCustomerId(id, newPassword);
    }
}
