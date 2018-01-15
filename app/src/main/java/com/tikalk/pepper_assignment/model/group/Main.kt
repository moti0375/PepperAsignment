package com.tikalk.pepper_assignment.model.group

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by motibartov on 15/01/2018.
 */
data class Main (

    @SerializedName("temp_min")
    @Expose
    var tempMin: Int? = null,
    @SerializedName("temp_max")
    @Expose
    var tempMax: Int? = null

)