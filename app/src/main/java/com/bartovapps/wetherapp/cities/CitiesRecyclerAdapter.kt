package com.bartovapps.wetherapp.cities

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bartovapps.wetherapp.R
import com.bartovapps.wetherapp.api.ApiModule
import com.bartovapps.wetherapp.model.global.GlobalForecast
import kotlinx.android.synthetic.main.citi_list_item.view.*

/**
 * Created by motibartov on 14/01/2018.
 */
class CitiesRecyclerAdapter : RecyclerView.Adapter<CitiesRecyclerAdapter.CitiesViewHolder>(){

    var data = ArrayList<GlobalForecast>()
    var adapterClickListener : CitiesRecyclerAdapter.CitiesAdapterClickListener? = null


    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) {
        holder.bind(data[position])
        holder.view.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                adapterClickListener?.onItemClicked(data[position].id, data[position].name)
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.citi_list_item, parent, false)

        return CitiesViewHolder(view)
    }


    override fun getItemCount(): Int {
        return data.size
    }

    fun updateForecast(city: List<GlobalForecast>){
        data.clear()
        data.addAll(city)
        notifyDataSetChanged()
    }


    fun setClickListener(adapterClickListener: CitiesAdapterClickListener){
        this.adapterClickListener = adapterClickListener
    }

    class CitiesViewHolder(val view: View) : RecyclerView.ViewHolder(view){

        fun bind(city: GlobalForecast){
            val glideOptions = RequestOptions()
            glideOptions.fitCenter()
            Glide.with(view.context).load(ApiModule.BASE_ICON_URL + city.weather[0].icon + ".png").apply(glideOptions).into(view.ivCityItem)
            view.tvCityName.text = city.name
            view.tvTemp.text = "${city.globalTemp.tempMin}℃ - ${city.globalTemp.tempMax}℃"
            view.tvMain.text = city.weather[0].main

        }

    }

    interface CitiesAdapterClickListener{
        fun onItemClicked(id: Int?, name: String?)
    }

}