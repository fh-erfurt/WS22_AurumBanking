package de.fhe.ai.aurumbanking.view.deposit

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.fhe.ai.aurumbanking.R
import de.fhe.ai.aurumbanking.core.Helper
import java.math.BigDecimal

class DepositFragment : Fragment(){

    val ARG_TRANSACTIONLISTID_ID = "transactionListId"
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

        val depositInformation = this.root.findViewById<TextView?>(R.id.depotDateInformation)
        depositInformation.text = "Sichteinlagen vom "+ helper.getDate(false).toString()

        val currentDepotCountry = this.root.findViewById<ImageView>(R.id.userDepotRight)
        currentDepotCountry.setImageResource(R.drawable.europaische_union)



        // RecyclerView Stuff
        // ---------------------------------------
        // Get RecyclerView Reference
        val latestDepotTransaction = root.findViewById<RecyclerView?>(R.id.latestDepotTransaction)

        //TODO zweites Element öffnet sich zwar, Daten werden aber nicht weiter gegeben
        val adapter = DepositTransactionListAdapter(requireActivity()) { transactionListId ->
                val args = Bundle().apply { putLong(ARG_TRANSACTIONLISTID_ID, transactionListId)}
                val nc = NavHostFragment.findNavController(this)
                nc.navigate(R.id.action_depositFragment_to_transactionDetailFragment, args)
            }

        latestDepotTransaction.adapter = adapter
        latestDepotTransaction.layoutManager = LinearLayoutManager(requireActivity())

        this.viewModel.transactionList.observe(this.requireActivity(), adapter::setTransactionList)


        return this.root

    }


    override fun onResume() {
        super.onResume()
        val searchItem : SearchView = this.root.findViewById<SearchView>(R.id.searchDepotTransaction)
        searchItem.setOnQueryTextListener( object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                Helper.getHelperInstance().hideKeyboard(requireActivity(), searchItem)
                return true
            }

            override fun onQueryTextChange(searchTerm: String): Boolean {
                this@DepositFragment.setSearchTerm(searchTerm)
                return true
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun setCustomerDepotToFragment(depositValue : BigDecimal) {
        this.root.findViewById<TextView?>(R.id.userDepotLeft).text = "${depositValue.toString()}\nEuro"
    }

    private fun setSearchTerm( searchTerm: String){
        this.viewModel = ViewModelProvider(this)[DepositViewModel::class.java]
        this.viewModel.setSearchTerm(searchTerm)
    }
}


