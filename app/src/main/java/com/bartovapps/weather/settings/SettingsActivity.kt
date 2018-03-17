package com.bartovapps.weather.settings

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bartovapps.weather.R
import android.support.v4.app.NavUtils
import android.view.MenuItem


class SettingsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prefs)

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayUseLogoEnabled(true)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.container, PreferencesFragment())
        transaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
        // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
