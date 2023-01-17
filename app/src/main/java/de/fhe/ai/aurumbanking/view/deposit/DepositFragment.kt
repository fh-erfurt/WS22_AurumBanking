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
import de.fhe.ai.aurumbanking.view.login.LogInFragmentViewModel
import de.fhe.ai.aurumbanking.view.overview.OverviewViewModel

class DepositFragment : Fragment(){

    private var helper: Helper = Helper.getHelperInstance()
    private lateinit var root: View

    //private lateinit var viewModel: OverviewViewModel
    
    private lateinit var viewModel : LogInFragmentViewModel
    
    
    
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        
        this.root = inflater.inflate(R.layout.fragment_deposit, container, false)

        val depositInformation = this.root.findViewById<TextView?>(R.id.depotDateInformation)
        depositInformation.text = helper.getDate(false).toString()

        /*
            set current deposit of user, leftside
         */
        val currentDeposit = this.root.findViewById<TextView?>(R.id.userDepotLeft)
        currentDeposit.text = "5478,45 \n Euro"

        /*
           set current deposit of user, rightside
        */
        val currentDepotCountry = this.root.findViewById<ImageView>(R.id.userDepotRight)
        currentDepotCountry.setImageResource(R.drawable.europaische_union)

      
        return this.root


    }

}