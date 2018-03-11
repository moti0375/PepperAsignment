package com.bartovapps.wetherapp.cities


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bartovapps.wetherapp.MyApplication

import com.bartovapps.wetherapp.R
import com.bartovapps.wetherapp.model.global.GlobalForecast
import com.bartovapps.wetherapp.model.local.LocalForecast
import kotlinx.android.synthetic.main.cities_fragment.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class CitiesFragment : Fragment(), CitiesContract.View, CitiesRecyclerAdapter.CitiesAdapterClickListener, android.support.v4.app.FragmentManager.OnBackStackChangedListener{

    companion object {
        val TAG = "TAG_CitiesFragment"
    }
    @Inject
    lateinit var presenter : CitiesPresenter

    @Inject
    lateinit var adapter: CitiesRecyclerAdapter

    var mListener: CitiesEventListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is CitiesFragment.CitiesEventListener) {
            mListener = context
            (activity?.application as MyApplication).component.injectCities(this)
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.supportFragmentManager?.addOnBackStackChangedListener(this)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return  inflater.inflate(R.layout.cities_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvCities.layoutManager = GridLayoutManager(activity, 2)
        rvCities.adapter = adapter
        adapter.adapterClickListener = this
    }

    override fun onResume() {
        super.onResume()
        presenter.attach(this)
        presenter.loadCitiesForecast()
    }

    override fun onPause() {
        super.onPause()
        presenter.detach()
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun showCitiesForecast(forecast: List<GlobalForecast>) {
        adapter.updateForecast(forecast)
    }

    override fun onItemClicked(id: Int?, name: String?) {
        mListener?.onCityClicked(id, name)
    }

    override fun onBackStackChanged() {
        Log.i(TAG, "onBackStackChanged")
        if (activity?.supportFragmentManager!!.backStackEntryCount < 1) {
            (activity?.actionBar?.setDisplayHomeAsUpEnabled(false))
            (activity?.actionBar?.setDisplayShowHomeEnabled(false))
        }
    }


    interface CitiesEventListener{
        fun onCityClicked(id : Int?, name: String?)
    }
}
