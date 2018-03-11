package com.bartovapps.wetherapp.model.global

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by motibartov on 15/01/2018.
 */
data class ApiResponseForGlobal(

    @SerializedName("cnt")
    @Expose
    var cnt: Int? = null,
    @SerializedName("list")
    @Expose
    var cityWeather: List<GlobalForecast>? = null

)