package de.fhe.ai.aurumbanking.view.overview

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import de.fhe.ai.aurumbanking.R
import de.fhe.ai.aurumbanking.core.Helper

import java.util.*

class OverviewFragment : Fragment() {
    private var customerId: Long? = null

    private lateinit var root: View

    private lateinit var viewModel: OverviewViewModel

    private var helper: Helper = Helper.getHelperInstance()

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        this.root = inflater.inflate(R.layout.fragment_overview, container, false)
        this.customerId = helper.getCustomerId(activity?.application)


        this.viewModel = ViewModelProvider(this)[OverviewViewModel::class.java]

        this.viewModel.getCustomerFullNameByCustomerId(this.customerId)
            .observe(this.requireActivity(), this::setCustomerFullNameToFragment)


        /*
         set current Date
        */
        val currentDate = this.root.findViewById<TextView?>(R.id.currentDate)
        currentDate.text = helper.getDate(true).toString()


        val depositInformation = this.root.findViewById<TextView?>(R.id.depositInformation)
        depositInformation.text = "Sichteinlagen vom " + helper.getDate(false)

        /*
         set current deposit of user, leftside
         // TODO: Backend
        */
        val currentDeposit = this.root.findViewById<TextView?>(R.id.userDepotLeft)
        currentDeposit.text = "5478,45 \n Euro"


        /*
         set current deposit of user, rightside
         // TODO: Backend Maybe
        */
        val currentDepotCountry = this.root.findViewById<ImageView>(R.id.userDepotRight)
        currentDepotCountry.setImageResource(R.drawable.europaische_union)


        /*
         set Text Information of latest Transaction
         // TODO: Backend
        */
        val lastTransactionInfo = this.root.findViewById<TextView?>(R.id.lastTransactionInformation)
        lastTransactionInfo.text = "Letzte Transaktion"


        /*
         TODO: check if output transaction flag is set in Backend
        */
        val redCirle = this.root.findViewById<ImageButton>(R.id.cicle)
        redCirle.setImageResource(R.drawable.redcirle)

        // // TODO: Backend
        val lasTransaction = this.root.findViewById<TextView>(R.id.lastTransaction)
        lasTransaction.text = "4,99 Euro \n Rewe Anger"

        return this.root


    }

    override fun onPause() {
        super.onPause()

        this.viewModel.getCustomerFullNameByCustomerId(this.customerId)
            .removeObserver(this::setCustomerFullNameToFragment)
    }


    @SuppressLint("SetTextI18n")
    private fun setCustomerFullNameToFragment(fullCustomerName : String) {
        this.root.findViewById<TextView?>(R.id.welcomeUserText).text =
            "Willkommen \n$fullCustomerName!"
    }


}