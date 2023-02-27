package de.fhe.ai.aurumbanking.view


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import de.fhe.ai.aurumbanking.R
import de.fhe.ai.aurumbanking.view.login.LogInFragment

/**
 * Class is used to switch from the "after launch-activity" to the login fragment
 *
 */
class LaunchActivity : AppCompatActivity() {

    private var logInFragment: LogInFragment = LogInFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()
        transaction.replace(R.id.fragmentHolder, logInFragment, "Login_Tag")
        transaction.addToBackStack(null)
        transaction.commit()
    }

    /**
     * When pressed the back-button the app will be close
     */
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
