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
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.fhe.ai.aurumbanking.R
import de.fhe.ai.aurumbanking.core.Helper
import java.math.BigDecimal

/**
 * Class is just a placeholder for the navigation logic from Depotfragment to the Transactionlist Detailview Fragment.
 */
class DepositFragmentHolder : Fragment() {

    private lateinit var root: View
    private lateinit var navHostFragment: Fragment

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        this.root = inflater.inflate(R.layout.fragement_deposit_viewholder, container, false)

        /**
         * define childFragmentManager, which search the navigation host fragment
         */
        navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        return this.root
    }

    override fun onResume() {
        super.onResume()

        /**
         * Business Logic for the "Back-Button" from detailview to depositview
         */
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    NavHostFragment.findNavController(navHostFragment).popBackStack()
                }

            })
    }

    override fun onPause() {
        super.onPause()
        /**
         * destroy detailview, when fragment is not use
         */
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()

    }
}


