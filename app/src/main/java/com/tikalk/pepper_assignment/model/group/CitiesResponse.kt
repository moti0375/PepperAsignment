package com.tikalk.pepper_assignment.model.group

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by motibartov on 15/01/2018.
 */
data class CitiesResponse(

    @SerializedName("cnt")
    @Expose
    var cnt: Int? = null,
    @SerializedName("list")
    @Expose
    var city: List<City>? = null

)