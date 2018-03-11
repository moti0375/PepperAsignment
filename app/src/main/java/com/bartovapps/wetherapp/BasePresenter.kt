package com.bartovapps.wetherapp

/**
 * Created by motibartov on 14/01/2018.
 */
interface BasePresenter {
    fun attach(view: BaseView)
    fun detach()
}