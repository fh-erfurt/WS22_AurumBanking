package de.fhe.ai.aurumbanking.storage.Customer;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.fhe.ai.aurumbanking.model.Customer;
import de.fhe.ai.aurumbanking.model.CustomerAddress;
import de.fhe.ai.aurumbanking.model.CustomerCredentials;
import de.fhe.ai.aurumbanking.model.Deposit;
import de.fhe.ai.aurumbanking.model.OrderInput;
import de.fhe.ai.aurumbanking.model.OrderOutput;
import de.fhe.ai.aurumbanking.model.TransactionList;

/*
    Class only there to init Database with all the entities
    Database class instance should be handled as Singleton
 */
@Database( entities = {Customer.class, CustomerAddress.class, CustomerCredentials.class, Deposit.class, OrderInput.class, OrderOutput.class, TransactionList.class}, version = 1)

public abstract class CustomerBankingDatabase extends RoomDatabase {

    private static final String LOG_TAG = "CustomerBakingDatabase";

    /*
        Contact DAO reference, will be filled by Android
     */
    public abstract CustomerDao customerDao();


    /*
      Executor service to perform database operations asynchronous and independent from UI thread
     */
    private static final int NUMBER_OF_THREADS = 4;
    private static final ExecutorService databaseExecutor =
            Executors.newFixedThreadPool( NUMBER_OF_THREADS );

    /*
        Singleton Instance
     */
    private static volatile CustomerBankingDatabase INSTANCE;

    /*
        Helper methods to ease external usage of ExecutorService
        e.g. perform async database operations
     */
    public static <T> T query(Callable<T> task)
            throws ExecutionException, InterruptedException
    {
        return databaseExecutor.invokeAny(Collections.singletonList( task ) );
    }

    public static void execute( Runnable runnable )
    {
        databaseExecutor.execute( runnable );
    }

    /*
        Singleton 'getInstance' method to create database instance thereby opening and, if not
        already done, init the database. Note the 'createCallback'.
     */
    static CustomerBankingDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CustomerBankingDatabase.class) {
                if (INSTANCE == null) {
                            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    CustomerBankingDatabase.class, "customer_db")
                            .addCallback(createCallback) // See below
                            .build();
                    Log.i( LOG_TAG, "CustomerBankingDatabase was initialized. ");
                }
            }
        }
        return INSTANCE;
    }

    /*
        Create DB Callback
        Used to add some initial data
     */
    private static final Callback createCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            Log.i( LOG_TAG, "Calling createCallback-methode!" );

            /*
            execute(() -> {
                ContactDao dao = CUSTOMERDATBASE.contactDao();

                Faker faker = Faker.instance();
                for (int i = 0; i < 10; i++)
                {
                    Contact contact = new Contact(faker.name().lastName(), faker.name().firstName());
                    contact.setCreated( System.currentTimeMillis() );
                    contact.setModified( contact.getCreated() );
                    contact.setVersion( 1 );
                    dao.insert(contact);
                }
                Log.i(LOG_TAG, "Inserted 10 values to DB");
            });

             */
        }
    };
}
