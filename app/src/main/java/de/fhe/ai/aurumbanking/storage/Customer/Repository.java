package de.fhe.ai.aurumbanking.storage.Customer;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import de.fhe.ai.aurumbanking.model.Customer;


public class Repository {

    public static final String LOG_TAG = "CustomerRepository";

    private CustomerDao customerDao;
    private static volatile Repository INSTANCE;

    public static Repository getRepository( Application application )
    {
        if( INSTANCE == null ) {
            synchronized ( Repository.class ) {
                if( INSTANCE == null ) {
                    INSTANCE = new Repository( application );
                }
            }
        }

        return INSTANCE;
    }

    public Repository(Context context ) {
        CustomerDatabase db = CustomerDatabase.getDatabase( context );
        this.customerDao = db.customerDao();
    }



    private List<Customer> query( Callable<List<Customer>> query )
    {
        try {
            return CustomerDatabase.query( query );
        }
        catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
    


}

