package de.fhe.ai.aurumbanking.view.profil

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import de.fhe.ai.aurumbanking.R
import de.fhe.ai.aurumbanking.core.CustomerIdStore
import de.fhe.ai.aurumbanking.core.Helper

class ProfileFragment : Fragment() {
    private var customerId: Long? = null
    private val STORE_KEY_COUNTER = "CustomerId"
    private lateinit var viewModel: ProfileViewModel
    private lateinit var root: View

    private lateinit var firstname: String
    private lateinit var lastname: String

    private lateinit var currentPassword: String

    private var helper: Helper = Helper.getHelperInstance()


    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        this.root = inflater.inflate(R.layout.fragment_profile, container, false)

        this.customerId = helper.getCustomerId(activity?.application)
        this.viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        this.viewModel.getCustomerEmailByCustomerId(this.customerId)
            .observe(this.requireActivity(), this::setCustomerEmailProfileToFragment)

        this.viewModel.getCustomerPhoneNumberByCustomerId(this.customerId)
            .observe(this.requireActivity(), this::setCustomerPhonenumberToFragment)

        this.viewModel.getCustomerFullNameByCustomerId(this.customerId)
            .observe(this.requireActivity(), this::setCustomerFullNameToFragment)

        this.viewModel.getCustomerFullAddressById(this.customerId)
            .observe(this.requireActivity(), this::setCustomerAddressToFragment)

        this.viewModel.getCustomerAccountPasswordById(this.customerId)
            .observe(this.requireActivity(), this::getCurrentCustomerPassword)

        hidePasswordChangeSection()




        return this.root
    }

    override fun onResume() {
        super.onResume()

        val switchChangePasswordSection = this.root.findViewById<Switch?>(R.id.switch1)
        switchChangePasswordSection.setOnCheckedChangeListener({ _, isChecked ->
            if (isChecked) {
                this.root.findViewById<TextView?>(R.id.oldPwLeft).visibility = View.VISIBLE
                this.root.findViewById<EditText?>(R.id.oldPwRight).visibility = View.VISIBLE
                this.root.findViewById<TextView?>(R.id.newPwLeft).visibility = View.VISIBLE
                this.root.findViewById<EditText?>(R.id.newPwRight).visibility = View.VISIBLE
                this.root.findViewById<TextView?>(R.id.newPwAgainLeft).visibility = View.VISIBLE
                this.root.findViewById<EditText?>(R.id.newPwAgainRight).visibility = View.VISIBLE
                this.root.findViewById<Button?>(R.id.passwordSubmitButton).visibility = View.VISIBLE
                Log.i("Switch Aktiviert", "OK, up")
            } else {
                hidePasswordChangeSection()
            }
        })

        val passwordSubmitButton = this.root.findViewById<Button?>(R.id.passwordSubmitButton)
        passwordSubmitButton.setOnClickListener({
            val userInputOldPassword =
                this.root.findViewById<EditText?>(R.id.oldPwRight).text.toString()
            val userInputNewPassword =
                this.root.findViewById<EditText?>(R.id.newPwRight).text.toString()
            val userSecondInputNewPassword =
                this.root.findViewById<EditText?>(R.id.newPwAgainRight).text.toString()
            checkNewPassword(userInputOldPassword, userInputNewPassword, userSecondInputNewPassword)

            clearPasswordEditText()

            Helper.getHelperInstance().hideKeyboard(requireContext(), this.view)
        })
    }

    override fun onPause() {
        super.onPause()

        this.viewModel.getCustomerEmailByCustomerId(this.customerId)
            .removeObserver(this::setCustomerEmailProfileToFragment)

        this.viewModel.getCustomerPhoneNumberByCustomerId(this.customerId)
            .removeObserver(this::setCustomerPhonenumberToFragment)

        this.viewModel.getCustomerFullAddressById(this.customerId)
            .removeObserver(this::setCustomerAddressToFragment)

        this.viewModel.getCustomerFullNameByCustomerId(this.customerId)
            .removeObserver(this::setCustomerFullNameToFragment)

        clearPasswordEditText()

    }

    private fun setCustomerEmailProfileToFragment(email: String) {
        this.root.findViewById<TextView?>(R.id.userEmailRight).text = email
    }

    private fun setCustomerPhonenumberToFragment(phonenumber: Int) {
        this.root.findViewById<TextView?>(R.id.userTelNumberRight).text = phonenumber.toString()
    }

    @SuppressLint("SetTextI18n")
    private fun setCustomerFullNameToFragment(customerFullName: String) {
        root.findViewById<TextView?>(R.id.customerFullName).text = customerFullName
    }

    private fun setCustomerAddressToFragment(fullCustomerAddress: String) {
        val formatedFullCustomerAddress = fullCustomerAddress.replace(",", "\n")
        root.findViewById<TextView?>(R.id.customerAddressTextView).text =
            formatedFullCustomerAddress
    }

    private fun getCurrentCustomerPassword(currentPassword: String) {
        Log.i("Check currentPassword", "fname:$currentPassword")
        this.currentPassword = currentPassword
    }

    private fun checkNewPassword(
        userInputOldPassword: String,
        newPassword: String,
        controllNewPassword: String
    ) {
        if (userInputOldPassword == this.currentPassword) {
            if (newPassword == controllNewPassword) {
                this.viewModel.updateNewCustomerAccountPasswordByCustomerId(this.customerId, newPassword)
                succesfulPasswordChange()
            }
        } else {
            failedNewPasswordAlert()
        }
    }

    private fun hidePasswordChangeSection(){
        this.root.findViewById<TextView?>(R.id.oldPwLeft).visibility = View.GONE
        this.root.findViewById<EditText?>(R.id.oldPwRight).visibility = View.GONE
        this.root.findViewById<TextView?>(R.id.newPwLeft).visibility = View.GONE
        this.root.findViewById<EditText?>(R.id.newPwRight).visibility = View.GONE
        this.root.findViewById<TextView?>(R.id.newPwAgainLeft).visibility = View.GONE
        this.root.findViewById<EditText?>(R.id.newPwAgainRight).visibility = View.GONE
        this.root.findViewById<Button?>(R.id.passwordSubmitButton).visibility =
            View.GONE
        Log.i("Switch Deaktiviert", "OK, down")
    }

    private fun clearPasswordEditText(){
        this.root.findViewById<EditText?>(R.id.oldPwRight).text.clear()
        this.root.findViewById<EditText?>(R.id.newPwRight).text.clear()
        this.root.findViewById<EditText?>(R.id.newPwAgainRight).text.clear()
    }




    private fun failedNewPasswordAlert() {
        val dialogBuilder = AlertDialog.Builder(requireActivity())

        // set message of alert dialog
        dialogBuilder.setMessage("Ihre Eingaben waren nicht korrekt. Bitte erneurt eingeben!")
            // if the dialog is cancelable
            .setCancelable(false)
            // negative button text and action
            .setNegativeButton(
                "Password nochmal ändern!",
                DialogInterface.OnClickListener { dialog, _ ->
                    dialog.cancel()
                })

        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("Passwortänderung Fehlgeschlagen!")
        // show alert dialog
        alert.show()
    }


    private fun succesfulPasswordChange() {
        val dialogBuilder = AlertDialog.Builder(requireActivity())

        // set message of alert dialog
        dialogBuilder.setMessage("Das Password wurde geändert")
            // if the dialog is cancelable
            .setCancelable(false)
            .setPositiveButton("Verstanden!", DialogInterface.OnClickListener { dialog, _ ->
                dialog.cancel()
            })

        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("Passwortänderung Erfolgreich!")
        // show alert dialog
        alert.show()
    }

}