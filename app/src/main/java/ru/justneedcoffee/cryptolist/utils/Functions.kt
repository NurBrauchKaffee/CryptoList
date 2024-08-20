package ru.justneedcoffee.cryptolist.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T> resultToLiveData(result: T): LiveData<T> {
    val liveData = MutableLiveData<T>()
    liveData.postValue(result)
    return liveData
}