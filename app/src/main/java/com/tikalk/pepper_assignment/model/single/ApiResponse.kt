package com.tikalk.pepper_assignment.model.single

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by motibartov on 14/01/2018.
 */
data class ApiResponse (@SerializedName("city")
                        @Expose
                        var  city: City,
                        @SerializedName("list")
                        @Expose
                        var forecast: List<Forecast> )