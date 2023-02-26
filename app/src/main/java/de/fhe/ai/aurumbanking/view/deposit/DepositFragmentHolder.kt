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

class DepositFragmentHolder : Fragment() {

    private lateinit var root: View

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        this.root = inflater.inflate(R.layout.fragement_deposit_viewholder, container, false)


        // TODO raus in neues fragment
        val navHostFragment: Fragment =
            childFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        //val mNavController = navHostFragment.navController
        //NavigationUI.setupActionBarWithNavController(this.activity(), navController)
        //NavigationUI.setupWithNavController(navController)

        return this.root
    }

    override fun onResume() {
        super.onResume()

        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish()
                }

            })
    }

}

