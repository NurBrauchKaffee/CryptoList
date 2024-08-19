package ru.justneedcoffee.cryptolist.viewModel.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import ru.justneedcoffee.cryptolist.model.models.CurrencyItem
import ru.justneedcoffee.cryptolist.viewModel.repositories.CurrencyRepository

class ListViewModel(application: Application) : AndroidViewModel(application) {
    private val currencyRepository = CurrencyRepository.getInstance(application)

    val currencyListLiveData: LiveData<List<CurrencyItem>> = liveData(Dispatchers.IO) {
        emitSource(currencyRepository.getCurrencyListLiveData())
    }
}