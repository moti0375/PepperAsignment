package com.bartovapps.weather.forcast

import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bartovapps.weather.R
import com.bartovapps.weather.api.ApiModule
import com.bartovapps.weather.model.forecast.Forecast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.daily_list_item.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by motibartov on 14/01/2018.
 */
class DailyForcastAdapter : RecyclerView.Adapter<DailyForcastAdapter.ForecastViewHolder>() {

    var data = ArrayList<Forecast>()

    companion object {
        val TAG = "TAG_ForecastAdapter"
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.daily_list_item, parent, false)
        return ForecastViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }


    fun updateForecast(forecast: List<Forecast>?) {
        data.clear()
        if (forecast != null) {
            data.addAll(forecast)
        }
        notifyDataSetChanged()
    }

    class ForecastViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var sdf = SimpleDateFormat("h a", Locale.getDefault())
        val glideOptions = RequestOptions().fitCenter()

        fun bind(w: Forecast) {
            Log.i(TAG, "${w}")
            Glide.with(view.context).load(ApiModule.BASE_ICON_URL + w.weather[0].icon + ".png").apply(glideOptions).into(view.iv_daily_icon)
            val date = w.dt * 1000
//            val dateStr = sdf.format(date) + " - " + DateUtils.getRelativeTimeSpanString(date, System.currentTimeMillis(), DateUtils.DAY_IN_MILLIS, DateUtils.FORMAT_ABBREV_ALL)
            view.tv_daily_hour.text = sdf.format(date)
            view.tv_daily_temperature.text = view.context.getString(R.string.temperature, w.main.temp)
        }

    }

}