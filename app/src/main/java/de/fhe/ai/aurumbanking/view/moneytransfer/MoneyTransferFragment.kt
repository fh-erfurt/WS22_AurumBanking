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
import de.fhe.ai.aurumbanking.view.profil.ProfileViewModel
import java.util.*

class MoneyTransferFragment : Fragment() {
    private var customerId: Long? = null
    private val STORE_KEY_COUNTER = "CustomerId"
    private lateinit var viewModel: MoneyTransferViewModel

    private lateinit var transferDate: EditText
    private lateinit var thiscontext: Context
    private lateinit var root: View

    private var helper: Helper = Helper.getHelperInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.root = inflater.inflate(R.layout.fragment_moneytransfer, container, false)

        this.customerId = helper.getCustomerId(activity?.application)

        this.viewModel = ViewModelProvider(this)[MoneyTransferViewModel::class.java]

        transferDate = root.findViewById<EditText?>(R.id.dateOfTransferRight)

        transferDate.setOnClickListener {

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
                        transferDate.setText(date)
                    },
                    // passing year, month and day for the selected date in our date picker.
                    year, month, day
                )
            }
            //display DayPicker
            datePickerDialog?.show()
        }

        return root
    }

    override fun onResume() {
        super.onResume()

        var transactionSubmitButton = root.findViewById<Button?>(R.id.transactionSubmitButton)

        transactionSubmitButton.setOnClickListener{

            // TODO: Check empty stuff fix

            var dateFromEditText = root.findViewById<EditText?>(R.id.dateOfTransferRight).text.toString()


            var beneficiary = root.findViewById<EditText?>(R.id.beneficiaryRight).text.toString()
            var iban = root.findViewById<EditText?>(R.id.ibanRight).text.toString()
            var bankName = root.findViewById<EditText?>(R.id.bankNameRight).text.toString()
            // TODO need Iban UI
            var bic = root.findViewById<EditText?>(R.id.bicRight).toString()
            var moneyValueFromEditText = root.findViewById<EditText?>(R.id.amountMoneyRight).text.toString()

            Log.i("Money String", "Show:" + moneyValueFromEditText)


            var purposeOfUse = root.findViewById<EditText?>(R.id.UsageRight).text.toString()

            val newTransactionListElement = TransactionList(true)
            newTransactionListElement.customerId = this.customerId
            newTransactionListElement.depositId = this.customerId

            val transactionListId = viewModel.insertNewTransactionListFlagByCustomerId(newTransactionListElement)

            checkTransferInputfield(dateFromEditText, beneficiary, iban, bankName, bic, moneyValueFromEditText, purposeOfUse, transactionListId)


            Helper.getHelperInstance().hideKeyboard(requireContext(), this.view)
        }
    }


    fun checkTransferInputfield(dateFromEditText: String, beneficiary : String, iban : String, bankName: String, bic: String, moneyValue : String, purposeOfUse : String , transactionListId: Long){
        if(dateFromEditText.trim().length == 0 && beneficiary.trim().length == 0 && iban.trim().length == 0 && bankName.trim().length == 0 && bic.trim().length == 0
            && moneyValue.trim().length == 0 && purposeOfUse.trim().length == 0){
            failedTransaction()
        }
        else{
            var date = Converters.fromDateToString(dateFromEditText)
            var moneyValue = Converters.stringToBigDecimal(moneyValue)
            viewModel.prepareNewOutputTransactionByTransactionListId(date,beneficiary,iban,bankName,
                moneyValue, purposeOfUse , transactionListId )
            succesfulTransaction()
        }

    }



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


    private fun succesfulTransaction() {
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
}


