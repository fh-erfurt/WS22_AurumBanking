package de.fhe.ai.aurumbanking.view.profil;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.Map;

import de.fhe.ai.aurumbanking.storage.Customer.Repository;

public class ProfileViewModel extends AndroidViewModel {

    private final Repository repository;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        this.repository = Repository.getRepository(application);
    }

    //public LiveData<String> getCustomerFullNameById(Long id){
    //
    //    LiveData<String> firstname = repository.getCustomerFirstNameByCustomerId(id);
    //    LiveData<String> midname = repository.getCustomerMidNameByCustomerId(id);
    //    LiveData<String> lastname = repository.getCustomerLastNameByCustomerId(id);
    //
    //
    //
    //    return firstname + midname + lastname ;
    //}


    public LiveData<String> getCustomerEmailByCustomerId( Long id) {
        return repository.getCustomerEmailByCustomerId(id);
    }

    public LiveData<String> getCustomerFirstNameByCustomerId(Long id){
        return this.repository.getCustomerFirstNameByCustomerId(id);
    }

    public LiveData<String> getCustomerMidNameByCustomerId(Long id) {
        return repository.getCustomerMidNameByCustomerId(id);
    }

    public LiveData<String> getCustomerLastNameByCustomerId(Long id) {
        return repository.getCustomerLastNameByCustomerId(id);
    }

    public LiveData<String> getStreetnameByCustomerId(Long id) {
        return repository.getStreetnameByCustomerId(id);
    }

    public LiveData<String> getHousenumberByCustomerId(Long id) {
        return repository.getHousenumberByCustomerId(id);
    }

    public LiveData<String> getCityByCustomerId(Long id) {
        return repository.getCityByCustomerId(id);
    }

    public LiveData<String> getZipCodebyCustomerId(Long id) {
        return repository.getZipCodebyCustomerId(id);
    }

    public LiveData<String> getCountryCustomerId(Long id) {
        return repository.getCountryCustomerId(id);
    }


    public LiveData<Integer> getCustomerPhoneNumberByCustomerId(Long id) {
        return repository.getCustomerPhoneNumberByCustomerId(id);
    }
}
