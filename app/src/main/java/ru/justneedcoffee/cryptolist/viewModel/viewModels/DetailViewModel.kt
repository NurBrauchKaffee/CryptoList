package ru.justneedcoffee.cryptolist.viewModel.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import ru.justneedcoffee.cryptolist.model.models.CurrencyDetail
import ru.justneedcoffee.cryptolist.viewModel.repositories.CurrencyRepository

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    private val currencyRepository = CurrencyRepository.getInstance()

    fun currencyLiveData(id: String): LiveData<CurrencyDetail> = liveData(Dispatchers.IO) {
        emitSource(currencyRepository.getCurrencyByIdLiveData(id))
    }
}