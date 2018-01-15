package com.tikalk.pepper_assignment.model.group

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.tikalk.pepper_assignment.model.single.Weather


/**
 * Created by motibartov on 15/01/2018.
 */

data class City(

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
        var main: Main


)