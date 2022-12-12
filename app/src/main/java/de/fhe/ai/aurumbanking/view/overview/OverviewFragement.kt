package de.fhe.ai.aurumbanking.view.overview

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import de.fhe.ai.aurumbanking.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class OverviewFragement : Fragment() {


    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val root: View = inflater.inflate(R.layout.fragment_overview, container, false)

        /*
            set Text User Greetings
         */
        val greetings = root.findViewById<TextView?>(R.id.welcomeUserText)
        greetings.text = "Hello Maria \n Mustermann!"

        /*
            set current Date
         */
        val currentDate = root.findViewById<TextView?>(R.id.currentDate)
        currentDate.text = "10.12.2022"


        val depositInformation = root.findViewById<TextView?>(R.id.depositInformation)
        depositInformation.text = "Sichteinlagen vom 10.12.2022"

        /*
            set color of deposit Textview
         */
        val currentDeposit = root.findViewById<TextView?>(R.id.currentDeposit)
        currentDeposit.text = "5478,45 \n Euro"
        // Inflate the layout for this fragment


        val lastTransactionInfo = root.findViewById<TextView?>(R.id.lastTransactionInformation)
        lastTransactionInfo.text = "letzte Transaktion"


        /*
            TODO: check if output transaction flag is set
         */
        val redCirle = root.findViewById<ImageButton>(R.id.cicle)
        redCirle.setImageResource(R.drawable.redcirle)


        val lasTransaction = root.findViewById<TextView>(R.id.lastTransaction)
        lasTransaction.text = "4,99 Euro \n Rewe Anger"


        return root


    }

    // TODO: Fix Date format
    @RequiresApi(Build.VERSION_CODES.O)
    private fun getDate(): String {
        val current = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss.SSS")
        return current.format(formatter)
    }
}