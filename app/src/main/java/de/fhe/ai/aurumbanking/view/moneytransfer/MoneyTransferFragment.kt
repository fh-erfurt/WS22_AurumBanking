package de.fhe.ai.aurumbanking.view.moneytransfer


import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import de.fhe.ai.aurumbanking.R
import de.fhe.ai.aurumbanking.core.Converters
import de.fhe.ai.aurumbanking.core.Helper
import de.fhe.ai.aurumbanking.model.TransactionList
import java.math.BigDecimal
import java.util.*

class MoneyTransferFragment : Fragment() {
    private var customerId: Long? = null
    private val STORE_KEY_COUNTER = "CustomerId"
    private lateinit var viewModel: MoneyTransferViewModel

    private lateinit var transferDatePicker: EditText
    private lateinit var thiscontext: Context
    private lateinit var root: View

    private var depotValue: BigDecimal? = null


    private var helper: Helper = Helper.getHelperInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        this.root = inflater.inflate(R.layout.fragment_moneytransfer, container, false)

        /**
         * Get customerId from shared preference
         */
        this.customerId = helper.getCustomerId(activity?.application)

        this.viewModel = ViewModelProvider(this)[MoneyTransferViewModel::class.java]

        transferDatePicker = root.findViewById<EditText?>(R.id.dateOfTransferRight)


        /**
         * setOnClickListener which display a date picker and save value in the Edittext
         */
        transferDatePicker.setOnClickListener {

            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = container?.let { it1 ->
                DatePickerDialog(
                    it1.context,
                    { _, year, monthOfYear, dayOfMonth ->
                        // setting date to our edit text.
                        val date = (dayOfMonth.toString() + "." + (monthOfYear + 1) + "." + year)
                        transferDatePicker.setText(date)
                    },
                    // passing year, month and day for the selected date in our date picker.
                    year, month, day
                )
            }
            // display DayPicker
            datePickerDialog?.show()
        }

        this.viewModel.getCustomerDepositByCustomerId(this.customerId).observe(this.requireActivity(), this::convertCurrentDepotValueLiveData)


        return root
    }


    override fun onResume() {
        super.onResume()

        var transactionSubmitButton = root.findViewById<Button?>(R.id.transactionSubmitButton)


        /**
         * setOnClickListener for the "Überweisung durchführen"-Button.
         * Take all the Edittext in the formula and bypass it to the view model
         */
        transactionSubmitButton.setOnClickListener{

            val dateFromEditText = root.findViewById<EditText?>(R.id.dateOfTransferRight).text.toString()
            val beneficiary = root.findViewById<EditText?>(R.id.beneficiaryRight).text.toString()
            val iban = root.findViewById<EditText?>(R.id.ibanRight).text.toString()
            val bankName = root.findViewById<EditText?>(R.id.bankNameRight).text.toString()
            val bic = root.findViewById<EditText?>(R.id.bicRight).text.toString()
            val moneyValueFromEditText = root.findViewById<EditText?>(R.id.amountMoneyRight).text.toString()

            Log.i("Money String", "Show:$moneyValueFromEditText")
            val purposeOfUse = root.findViewById<EditText?>(R.id.UsageRight).text.toString()

            val newTransactionListElement = TransactionList(true)
            newTransactionListElement.customerId = this.customerId

            // deposit ID == customerId
            newTransactionListElement.depositId = this.customerId

            checkTransferInputField(dateFromEditText, beneficiary, iban, bankName, bic, moneyValueFromEditText, purposeOfUse)

            Helper.getHelperInstance().hideKeyboard(requireContext(), this.view)


        }

    }


    /**
     * Check all the needed Edittext from the formula is not emptys
     *
     * @param dateFromEditText
     * @param beneficiary
     * @param iban
     * @param bankName
     * @param bic
     * @param moneyValue
     * @param purposeOfUse
     */
    private fun checkTransferInputField(dateFromEditText: String, beneficiary : String, iban : String, bankName: String, bic: String, moneyValue : String, purposeOfUse : String){
        if(dateFromEditText.trim().isEmpty() && beneficiary.trim().isEmpty() && iban.trim()
                .isEmpty() && bankName.trim().isEmpty() && bic.trim().isEmpty()
            && moneyValue.trim().isEmpty() && purposeOfUse.trim().isEmpty()
        ){
            failedTransaction()
        }
        else{
            val moneyValue = Converters.stringToBigDecimal(moneyValue)

            /**
             * current value - transfer value = newDepotValue
             */
            val newDepotValue = this.depotValue?.minus(moneyValue)

            viewModel.insertNewTransactionListElementByCustomerId(
                this.customerId, this.customerId, dateFromEditText, beneficiary, iban, bankName,
                moneyValue, purposeOfUse, newDepotValue, bic
            )
            successfulTransaction()
        }

    }


    /**
     *
     * DialogBuilder for failed money transfer order.
     */
    private fun failedTransaction() {
        val dialogBuilder = AlertDialog.Builder(requireActivity())

        // set message of alert dialog
        dialogBuilder.setMessage("Ihre Eingaben sind unvollständig. Bitte erneurt eingeben!")
            // if the dialog is cancelable
            .setCancelable(false)
            // negative button text and action
            .setNegativeButton(
                "Eingaben prüfen!",
                DialogInterface.OnClickListener { dialog, _ ->
                    dialog.cancel()
                })

        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("Transaktion Fehlgeschlagen!")
        // show alert dialog
        alert.show()
    }


    /**
     * 
     * DialogBuilder for sucessfull money transfer order.
     */
    private fun successfulTransaction() {
        val dialogBuilder = AlertDialog.Builder(requireActivity())

        // set message of alert dialog
        dialogBuilder.setMessage("Transaktion erfolgreich ausgeführt!")
            // if the dialog is cancelable
            .setCancelable(false)
            .setPositiveButton("Verstanden!", DialogInterface.OnClickListener { dialog, _ ->
                dialog.cancel()
            })

        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("Transaktion Erfolgreich!")
        // show alert dialog
        alert.show()
    }

    private fun convertCurrentDepotValueLiveData(currentDepotValue: BigDecimal) {
        this.depotValue = currentDepotValue
    }
}


