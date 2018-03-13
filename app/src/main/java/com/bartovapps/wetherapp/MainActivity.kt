package com.bartovapps.wetherapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.bartovapps.wetherapp.forcast.ForecastFragment
import com.bartovapps.wetherapp.settings.SettingsActivity

class MainActivity : AppCompatActivity() {


    companion object {
        val TAG = "TAG_MainActivity"
        val ID = "ID"
        val NAME = "NAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "onCreate")
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.container, ForecastFragment()).commit()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy")
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

            R.id.ic_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
