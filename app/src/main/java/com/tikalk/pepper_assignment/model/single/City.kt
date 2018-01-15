package com.tikalk.pepper_assignment.model.single

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by motibartov on 15/01/2018.
 */
data class City(

        @SerializedName("id")
        @Expose
        var id: Int? = null,
        @SerializedName("name")
        @Expose
        var name: String? = null,
        @SerializedName("country")
        @Expose
        var country: String? = null
)