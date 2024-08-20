package ru.justneedcoffee.cryptolist.viewModel.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import ru.justneedcoffee.cryptolist.model.models.CurrencyDetail
import ru.justneedcoffee.cryptolist.utils.ResultWrapper
import ru.justneedcoffee.cryptolist.utils.resultToLiveData
import ru.justneedcoffee.cryptolist.viewModel.repositories.CurrencyRepository

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    private val currencyRepository = CurrencyRepository.getInstance()

    fun currencyLiveData(id: String): LiveData<CurrencyDetail?> = liveData(Dispatchers.IO) {
        val list = fetchData(id, currencyRepository)
        val result: LiveData<CurrencyDetail?> = resultToLiveData(list)
        emitSource(result)
    }

    private suspend fun fetchData(id: String, currencyRepository: CurrencyRepository): CurrencyDetail? {
        val detail: CurrencyDetail? = when (val receivedData = currencyRepository.getCurrencyByIdLiveData(id)) {
            is ResultWrapper.Success -> {
                receivedData.value
            }
            else -> {
                null
            }
        }
        return detail
    }
}