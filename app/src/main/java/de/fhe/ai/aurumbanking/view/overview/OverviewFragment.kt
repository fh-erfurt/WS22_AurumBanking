package de.fhe.ai.aurumbanking.view.overview

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
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
import java.math.BigDecimal

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

        this.viewModel.getCustomerDepositByCustomerId(this.customerId)
            .observe(this.requireActivity(), this::setCustomerDepotToFragment)

        this.viewModel.getLatestDeductionFlagByCustomerId(this.customerId)
            .observe(this.requireActivity(), this::setIconForLatestTransaction)

        this.viewModel.getLatestMoneyValueFromOrderInputByCustomerId(this.customerId)
            .observe(this.requireActivity(), this::setValueForLatestTransaction)

        /*
         set current Date
        */
        val currentDate = this.root.findViewById<TextView?>(R.id.currentDate)
        currentDate.text = helper.getDate(true).toString()


        val depositInformation = this.root.findViewById<TextView?>(R.id.depositInformation)
        depositInformation.text = "Sichteinlagen vom " + helper.getDate(false)

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


        return this.root


    }

    override fun onPause() {
        super.onPause()

        this.viewModel.getCustomerFullNameByCustomerId(this.customerId)
            .removeObserver(this::setCustomerFullNameToFragment)

        this.viewModel.getCustomerDepositByCustomerId(this.customerId)
            .removeObserver(this::setCustomerDepotToFragment)

        this.viewModel.getLatestDeductionFlagByCustomerId(this.customerId)
            .removeObserver(this::setIconForLatestTransaction)
    }


    @SuppressLint("SetTextI18n")
    private fun setCustomerFullNameToFragment(fullCustomerName : String) {
        this.root.findViewById<TextView?>(R.id.welcomeUserText).text =
            "Willkommen \n$fullCustomerName!"
    }

    @SuppressLint("SetTextI18n")
    private fun setCustomerDepotToFragment(depositValue : BigDecimal) {
        this.root.findViewById<TextView?>(R.id.userDepotLeft).text = "$depositValue\nEuro"
    }

    private fun setIconForLatestTransaction(deductionFlag : Boolean){
        if (deductionFlag){
            this.root.findViewById<ImageButton>(R.id.cicle)
                .setImageResource(R.drawable.redcirle)
        }else{
            this.root.findViewById<ImageButton>(R.id.cicle)
                .setImageResource(R.drawable.greencirle)
        }

    }


    @SuppressLint("SetTextI18n")
    private fun setValueForLatestTransaction(moneyValue : String){

        //TODO: Fix Flag for latestValue

        this.root.findViewById<TextView>(R.id.lastTransaction).text = moneyValue.toString().replace(",", "\n\n") + " Euro"

        //if (this.viewModel.getLatestDeductionFlagByCustomerId(this.customerId).value == false){
        //
        //}else{
        //    this.root.findViewById<TextView>(R.id.lastTransaction).text = null
        //}

    }




}