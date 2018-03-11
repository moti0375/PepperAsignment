package com.bartovapps.wetherapp.model.local

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by motibartov on 14/01/2018.
 */
data class LocalForecast(
        @SerializedName("dt")
        @Expose
        var dt: Long,
        @SerializedName("temp")
        @Expose
        var temp: LocalTemp? = null,
        @SerializedName("weather")
        @Expose
        var weather: List<Weather>

)