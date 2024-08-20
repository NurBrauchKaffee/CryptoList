package ru.justneedcoffee.cryptolist.viewModel.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import ru.justneedcoffee.cryptolist.api.CurrencyEndpoints
import ru.justneedcoffee.cryptolist.api.RetrofitInstance
import ru.justneedcoffee.cryptolist.model.models.CurrencyDetail
import ru.justneedcoffee.cryptolist.model.models.CurrencyItem
import ru.justneedcoffee.cryptolist.utils.ResultWrapper

class CurrencyRepository {
    private val currencyCalls = RetrofitInstance.getInstance().create(CurrencyEndpoints::class.java)

    suspend fun getCurrencyListLiveData(currencyType: String): ResultWrapper<List<CurrencyItem>> {
        return safeApiCall { currencyCalls.getCurrencyList(currencyType) }
    }

    suspend fun getCurrencyByIdLiveData(currencyId: String): ResultWrapper<CurrencyDetail> {
        return safeApiCall { currencyCalls.getCurrencyById(currencyId) }
    }

    private suspend fun <T> safeApiCall(apiCall: suspend () -> T): ResultWrapper<T> {
        return withContext(Dispatchers.IO) {
            try {
                ResultWrapper.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        ResultWrapper.Error(false, throwable.code(), throwable.response()?.errorBody())
                    }
                    else -> {
                        ResultWrapper.Error(true)
                    }
                }
            }
        }
    }

    companion object {
        private var INSTANCE: CurrencyRepository? = null

        fun getInstance(): CurrencyRepository = INSTANCE ?: kotlin.run {
            INSTANCE = CurrencyRepository()
            INSTANCE!!
        }
    }
}