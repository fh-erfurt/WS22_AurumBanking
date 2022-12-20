package de.fhe.ai.aurumbanking.core

import android.app.Application
import android.util.Log
import androidx.core.content.PackageManagerCompat.LOG_TAG
import de.fhe.ai.aurumbanking.storage.Customer.Repository

class MyApplication : Application() {

    private var LOG_TAG = "Aurum Banking App - MyApplication Class:"

    override fun onCreate() {
        super.onCreate()
        testDatabase()
        Log.i(this.LOG_TAG, "On Create finished.")
    }

    fun testDatabase(){

        Log.i(this.LOG_TAG, "Repo Init.")
        var repo = Repository(this)



    }
}