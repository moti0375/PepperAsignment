package com.bartovapps.weather.data.source.local.type_converters

import android.arch.persistence.room.TypeConverter
import com.bartovapps.weather.model.City
import com.bartovapps.weather.model.daily_forecast.DailyTemp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DailyTempConverter {

        @TypeConverter
        fun fromDailyTemp(dailyTemp: DailyTemp?): String? {
            if (dailyTemp == null) {
                return null
            }
            val gson = Gson()
            val type = object : TypeToken<DailyTemp>() {

            }.type
            return gson.toJson(dailyTemp, type)
        }

        @TypeConverter
        fun toDailyTemp(countryLangString: String?): DailyTemp? {
            if (countryLangString == null) {
                return null
            }
            val gson = Gson()
            val type = object : TypeToken<DailyTemp>() {

            }.type
            return gson.fromJson(countryLangString, type)
        }
}

