package ru.justneedcoffee.cryptolist.viewModel.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import ru.justneedcoffee.cryptolist.model.models.CurrencyItem
import ru.justneedcoffee.cryptolist.utils.ResultWrapper
import ru.justneedcoffee.cryptolist.utils.resultToLiveData
import ru.justneedcoffee.cryptolist.viewModel.repositories.CurrencyRepository

class ListViewModel(application: Application) : AndroidViewModel(application) {
    private val currencyRepository = CurrencyRepository.getInstance()

    fun currencyListLiveData(currencyType: String): LiveData<List<CurrencyItem>> = liveData(Dispatchers.IO) {
        val list = fetchData(currencyType, currencyRepository)
        val result: LiveData<List<CurrencyItem>> = resultToLiveData(list)
        emitSource(result)
    }

    private suspend fun fetchData(currencyType: String, currencyRepository: CurrencyRepository): List<CurrencyItem> {
        val list: List<CurrencyItem> = when (val receivedData = currencyRepository.getCurrencyListLiveData(currencyType)) {
            is ResultWrapper.Success -> {
                receivedData.value
            }
            else -> {
                emptyList()
            }
        }
        list.forEach { item -> item.priceType = currencyType }
        return list
    }

//    // for future updates (can pass ResultWrapper.Error to Fragment and show the exact reason of error)
//    fun handleError(): LiveData<ResultWrapper.Error> {}
}