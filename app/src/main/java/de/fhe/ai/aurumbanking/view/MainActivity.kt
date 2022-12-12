package de.fhe.ai.aurumbanking.view

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import de.fhe.ai.aurumbanking.R
import java.util.regex.Pattern


class MainActivity : AppCompatActivity() {

    private var password: String = ""
    private var email: String = ""

    private val EMAIL_ADDRESS_PATTERN = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_main)

        val login_Button = findViewById<Button>(R.id.login)

        login_Button.setOnClickListener() {
            getCredentails()
            checkLogin(validateEmail())
        }

        (findViewById<TextView>(R.id.UserPassword)).setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                getCredentails()
                checkLogin(validateEmail())

                return@OnKeyListener true
            }
            false
        })
    }


    private fun logInError(){
        // build alert dialog
        val dialogBuilder = AlertDialog.Builder(this)

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
        val dialogBuilder = AlertDialog.Builder(this)

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


    private fun getCredentails(){
        this.email = (findViewById<TextView>(R.id.UserEmailAddress)).text.toString()
        this.password = (findViewById<TextView>(R.id.UserPassword)).text.toString()
    }

    private fun checkLogin(emailValidation: Boolean){
        if (this.email.equals("abc@test.de")  && this.password.equals("123") && emailValidation) {
            onPostExecute()
        }
        else {
            logInError()
        }
    }

    private fun validateEmail() : Boolean{

        if (!EMAIL_ADDRESS_PATTERN.matcher(this.email).matches()){
            emailError()
        }
        return EMAIL_ADDRESS_PATTERN.matcher(this.email).matches()
    }

    private fun onPostExecute() {
        val startActivity = Intent(this, StartActivity::class.java)
        startActivity(startActivity)
    }
}
