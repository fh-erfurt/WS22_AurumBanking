package de.fhe.ai.aurumbanking.view.deposit

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import de.fhe.ai.aurumbanking.R
import de.fhe.ai.aurumbanking.view.LogInFragment.LogInFragmentViewModel

class DepositFragment : Fragment(){

    private lateinit var viewModel : LogInFragmentViewModel
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {



        val root = inflater.inflate(R.layout.fragment_deposit, container, false)

        val depositInformation = root.findViewById<TextView?>(R.id.depotDateInformation)
        depositInformation.text = "Sichteinlagen vom 13.12.2022"

        /*
            set current deposit of user, leftside
         */
        val currentDeposit = root.findViewById<TextView?>(R.id.userDepotLeft)
        currentDeposit.text = "5478,45 \n Euro"

        /*
           set current deposit of user, rightside
        */
        val currentDepotCountry = root.findViewById<ImageView>(R.id.userDepotRight)
        currentDepotCountry.setImageResource(R.drawable.europaische_union)

        this.viewModel = ViewModelProvider(this)[LogInFragmentViewModel::class.java]

        //TODO: Still not working after DB is running
        val email = this.viewModel.getCustomerEmailById(14).value
        Log.i("Check Email in Deposit", "Email:" + email)
        // Inflate the layout for this fragment
        return root


    }

}