package com.bartovapps.weather.settings

import android.content.SharedPreferences

class PreferencesHelper(val sharedPreferences: SharedPreferences){

    companion object {
        const val DEFAULT_PERIOD = "5" //It's a string as it is a String array in the preferences screen

        const val LOCATION_KEY = "location_key"
        const val PERIOD_KEY = "period_key"
        const val LAST_UPDATE_KEY = "last_updated_key"
    }

    fun getLocation() : String?{
        return sharedPreferences.getString(LOCATION_KEY, null)
    }

    fun saveLocation(location: String){
        sharedPreferences.edit().putString(LOCATION_KEY, location).apply()
    }

    fun getPeriod() : Int{
        return Integer.valueOf(sharedPreferences.getString(PERIOD_KEY, DEFAULT_PERIOD))
    }

    fun setPeriod(period : Int){
        sharedPreferences.edit().putString(PERIOD_KEY, "$period").apply()
    }

    fun getLastUpdated() : String?{
        return sharedPreferences.getString(LAST_UPDATE_KEY, null)
    }

    fun setLastUpdated(lastUpdated: String){
        sharedPreferences.edit().putString(LAST_UPDATE_KEY, lastUpdated).apply()
    }
}
