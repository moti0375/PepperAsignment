package com.bartovapps.weather.data.source.local.type_converters

import android.arch.persistence.room.TypeConverter
import com.bartovapps.weather.model.daily_forecast.DailyTemp
import com.bartovapps.weather.model.forecast.Main
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MainTypeConverter{
    @TypeConverter
    fun fromMain(dailyTemp: Main?): String? {
        if (dailyTemp == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<Main>() {

        }.type
        return gson.toJson(dailyTemp, type)
    }

    @TypeConverter
    fun toDailyTemp(countryLangString: String?): Main? {
        if (countryLangString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<Main>() {

        }.type
        return gson.fromJson(countryLangString, type)
    }
}