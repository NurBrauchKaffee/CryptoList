package ru.justneedcoffee.cryptolist.viewModel.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.justneedcoffee.cryptolist.api.CurrencyEndpoints
import ru.justneedcoffee.cryptolist.api.RetrofitInstance
import ru.justneedcoffee.cryptolist.model.models.CurrencyDetail
import ru.justneedcoffee.cryptolist.model.models.CurrencyItem

class CurrencyRepository {
    private val currencyCalls = RetrofitInstance.getInstance().create(CurrencyEndpoints::class.java)

    suspend fun getCurrencyListLiveData(currencyType: String): LiveData<List<CurrencyItem>> {
        val receivedList = currencyCalls.getCurrencyList(currencyType)
        receivedList.forEach { item ->
            item.priceType = currencyType
        }
        val currencyList = MutableLiveData<List<CurrencyItem>>()
        currencyList.postValue(receivedList)
        return currencyList
    }

    suspend fun getCurrencyByIdLiveData(currencyId: String): LiveData<CurrencyDetail> {
        val currencyData = MutableLiveData<CurrencyDetail>()
        currencyData.postValue(currencyCalls.getCurrencyById(currencyId))
        return currencyData
    }

    companion object {
        private var INSTANCE: CurrencyRepository? = null

        fun getInstance(): CurrencyRepository = INSTANCE ?: kotlin.run {
            INSTANCE = CurrencyRepository()
            INSTANCE!!
        }
    }
}