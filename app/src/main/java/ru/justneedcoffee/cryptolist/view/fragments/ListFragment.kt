package ru.justneedcoffee.cryptolist.view.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.justneedcoffee.cryptolist.R
import ru.justneedcoffee.cryptolist.utils.CURRENCY_ID
import ru.justneedcoffee.cryptolist.viewModel.viewModels.ListViewModel
import ru.justneedcoffee.cryptolist.view.adapters.CurrencyRecyclerViewAdapter

class ListFragment : Fragment(R.layout.list_fragment) {

    private val viewModel by viewModels<ListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listCrypto: RecyclerView = view.findViewById(R.id.listCrypto)

        listCrypto.apply {
            this.layoutManager = LinearLayoutManager(this.context)
            this.adapter = CurrencyRecyclerViewAdapter { currency ->
                val bundle = bundleOf(CURRENCY_ID to currency.id)
                findNavController().navigate(R.id.action_listFragment_to_detailFragment, bundle)
            }
        }

        viewModel.currencyListLiveData.observe(viewLifecycleOwner) { currencyList ->
            (listCrypto.adapter as CurrencyRecyclerViewAdapter).submitList(currencyList)
        }
    }
}