package com.bartovapps.weather.data.source.local.type_converters

import android.arch.persistence.room.TypeConverter
import com.bartovapps.weather.model.Weather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class WeatherTypeConverter {

    @TypeConverter
    fun fromWeatherList(weatherList: List<Weather>?): String? {
        if (weatherList == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<Weather>>() {

        }.type
        return gson.toJson(weatherList, type)
    }

    @TypeConverter
    fun toWeatherList(weatherListString: String?): List<Weather>? {
        if (weatherListString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<Weather>>() {

        }.type
        return gson.fromJson(weatherListString, type)
    }
}