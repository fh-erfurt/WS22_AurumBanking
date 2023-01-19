package de.fhe.ai.aurumbanking.view.deposit

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import de.fhe.ai.aurumbanking.R
import de.fhe.ai.aurumbanking.core.Helper

class DepositFragment : Fragment(){

    private var customerId: Long? = null
    private var helper: Helper = Helper.getHelperInstance()
    private lateinit var root: View
    private lateinit var viewModel: DepositViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        
        this.root = inflater.inflate(R.layout.fragment_deposit, container, false)

        this.customerId = helper.getCustomerId(activity?.application)

        this.viewModel = ViewModelProvider(this)[DepositViewModel::class.java]

        this.viewModel.getCustomerDepositByCustomerId(this.customerId)
            .observe(this.requireActivity(), this::setCustomerDepotToFragment)

        // TODO: Fix XML, zu klein
        val depositInformation = this.root.findViewById<TextView?>(R.id.depotDateInformation)
        depositInformation.text = "Sichteinlagen vom "+ helper.getDate(false).toString()

        val currentDepotCountry = this.root.findViewById<ImageView>(R.id.userDepotRight)
        currentDepotCountry.setImageResource(R.drawable.europaische_union)

      
        return this.root

    }

    @SuppressLint("SetTextI18n")
    private fun setCustomerDepotToFragment(depositValue : Float) {
        this.root.findViewById<TextView?>(R.id.userDepotLeft).text = "${depositValue.toString()}\nEuro"
    }
}