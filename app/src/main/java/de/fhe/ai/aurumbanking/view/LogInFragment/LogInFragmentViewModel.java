package de.fhe.ai.aurumbanking.view.LogInFragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import de.fhe.ai.aurumbanking.storage.Customer.Repository;

public class LogInFragmentViewModel extends AndroidViewModel {

    private final Repository repository;

    public LogInFragmentViewModel(@NonNull Application application) {
        super(application);
        this.repository = Repository.getRepository(application);
    }

    public LiveData<String> getCustomerEmailById(Long id){
        return this.repository.getCustomerEmailByCustomerId(id);
    }
}
