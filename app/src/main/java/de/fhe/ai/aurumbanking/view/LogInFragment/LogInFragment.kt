package de.fhe.ai.aurumbanking.view.LogInFragment

import android.app.Activity
import android.app.Application
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import de.fhe.ai.aurumbanking.R
import de.fhe.ai.aurumbanking.core.CustomerIdStore
import de.fhe.ai.aurumbanking.core.MyApplication
import de.fhe.ai.aurumbanking.view.MainActivity
import de.fhe.ai.aurumbanking.view.StartActivity
import java.util.regex.Pattern


class LogInFragment: Fragment() {

    private lateinit var viewModel : LogInFragmentViewModel
    private var password: String = ""
    private var email: String = ""
    private val STORE_KEY_COUNTER = "CustomerId"




    private val EMAIL_ADDRESS_PATTERN = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)

        val root = inflater.inflate(R.layout.fragment_login, container, false)
        val login_Button = root.findViewById<Button>(R.id.login)

        this.viewModel = ViewModelProvider(this)[LogInFragmentViewModel::class.java]


        login_Button.setOnClickListener() {
            this.email = root.findViewById<TextView?>(R.id.UserEmailAddress).text.toString()
            this.password = root.findViewById<TextView?>(R.id.UserPassword).text.toString()

            if (validateEmail()){

                //TODO: need an Oberserver, to get the Data from Live Data
                val emailLiveData = this.viewModel.mapOfCustomerEmailAndPassword
                emailLiveData.observe(requireActivity(), this::checkLogin)
            }
        }

        (root.findViewById<TextView>(R.id.UserPassword)).setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                this.email = root.findViewById<TextView?>(R.id.UserEmailAddress).text.toString()
                this.password = root.findViewById<TextView?>(R.id.UserPassword).text.toString()

                val emailLiveData = this.viewModel.mapOfCustomerEmailAndPassword
                emailLiveData.observe(requireActivity(), this::checkLogin)

                return@OnKeyListener true
            }
            false
        })


        return root
    }


    private fun logInError(){
        // build alert dialog
        val dialogBuilder = AlertDialog.Builder(requireActivity())

        // set message of alert dialog
        dialogBuilder.setMessage("Ihr Passwort war leider falsch, bitte versuchen Sie es nochmal!")
            // if the dialog is cancelable
            .setCancelable(false)
            // negative button text and action
            .setNegativeButton("Login Daten nochmal eingeben.", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })

        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("Falsche Login Daten")
        // show alert dialog
        alert.show()
    }

    private fun emailError(){
        val dialogBuilder = AlertDialog.Builder(requireActivity())

        // set message of alert dialog
        dialogBuilder.setMessage("Ihre Email war leider falsch, bitte versuchen Sie es nochmal!")
            // if the dialog is cancelable
            .setCancelable(false)
            // negative button text and action
            .setNegativeButton("Email nochmal eingeben.", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })

        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("Falscher Email-Addresse")
        // show alert dialog
        alert.show()
    }

    //(
    private fun checkLogin ( userCredentials: Map<String, String>){

        if (userCredentials.containsKey(this.email) && userCredentials.containsValue(this.password)) {
            onPostExecute()
            // TODO: Value is not right
            this.viewModel.getCustomerIdByEmail(this.email).observe(requireActivity(), this::setCustomerIdStore)
        }
        else {
            logInError()
        }

        this.updateStuff()

    }

    private fun validateEmail() : Boolean{

        if (!EMAIL_ADDRESS_PATTERN.matcher(this.email).matches()){
            emailError()
        }
        return EMAIL_ADDRESS_PATTERN.matcher(this.email).matches()
    }

    private fun onPostExecute() {
        val startActivity = Intent(activity, StartActivity::class.java)
        startActivity(startActivity)
    }

    private fun setCustomerIdStore(id: Long){
        val appInstance = activity?.application
        val customerIdStore : CustomerIdStore = CustomerIdStore(appInstance)
        Log.i("Check Id", "id:$id")
        customerIdStore.setCustomerId(STORE_KEY_COUNTER, id )
    }

    private fun updateStuff(){
        val appInstance = activity?.application
        val customerIdStore : CustomerIdStore = CustomerIdStore(appInstance)
        Log.i("Check Infos", "Info:" + customerIdStore.getCustomerId(STORE_KEY_COUNTER))
    }
}