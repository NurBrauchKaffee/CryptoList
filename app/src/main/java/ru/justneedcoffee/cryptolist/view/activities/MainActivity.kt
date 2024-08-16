package ru.justneedcoffee.cryptolist.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.chip.Chip
import ru.justneedcoffee.cryptolist.R

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
//    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_fragment)
        setSupportActionBar(findViewById(R.id.listToolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false)
//        setContentView(R.layout.activity_main)
//        navController = findNavController(R.id.navHostFragment)
//        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}