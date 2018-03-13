package com.bartovapps.wetherapp.forcast

import android.arch.persistence.room.util.StringUtil
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.preference.PreferenceManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log.i
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.bartovapps.wetherapp.MyApplication

import com.bartovapps.wetherapp.R
import com.bartovapps.wetherapp.api.ApiModule
import com.bartovapps.wetherapp.api.ApiService
import com.bartovapps.wetherapp.model.global.GlobalForecast
import com.bartovapps.wetherapp.model.local.LocalForecast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.forecast_list_item.*
import kotlinx.android.synthetic.main.forecast_list_item.view.*
import kotlinx.android.synthetic.main.fragment_main_weather.*
import javax.inject.Inject


class ForecastFragment : Fragment(), ForecastContract.View {

    @Inject
    lateinit var presenter: ForecastPresenter



    @Inject
    lateinit var adapter: DailyForcastAdapter

    lateinit var sharedPreferences: SharedPreferences

    companion object {
        val TAG = "ForecastFragment"
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        (activity?.application as MyApplication).component.injectForecast(this)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spLocation.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                i(TAG, "onNothingSelected: nothing selected")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                val selectedItem: String = spLocation.selectedItem as String
                i(TAG, "onItemSelected: Item selected: ${spLocation.selectedItem}")
                val period = Integer.valueOf(sharedPreferences.getString(getString(R.string.forecastPeriodKey), "5"))
                presenter.loadForecastForLocation(ApiService.cities[selectedItem], period)
            }

        }
        rvDailyForecast.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rvDailyForecast.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        presenter.subscribe(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.unsubscribe()
    }

    override fun showForecast(forecast: List<LocalForecast>?) {
//        adapter.updateForecast(forecast)
        i(TAG, "Loaded forecast: $forecast")
        val today = forecast?.get(0)
        Glide.with(activity).load(ApiModule.BASE_ICON_URL + today?.weather?.get(0)?.icon + ".png").into(ivForecastIcon)
        tvDailyDescription.text = today?.weather?.get(0)?.description
//        tvTemperature.text = getString(R.string.forecast_temp, today?.temp?.min, today?.temp?.max )
        tvTemperature.text = getString(R.string.temperature, today?.temp?.day)
    }


    override fun showDailyForecast(dailyForecast: List<GlobalForecast>?) {
        i(TAG, "showDailyForecast: ${dailyForecast.toString()}")
        adapter.updateForecast(dailyForecast)
    }

}
