package com.bartovapps.weather.model.local

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by motibartov on 14/01/2018.
 */
data class LocalTemp(

        @SerializedName("min")
        @Expose
        var min: Double? = null,

        @SerializedName("max")
        @Expose
        var max: Double? = null,

        @SerializedName("day")
        @Expose
        var day: Double? = null
)