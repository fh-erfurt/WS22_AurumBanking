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

    // on below line creating a variable.
    lateinit var transerDate: EditText
    lateinit var thiscontext: Context
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val root: View = inflater.inflate(R.layout.fragment_moneytransfer, container, false)


        return root
    }
}


