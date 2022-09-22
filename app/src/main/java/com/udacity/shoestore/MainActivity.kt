package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.udacity.shoestore.databinding.ActivityMainBinding
import com.udacity.shoestore.screans.ShoeListDirections
import timber.log.Timber

class MainActivity : AppCompatActivity() {
/*    private lateinit var appBarConfiguration: AppBarConfiguration
    val navController = this.findNavController((R.id.navigation))
    appBarConfiguration = AppBarConfiguration(navController.graph)*/

    private val navController by lazy {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navigation) as NavHostFragment
        navHostFragment.navController
    }
    private val appBarConfiguration by lazy {
        AppBarConfiguration(navController.graph)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        setContentView(binding.root)
        Timber.plant(Timber.DebugTree())
        Timber.i("in activity main onCreate")



        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.loginFragment -> {
                    // Handle favorite icon press
                    navController.navigateUp()
                    navController.navigate(R.id.loginFragment)
                    true
                }

                else -> false
            }
        }

        setupActionBarWithNavController(navController!!, appBarConfiguration!!)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onNavigateUp(): Boolean {
        val navController = this.findNavController((R.id.navigation))
        return navController.navigateUp()
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

}

