package com.tikalk.pepper_assignment.model.single

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by motibartov on 14/01/2018.
 */
data class Temp(

        @SerializedName("min")
        @Expose
        var min: Double? = null,
        @SerializedName("max")
        @Expose
        var max: Double? = null
)