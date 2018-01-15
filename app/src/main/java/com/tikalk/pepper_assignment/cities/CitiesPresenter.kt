package com.tikalk.pepper_assignment.cities

import android.util.Log
import com.tikalk.pepper_assignment.BaseView
import com.tikalk.pepper_assignment.data.AppRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by motibartov on 15/01/2018.
 */
class CitiesPresenter (val repository: AppRepository) : CitiesContract.Presenter{


    lateinit var view : CitiesContract.View
    val compositeDisposable = CompositeDisposable()

    companion object {
        val TAG = "TAG_CitiesPresenter"
        val cities = "2643743,293396,5128581,2800866,3128760,2988507,1850147,1816670,2147714,3432043,4164138,6173331,524901,1609350,993800,2464470,1701668"

    }


    override fun setView(view: BaseView) {
        this.view = view as CitiesContract.View
    }

    override fun loadCitiesForecast() {
        Log.i(TAG, "loadCitiesForecast")
        repository.getGroupWeather(cities)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {it -> view.showCitiesForecast(it)},
                        {e -> Log.e(TAG, "There was an exception: " , e)})
    }

}