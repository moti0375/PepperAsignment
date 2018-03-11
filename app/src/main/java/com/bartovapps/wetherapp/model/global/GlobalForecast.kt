package com.bartovapps.wetherapp.model.global

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.bartovapps.wetherapp.model.local.Weather


/**
 * Created by motibartov on 15/01/2018.
 */

data class GlobalForecast(

        @Expose
        @SerializedName("weather")
        var weather: List<Weather>,
        @SerializedName("id")
        @Expose
        var id: Int? = null,
        @SerializedName("name")
        @Expose
        var name: String? = null,
        @SerializedName("main")
        @Expose
        var globalTemp: GlobalTemp

)