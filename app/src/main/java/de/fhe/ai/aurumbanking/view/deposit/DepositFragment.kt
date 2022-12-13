package de.fhe.ai.aurumbanking.view.deposit

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import de.fhe.ai.aurumbanking.R

class DepositFragment : Fragment(){
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

        // Inflate the layout for this fragment
        return root


    }

}