package com.tikalk.pepper_assignment.settings

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tikalk.pepper_assignment.R

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
}
