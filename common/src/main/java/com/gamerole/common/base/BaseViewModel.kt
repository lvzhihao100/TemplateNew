package com.gamerole.common.base

import androidx.lifecycle.MutableLiveData

abstract class BaseViewModel : LiveCoroutinesViewModel() {
    open val error = MutableLiveData<Exception>()
    open val progress = MutableLiveData<Boolean>()
    fun showLoading(){
        progress.postValue(true)
    }
    fun hideLoading(){
        progress.postValue(false)
    }



    protected fun onError(throwable: Exception) {
        error.postValue(throwable)
    }

}