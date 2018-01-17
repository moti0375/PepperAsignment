package com.tikalk.pepper_assignment

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.tikalk.pepper_assignment.cities.CitiesFragment
import com.tikalk.pepper_assignment.forcast.ForecastFragment
import com.tikalk.pepper_assignment.settings.SettingsActivity
import javax.security.auth.login.LoginException

class MainActivity : AppCompatActivity() , CitiesFragment.CitiesEventListener{



    companion object {
        val TAG = "TAG_MainActivity"
        val ID = "ID"
        val NAME = "NAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().add(R.id.container, CitiesFragment()).commit()
        }
    }


    override fun onCityClicked(id: Int?, name: String?) {
        val forecast = ForecastFragment()
        val bundle = Bundle()

        id?.let { bundle.putInt(ID, it) }
        name?.let { bundle.putString(NAME, it) }
        forecast.arguments = bundle
        val tr = supportFragmentManager.beginTransaction().replace(R.id.container, forecast)
                .setCustomAnimations(android.R.animator.fade_out, android.R.animator.fade_in)
                .addToBackStack(null)

        tr.commit()
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                Log.i(TAG, "onOptionsItemSelected")
                val fm = supportFragmentManager
                if (fm.backStackEntryCount > 0) {
                    fm.popBackStack()
                    getSupportActionBar()?.setDisplayHomeAsUpEnabled(false)
                }
                return true
            }

            R.id.ic_settings ->{
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }}
