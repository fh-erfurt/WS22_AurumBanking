package de.fhe.ai.aurumbanking.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import de.fhe.ai.aurumbanking.R
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import de.fhe.ai.aurumbanking.view.core.TabAdapter

class MainActivity : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    //lateinit var tabLayoutProfile: TabLayout
    //lateinit var tabLayoutMoneyTransfer: TabLayout
    //lateinit var tabLayoutDeposits: TabLayout
    //lateinit var tabLayoutSupport: TabLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.start_main)

        tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        viewPager = findViewById<ViewPager>(R.id.viewPager)

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.overview_icon).setText("Übersicht"))
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.user).setText("Profil"))
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.dollar_coin_2149).setText("Überweisung"))
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.money_bag_6384).setText("Depot"))
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.support_telefon).setText("Support"))


        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = TabAdapter(this, supportFragmentManager,
            tabLayout.tabCount)

        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })


        //tabLayoutProfile = findViewById(R.id.profil)
        //tabLayoutMoneyTransfer = findViewById(R.id.moneytransfer)
        //tabLayoutDeposits = findViewById(R.id.deposit)
        //tabLayoutSupport = findViewById(R.id.support)


    }
}