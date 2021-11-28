package com.example.semester_project_crypto_wallet.ui

import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.semester_project_crypto_wallet.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Changing policy because of response from Stellar server
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)


        // TESTING AREA

//        val user = User()
//        user.generateKeys()
//        user.createAccount()
//        user.checkBalances()

        // TESTING AREA


        NavigationUI.setupWithNavController(
            nav_view, Navigation.findNavController(
                this, R.id.nav_host_fragment
            )
        )
    }
    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.homeFragment -> {
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}