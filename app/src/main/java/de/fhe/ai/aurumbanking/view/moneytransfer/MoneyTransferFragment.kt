package de.fhe.ai.aurumbanking.view.moneytransfer


import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
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

            var dateFromEditText = root.findViewById<EditText?>(R.id.dateOfTransferRight).text.toString()
            var date = Converters.fromDateToString(dateFromEditText)

            var beneficiary = root.findViewById<EditText?>(R.id.beneficiaryRight).text.toString()
            var iban = root.findViewById<EditText?>(R.id.ibanRight).toString()
            var bankName = root.findViewById<EditText?>(R.id.bankNameRight).toString()
            var bic = root.findViewById<EditText?>(R.id.bicRight).toString()
            var moneyValueFromEditText = root.findViewById<EditText?>(R.id.amountMoneyRight).toString()

            Log.i("Money String", "Show:" + moneyValueFromEditText)

            //TODO need to fix Converters
            var moneyValue = Converters.stringToBigDecimal(moneyValueFromEditText)

            var purposeOfUse = root.findViewById<EditText?>(R.id.UsageRight).toString()

            val newTransactionListElement = TransactionList(true)
            newTransactionListElement.customerId = this.customerId
            newTransactionListElement.depositId = this.customerId

            val transactionListId = viewModel.insertNewTransactionListFlagByCustomerId(newTransactionListElement)

            viewModel.prepareNewOutputTransactionByTransactionListId(date,beneficiary,iban,bankName,
                moneyValue, purposeOfUse , transactionListId )

            Helper.getHelperInstance().hideKeyboard(requireContext(), this.view)
        }
    }
}


