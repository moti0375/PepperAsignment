package com.bartovapps.wetherapp.model.local

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by motibartov on 14/01/2018.
 */
data class ApiResponseForLocation(
                                  @SerializedName("city")
                                  @Expose
                                  var cityWeather: Location,

                                  @SerializedName("list")
                                  @Expose
                                  var forecast: List<LocalForecast>)
