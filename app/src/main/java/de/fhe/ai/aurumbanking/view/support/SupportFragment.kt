package de.fhe.ai.aurumbanking.view.support


import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import de.fhe.ai.aurumbanking.R
import de.fhe.ai.aurumbanking.core.Helper
import de.fhe.ai.aurumbanking.view.profil.ProfileViewModel

/**
 * Fragment class for the support layout of the app.
 *
 */
class SupportFragment : Fragment() {
    private lateinit var root: View
    private var customerId: Long? = null
    private val STORE_KEY_COUNTER = "CustomerId"
    private var helper: Helper = Helper.getHelperInstance()
    private lateinit var spinner : Spinner
    private lateinit var viewModel: SupportViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        this.root = inflater.inflate(R.layout.fragment_support, container, false)

        /**
         * Get customerid from shared preference
         */
        this.customerId = helper.getCustomerId(activity?.application)
        this.viewModel = ViewModelProvider(this)[SupportViewModel::class.java]


        this.viewModel.getCustomerFullNameByCustomerId(this.customerId)
            .observe(this.requireActivity(), this::setCustomerFullNameToFragment)


        val request = resources.getStringArray(R.array.kind_of_request)

        /**
         * create and set a array adapter for the spinner, which multiple value to select
         */
        this.spinner = this.root.findViewById<Spinner>(R.id.spinnerSelectRequest)
        val adapter = ArrayAdapter( requireActivity(), R.layout.spinner_kind_of_request, request)
        adapter.setDropDownViewResource(R.layout.spinner_kind_of_request)
        spinner.adapter = adapter
        return this.root
    }

    override fun onResume() {
        super.onResume()

        val supportRequestButton = this.root.findViewById<Button?>(R.id.supportRequestButton)

        /**
         * setOnClickListener for "Anfrage absenden" button and bypass the values after the click to the view model
         */
        supportRequestButton.setOnClickListener {
            val kindOrRequest = this.root.findViewById<Spinner?>(R.id.spinnerSelectRequest).selectedItem.toString()
            val customerRequestEmail = this.root.findViewById<EditText?>(R.id.userEmailRight).text.toString()
            val customerRequestMessage = this.root.findViewById<EditText>(R.id.customerSupportMessage).text.toString()

            this.checkRequiredFieldsAreFilled(kindOrRequest, customerRequestEmail, customerRequestMessage)

            this.root.findViewById<EditText?>(R.id.userEmailRight).text.clear();
            this.root.findViewById<EditText>(R.id.customerSupportMessage).text.clear()

            Helper.getHelperInstance().hideKeyboard(requireContext(), this.view)

        }
    }

    override fun onPause() {
        super.onPause()

        this.viewModel.getCustomerFullNameByCustomerId(this.customerId)
            .removeObserver(this::setCustomerFullNameToFragment)
    }

    /**
     * Checking if required fields are filled in the formula
     *
     * @param kindOrRequest
     * @param customerRequestEmail
     * @param customerRequestMessage
     * @return
     */
    private fun checkRequiredFieldsAreFilled(kindOrRequest:String, customerRequestEmail : String
                                     ,customerRequestMessage :String) : Boolean{

        return if (kindOrRequest.isEmpty()){
            errorMessage("Bitte wählen Sie eine Anfrage Kategorie aus!")
            false
        } else if (customerRequestEmail.isEmpty()){
            errorMessage("Bitte geben Sie eine korrekte Email Addresse ein!")
            false
        } else if (customerRequestMessage.isEmpty()){
            errorMessage("Bitte geben Sie eine Nachricht für den Support ein!")
            false
        } else {
            successfulMessage("Vielen Dank für Ihre Anfrage, das Anliegen wird an den Supprt weitergeleitet!")
            true
        }
    }

    /**
     * display customer full name to the fragment view
     *
     * @param customerFullName
     */
    private fun setCustomerFullNameToFragment(customerFullName: String) {
        this.root.findViewById<TextView?>(R.id.customerFullNameSupport).text = customerFullName

    }

    /**
     * DialogBuilder for wrong input value of the support formula
     *
     * @param errorMessage
     */
    private fun errorMessage(errorMessage: String) {
        val dialogBuilder = AlertDialog.Builder(requireActivity())

        // set message of alert dialog
        dialogBuilder.setMessage(errorMessage)
            // if the dialog is cancelable
            .setCancelable(false)
            // negative button text and action
            .setNegativeButton(
                "Überprüfen Sie ihre Eingaben!",
                DialogInterface.OnClickListener { dialog, _ ->
                    dialog.cancel()
                })
        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("Fehler Support Anfrage!")
        // show alert dialog
        alert.show()
    }

    /**
     * DialogBuilder for valid input value of the support formula
     *
     * @param message
     */
    private fun successfulMessage(message: String) {
        val dialogBuilder = AlertDialog.Builder(requireActivity())

        // set message of alert dialog
        dialogBuilder.setMessage(message)
            // if the dialog is cancelable
            .setCancelable(false)
            .setPositiveButton("Verstanden!", DialogInterface.OnClickListener { dialog, _ ->
                dialog.cancel()
            })
        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("Anfrage abgeschickt!")
        // show alert dialog
        alert.show()
    }
}