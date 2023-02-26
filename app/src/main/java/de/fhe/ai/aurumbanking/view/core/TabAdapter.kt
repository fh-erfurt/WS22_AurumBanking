package de.fhe.ai.aurumbanking.view.core

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import de.fhe.ai.aurumbanking.view.moneytransfer.MoneyTransferFragment
import de.fhe.ai.aurumbanking.view.overview.OverviewFragment
import de.fhe.ai.aurumbanking.view.support.SupportFragment
import de.fhe.ai.aurumbanking.view.deposit.DepositFragment
import de.fhe.ai.aurumbanking.view.deposit.DepositFragmentHolder

@Suppress("DEPRECATION")
internal class TabAdapter (var context: Context, fm: FragmentManager, var totalTabs: Int):
    FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> {
                    OverviewFragment()
                }
                1 -> {
                    de.fhe.ai.aurumbanking.view.profil.ProfileFragment()
                }
                2 -> {
                    MoneyTransferFragment()
                }
                3 -> {
                    DepositFragmentHolder()
                }
                4 -> {
                    SupportFragment()
                }
                else -> getItem(position)
            }
        }
        override fun getCount(): Int {
            return totalTabs
        }


}