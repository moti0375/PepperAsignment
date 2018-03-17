package com.bartovapps.weather.forcast

import android.util.Log
import android.util.Log.i
import com.bartovapps.weather.BaseView
import com.bartovapps.weather.data.AppRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by motibartov on 14/01/2018.
 */
class ForecastPresenter(val repository: AppRepository) : ForecastContract.Presenter {


    override fun onLocationSelected(location: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    companion object {
        val TAG = "ForecastPresenter"
    }

    var view: ForecastContract.View? = null
    var id : String? = ""

    val compositeDisposable = CompositeDisposable()
    override fun subscribe(view: BaseView) {
        this.view = view as ForecastContract.View
    }

    override fun unsubscribe() {
        Log.i(TAG, "unsubscribe")
        view = null
        compositeDisposable.clear()
    }

    override fun loadForecastForLocation(id: String?, period: Int) {

        this.id = id

        i(TAG, "loadForecastForLocation: $id")
        if(id != null){
            val subscribe : Disposable = repository.getLocalWeather(id, period)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe({
                t -> view?.showForecast(t)
            }, {
                e -> Log.e(TAG, "There was an exception: $e")
            })

            compositeDisposable.add(subscribe)

            val daily : Disposable = repository.getGlobalWeather(id, 24).
                    subscribeOn(Schedulers.io()).
                    observeOn(AndroidSchedulers.mainThread()).
                    subscribe({ t-> view?.showDailyForecast(t)},
                            {e -> Log.e(TAG, "There was an exception: $e")})

            compositeDisposable.add(daily)
        }

    }

}