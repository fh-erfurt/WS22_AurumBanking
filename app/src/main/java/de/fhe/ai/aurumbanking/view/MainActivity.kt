package de.fhe.ai.aurumbanking.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import de.fhe.ai.aurumbanking.R
import de.fhe.ai.aurumbanking.view.LogInFragment.LogInFragment


class MainActivity : AppCompatActivity() {

    private var logInFragment: LogInFragment = LogInFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // val currentFragment = logInFragment.getInstance()
        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()
        transaction.replace(R.id.fragmentHolder, logInFragment, "Login_Tag")
        transaction.addToBackStack(null)
        transaction.commit()

    }

    override fun onResume(){
        super.onResume()
    }



}
