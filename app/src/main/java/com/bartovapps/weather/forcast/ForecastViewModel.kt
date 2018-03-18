package com.bartovapps.weather.forcast

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import android.util.Log.i
import com.bartovapps.weather.data.AppRepository
import com.bartovapps.weather.model.daily_forecast.DailyForecast
import com.bartovapps.weather.model.forecast.Forecast
import com.bartovapps.weather.settings.PreferencesHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ForecastViewModel(val repository: AppRepository, val preferencesHelper: PreferencesHelper) : ViewModel() {

    companion object {
        val TAG = "ForecastViewModel"
    }
    val forecast  = MutableLiveData<List<Forecast>>()
    val dailyForecast = MutableLiveData<List<DailyForecast>>()
    private val compositeDisposable = CompositeDisposable()


    fun fetchForecast(location: String, period: Int, forceUpdate: Boolean) {

        i(TAG, "fetchForecast: ")
        if(forceUpdate){
            repository.forceUpdate()
        }

        val forecastDisposable = repository.fetchForecast(location, 24).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe({ t ->
                    i(TAG, "fetchForecast: onNext ")
                    forecast.value = t
        }, { e ->
            Log.e(TAG, "There was an exception: $e")
        })

        compositeDisposable.add(forecastDisposable)

        val dailyDisposable = repository.fetchDailyForecast(location, period).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe({ t ->
            i(TAG, "fetchDailyForecast: onNext ")
            dailyForecast.value = t
        }, { e ->
            Log.e(TAG, "There was an exception: $e")
        })

        compositeDisposable.add(dailyDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
