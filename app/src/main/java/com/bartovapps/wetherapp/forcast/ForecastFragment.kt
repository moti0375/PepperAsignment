package com.bartovapps.wetherapp.forcast

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.preference.PreferenceManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bartovapps.wetherapp.MainActivity
import com.bartovapps.wetherapp.MyApplication

import com.bartovapps.wetherapp.R
import com.bartovapps.wetherapp.model.local.LocalForecast
import kotlinx.android.synthetic.main.forecast_fragment.*
import javax.inject.Inject


class ForecastFragment : Fragment(), ForecastContract.View {

    @Inject
    lateinit var presenter: ForecastPresenter

    @Inject
    lateinit var adapter: ForecastRecyclerAdapter

    lateinit var sharedPreferences: SharedPreferences

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        (activity?.application as MyApplication).component.injectForecast(this)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.forecast_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvForecast.layoutManager = LinearLayoutManager(activity)
        rvForecast.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        presenter.attach(this)

        val id = arguments?.getInt(MainActivity.ID)
        val name: String? = arguments?.getString(MainActivity.NAME)
        tvCityName.text = name
        val period = Integer.valueOf(sharedPreferences.getString(getString(R.string.forecastPeriodKey), "5"))
        presenter.loadForecastForLocation("$id", period)

    }

    override fun onPause() {
        super.onPause()
        presenter.detach()
    }

    override fun showForecast(forecast: List<LocalForecast>?) {
        adapter.updateForecast(forecast)
    }

}
