package com.bartovapps.weather.forcast

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.preference.PreferenceManager
import android.support.v7.widget.LinearLayoutManager
import android.text.format.DateUtils
import android.util.Log.i
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.bartovapps.weather.MyApplication

import com.bartovapps.weather.R
import com.bartovapps.weather.api.ApiModule
import com.bartovapps.weather.api.ApiService
import com.bartovapps.weather.model.forecast.Forecast
import com.bartovapps.weather.model.daily_forecast.DailyForecast
import com.bartovapps.weather.settings.PreferencesHelper
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_main_weather.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class ForecastFragment : Fragment() {

    @Inject
    lateinit var dailyAdapter: DailyForecastAdapter
    @Inject
    lateinit var forecastAdapter: ForecastAdapter
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject lateinit var preferencesHelper: PreferencesHelper

    @Inject lateinit var sdf : SimpleDateFormat

    //ViewModelFactory required to provide the ViewModel dependencies
    @Inject lateinit var viewModelFactory: ForecastViewModelFactory

    lateinit var viewModel: ForecastViewModel

    companion object {
        val TAG = "ForecastFragment"
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        (activity?.application as MyApplication).component.injectForecast(this)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ForecastViewModel::class.java)
        viewModel.forecast.observe(this as LifecycleOwner, Observer<List<Forecast>> { t ->
            i(TAG, "forecast: onChanged")
            showForecast(t)
        })

        viewModel.dailyForecast.observe(this as LifecycleOwner, Observer<List<DailyForecast>> { t ->
            i(TAG, "dailyForecast: onChanged")
            showDailyForecast(t)
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spLocation.setSelection(getSelectionPosition(preferencesHelper.getLocation()))
        spLocation.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                val selectedItem: String? = ApiService.cities[spLocation.selectedItem as String]
                val period = preferencesHelper.getPeriod()

                if(selectedItem != null){
                    viewModel.fetchForecast(selectedItem, period, forecastDirty(spLocation.selectedItem as String))
                }
            }
        }

        //Setup the adapters
        rvDailyForecast.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rvDailyForecast.adapter = dailyAdapter
        rvWeeklyForecast.layoutManager = LinearLayoutManager(activity)
        rvWeeklyForecast.adapter = forecastAdapter
    }

     fun showDailyForecast(dailyForecast: List<DailyForecast>?) {
        val today = dailyForecast?.get(0)

        if(today != null){
            val date = today.dt * 1000  //The date value from the OpenWeather API is in unix format so it should be multiply by 1000
            tvTodayDate.text = DateUtils.getRelativeTimeSpanString(date, System.currentTimeMillis(), DateUtils.DAY_IN_MILLIS, DateUtils.FORMAT_ABBREV_ALL)
        }

        Glide.with(activity).load(ApiModule.BASE_ICON_URL + today?.weather?.get(0)?.icon + ".png").into(ivForecastIcon)
        tvDailyDescription.text = today?.weather?.get(0)?.description
        tvTemperature.text = getString(R.string.temperature, today?.temp?.day)
        forecastAdapter.updateForecast(dailyForecast)

    }


     fun showForecast(forecast: List<Forecast>?) {
        dailyAdapter.updateForecast(forecast)
    }


    /**
     * This method checks if the forecast is dirty and should be updated
     * The forecast is dirty if the selected location different than the last forecast location or if the current
     * forecast is old.
     * Both the last location and last updated saved in app shared preferences.
     *
     * @param selectedLocation selected location for forecast
     * @return if the forecast is dirty, it should be updated
     */
    private fun forecastDirty(selectedLocation: String) : Boolean{
        val location = preferencesHelper.getLocation()
        val lastUpdated = preferencesHelper.getLastUpdated()
        val today = sdf.format(Date(System.currentTimeMillis()))
        preferencesHelper.setLastUpdated(today)
        preferencesHelper.saveLocation(selectedLocation)
        val dirty = lastUpdated == null || lastUpdated !=  today || location != selectedLocation
        i(TAG, "lastUpdated: $lastUpdated, today: $today, selectedLocation: $selectedLocation, savedLocation: $location is dirty: $dirty")
        return dirty
    }


    private fun getSelectionPosition(selection: String?) : Int{
        val locations: List<String> = activity?.resources?.getStringArray(R.array.locations)?.asList()
                ?: return 0

        return locations?.indexOf(selection)
    }
}
