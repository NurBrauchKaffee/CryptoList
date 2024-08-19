package ru.justneedcoffee.cryptolist.viewModel.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.justneedcoffee.cryptolist.api.CurrencyEndpoints
import ru.justneedcoffee.cryptolist.api.RetrofitInstance
import ru.justneedcoffee.cryptolist.model.models.CurrencyDetail
import ru.justneedcoffee.cryptolist.model.models.CurrencyItem

class CurrencyRepository private constructor(application: Application) {
    private val currencyCalls = RetrofitInstance.getInstance().create(CurrencyEndpoints::class.java)

    suspend fun getCurrencyListLiveData(): LiveData<List<CurrencyItem>> {
        val currencyList = MutableLiveData<List<CurrencyItem>>()
        currencyList.postValue(currencyCalls.getCurrencyList())
        return currencyList
    }

    suspend fun getCurrencyByIdLiveData(currencyId: String): LiveData<CurrencyDetail> {
        val currencyData = MutableLiveData<CurrencyDetail>()
        currencyData.postValue(currencyCalls.getCurrencyById(currencyId))
        return currencyData
    }

    companion object {
        private var INSTANCE: CurrencyRepository? = null

        fun getInstance(application: Application): CurrencyRepository = INSTANCE ?: kotlin.run {
            INSTANCE = CurrencyRepository(application = application)
            INSTANCE!!
        }
    }
}