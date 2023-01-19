package de.fhe.ai.aurumbanking.view.moneytransfer


import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import de.fhe.ai.aurumbanking.R
import java.util.*

class MoneyTransferFragment : Fragment() {
    private lateinit var transerDate: EditText
    private lateinit var thiscontext: Context
    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        this.root = inflater.inflate(R.layout.fragment_moneytransfer, container, false)

        transerDate = root.findViewById<EditText?>(R.id.dateOfTransferRight)

        transerDate.setOnClickListener {

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
                        transerDate.setText(date)
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
}


