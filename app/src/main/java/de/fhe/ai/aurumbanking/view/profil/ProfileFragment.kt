package de.fhe.ai.aurumbanking.view.profil

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import de.fhe.ai.aurumbanking.R
import de.fhe.ai.aurumbanking.core.CustomerIdStore
import androidx.appcompat.app.AppCompatActivity

class ProfileFragment : Fragment() {
    private var customerId: Long? = null
    private val STORE_KEY_COUNTER = "CustomerId"
    private lateinit var viewModel: ProfileViewModel
    private lateinit var root: View

    private lateinit var firstname: String
    private lateinit var midname: String
    private lateinit var lastname: String


    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        this.root = inflater.inflate(R.layout.fragment_profile, container, false)
        getCustomerId()

        this.viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        this.viewModel.getCustomerEmailByCustomerId(this.customerId)
            .observe(this.requireActivity(), this::setCustomerEmailProfileToFragment)
        this.viewModel.getCustomerEmailByCustomerId(this.customerId)
            .removeObserver(this::setCustomerEmailProfileToFragment)
        this.viewModel.getCustomerPhoneNumberByCustomerId(this.customerId)
            .observe(this.requireActivity(), this::setCustomerPhonenumberToFragment)
        this.viewModel.getCustomerPhoneNumberByCustomerId(this.customerId)
            .removeObserver(this::setCustomerPhonenumberToFragment)

        this.viewModel.getCustomerFirstNameByCustomerId(this.customerId)
            .observe(this.requireActivity(), this::prepareCustomerFirstname)
        this.viewModel.getCustomerMidNameByCustomerId(this.customerId)
            .observe(this.requireActivity(), this::prepareCustomerMidname)
        this.viewModel.getCustomerLastNameByCustomerId(this.customerId)
            .observe(this.requireActivity(), this::prepareCustomerLastname)


        val switchChangePasswordSection = this.root.findViewById<Switch?>(R.id.switch1)

        switchChangePasswordSection.setOnCheckedChangeListener ({ _, isChecked ->
            if (isChecked) {
                this.root.findViewById<TextView?>(R.id.oldPwLeft).visibility = View.VISIBLE
                this.root.findViewById<EditText?>(R.id.oldPwRight).visibility = View.VISIBLE
                this.root.findViewById<TextView?>(R.id.newPwLeft).visibility = View.VISIBLE
                this.root.findViewById<EditText?>(R.id.newPwRight).visibility = View.VISIBLE
                this.root.findViewById<TextView?>(R.id.newPwAgainLeft).visibility = View.VISIBLE
                this.root.findViewById<EditText?>(R.id.newPwAgainLeft).visibility = View.VISIBLE
                //this.root.findViewById<Button?>(R.id.button).visibility = View.VISIBLE
            }else{
                this.root.findViewById<TextView?>(R.id.oldPwLeft).visibility = View.VISIBLE
                this.root.findViewById<EditText?>(R.id.oldPwRight).visibility = View.VISIBLE
                this.root.findViewById<TextView?>(R.id.newPwLeft).visibility = View.VISIBLE
                this.root.findViewById<EditText?>(R.id.newPwRight).visibility = View.VISIBLE
                this.root.findViewById<TextView?>(R.id.newPwAgainLeft).visibility = View.VISIBLE
                this.root.findViewById<EditText?>(R.id.newPwAgainLeft).visibility = View.VISIBLE
                //this.root.findViewById<Button?>(R.id.button).visibility = View.VISIBLE
        }
        })


        return this.root
    }

    private fun getCustomerId() {
        val appInstance = activity?.application
        val customerIdStore: CustomerIdStore = CustomerIdStore(appInstance)
        this.customerId = customerIdStore.getCustomerId(STORE_KEY_COUNTER)
        Log.i("Check Infos", "Info:" + this.customerId)
    }


    private fun setCustomerEmailProfileToFragment(email: String) {
        this.root.findViewById<TextView?>(R.id.userEmailRight).text = email
    }

    private fun setCustomerPhonenumberToFragment(phonenumber: Int) {
        this.root.findViewById<TextView?>(R.id.userTelNumberRight).text = phonenumber.toString()
    }

    @SuppressLint("SetTextI18n")
    private fun setCustomerFullNameToFragment() {
        if (this.midname.isEmpty()) {
            root.findViewById<TextView?>(R.id.customerFullName).text =
                this.firstname + " " + this.lastname
        } else {
            root.findViewById<TextView?>(R.id.customerFullName).text =
                this.firstname + " " + this.midname + " " + this.lastname
        }

    }


    private fun prepareCustomerFirstname(firstname: String) {
        Log.i("Check fname", "fname:$firstname")
        this.firstname = firstname
        root.findViewById<TextView?>(R.id.customerFullName).text = this.firstname

    }

    private fun prepareCustomerMidname(midname: String) {
        Log.i("Check mname", "mname:$midname")
        this.midname = midname
    }

    private fun prepareCustomerLastname(lastname: String) {
        Log.i("Check lname", "lname:$lastname")
        this.lastname = lastname

        setCustomerFullNameToFragment()
    }


}