package com.bartovapps.weather.settings


import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.preference.ListPreference
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import android.support.v7.preference.PreferenceGroup
import android.util.Log
import com.bartovapps.weather.R

class PreferencesFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferences = preferenceScreen.sharedPreferences
        preferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
    }


    override fun onResume() {
        super.onResume()
        (0 until preferenceScreen.preferenceCount)
                .map { preferenceScreen.getPreference(it) }
                .forEach {
                    if (it is PreferenceGroup) {
                        for (j in 0 until it.preferenceCount) {
                            updatePreferenceSummary(it.getPreference(j))
                        }
                    } else {
                        updatePreferenceSummary(it)
                    }
                }
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {

        Log.i(TAG, "onSharedPreferenceChanged")
        updatePreferenceSummary(findPreference(key))
    }

    private fun updatePreferenceSummary(preference: Preference) {
        if (preference is ListPreference) {
            preference.summary = preference.entry
        }
    }

    override fun onStop() {
        super.onStop()
        preferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {

        private val TAG = "TAG_" + PreferencesFragment::class.java.simpleName
    }
}

