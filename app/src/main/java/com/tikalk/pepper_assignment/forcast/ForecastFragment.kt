package com.tikalk.pepper_assignment.forcast

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tikalk.pepper_assignment.MainActivity
import com.tikalk.pepper_assignment.MyApplication

import com.tikalk.pepper_assignment.R
import com.tikalk.pepper_assignment.model.single.Forecast
import kotlinx.android.synthetic.main.forecast_fragment.*
import javax.inject.Inject


class ForecastFragment : Fragment(), ForecastContract.View{

    @Inject
    lateinit var presenter : ForecastPresenter

    @Inject
    lateinit var adapter : ForecastRecyclerAdapter

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        (activity?.application as MyApplication).component.injectForecast(this)
        val ab = activity?.actionBar
//        ab?.setDisplayHomeAsUpEnabled(true)
//        ab?.setDisplayShowHomeEnabled(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
         return inflater.inflate(R.layout.forecast_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvForecast.layoutManager = LinearLayoutManager(activity)
        rvForecast.adapter = adapter
        presenter.setView(this)
    }

    override fun onResume() {
        super.onResume()
        val id = arguments?.getInt(MainActivity.ID)
        val name : String? = arguments?.getString(MainActivity.NAME)
        presenter.loadForecastForLocation("$id", 5)
    }


    override fun showForecast(forecast: List<Forecast>) {
        adapter.updateForecast(forecast)
    }


}
