package com.tikalk.pepper_assignment.forcast

import android.util.Log
import com.tikalk.pepper_assignment.BaseView
import com.tikalk.pepper_assignment.data.AppRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by motibartov on 14/01/2018.
 */
class ForecastPresenter(val repository: AppRepository) : ForecastContract.Presenter {

    companion object {
        val TAG = "TAG_ForecastPresenter"
    }

    lateinit var view: ForecastContract.View

    val compositeDisposable = CompositeDisposable()
    override fun setView(view: BaseView) {
        this.view = view as ForecastContract.View
    }


    override fun loadForecastForLocation(id: String, period: Int) {
        Log.i(TAG, "About to load data")

        val subscribe : Disposable = repository.getForecastForLocation(id, period)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({
            t -> view.showForecast(t)
        }, {
            e -> Log.e(TAG, "There was an exception: $e")
        })
        compositeDisposable.add(subscribe)
    }

}