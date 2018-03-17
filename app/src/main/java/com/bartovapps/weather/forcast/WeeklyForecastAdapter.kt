package com.bartovapps.weather.forcast

import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bartovapps.weather.R
import com.bartovapps.weather.api.ApiModule
import com.bartovapps.weather.model.local.LocalForecast
import kotlinx.android.synthetic.main.forecast_list_item.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by motibartov on 14/01/2018.
 */
class WeeklyForecastAdapter : RecyclerView.Adapter<WeeklyForecastAdapter.ForecastViewHolder>() {

    var data = ArrayList<LocalForecast>()

    companion object {
        val TAG = "TAG_ForecastAdapter"
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.forecast_list_item, parent, false)
        return ForecastViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }


    fun updateForecast(forecast: List<LocalForecast>?) {
        data.clear()
        if (forecast != null) {
            data.addAll(forecast)
            data.removeAt(0) //Remove the first day
        }

        notifyDataSetChanged()
    }

    class ForecastViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var sdf = SimpleDateFormat("E", Locale.getDefault())
        val glideOptions = RequestOptions().fitCenter()

        fun bind(forecast: LocalForecast) {
            Log.i(TAG, "${forecast}")
            Glide.with(view.context).load(ApiModule.BASE_ICON_URL + forecast.weather[0].icon + ".png").apply(glideOptions).into(view.ivItemIcon)
            view.tvMinTemp.text = view.context.getString(R.string.temperature, forecast.temp?.min)
            view.tvMaxTemp.text = view.context.getString(R.string.temperature, forecast.temp?.max)
            val date = forecast.dt * 1000
            val dateStr = sdf.format(date) + " - " + DateUtils.getRelativeTimeSpanString(date, System.currentTimeMillis(), DateUtils.DAY_IN_MILLIS, DateUtils.FORMAT_ABBREV_ALL)
            view.tvDay.text = dateStr
        }

    }

}