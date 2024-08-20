package ru.justneedcoffee.cryptolist.utils

import okhttp3.ResponseBody

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class Error(val isNetworkError: Boolean?,
                     val code: Int? = null,
                     val error: ResponseBody? = null): ResultWrapper<Nothing>()
}