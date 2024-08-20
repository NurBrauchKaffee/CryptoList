package ru.justneedcoffee.cryptolist.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import ru.justneedcoffee.cryptolist.R
import ru.justneedcoffee.cryptolist.utils.CURRENCY_ID
import ru.justneedcoffee.cryptolist.view.adapters.CurrencyRecyclerViewAdapter
import ru.justneedcoffee.cryptolist.viewModel.viewModels.ListViewModel

class ListFragment : Fragment(R.layout.list_fragment) {

    private val viewModel by viewModels<ListViewModel>()
    private lateinit var currencyType: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listCrypto: RecyclerView = view.findViewById(R.id.listCrypto)
        val chipGroup: ChipGroup = view.findViewById(R.id.listChipGroup)

        val listProgressBar: ProgressBar = view.findViewById(R.id.listProgressBar)
        listProgressBar.visibility = View.GONE

        val listErrorMessage = view.findViewById<View>(R.id.listErrorMessage)
        listErrorMessage.visibility = View.GONE

        val errorButton: Button = view.findViewById(R.id.errorButton)

        listCrypto.apply {
            this.layoutManager = LinearLayoutManager(this.context)
            this.adapter = CurrencyRecyclerViewAdapter { currency ->
                val bundle = bundleOf(CURRENCY_ID to currency.id)
                findNavController().navigate(R.id.action_listFragment_to_detailFragment, bundle)
            }
        }

        errorButton.setOnClickListener {
            listProgressBar.visibility = View.VISIBLE
            loadList(currencyType, listProgressBar, listErrorMessage, listCrypto)
        }

        chipGroup.setOnCheckedChangeListener { group, checkedId ->
            (listCrypto.adapter as CurrencyRecyclerViewAdapter).submitList(emptyList())
            listErrorMessage.visibility = View.GONE
            listProgressBar.visibility = View.VISIBLE

            currencyType = if (checkedId == View.NO_ID) ""
            else group.findViewById<Chip>(checkedId).text.toString().lowercase()

            currencyType.let { type ->
                if (type.isEmpty()) {
                    listProgressBar.visibility = View.GONE
                    (listCrypto.adapter as CurrencyRecyclerViewAdapter).submitList(emptyList())
                } else {
                    loadList(type, listProgressBar, listErrorMessage, listCrypto)
                }
            }
        }
    }

    private fun loadList(type: String, listProgressBar: ProgressBar, listErrorMessage: View, listCrypto: RecyclerView) {
        viewModel.currencyListLiveData(type).observe(viewLifecycleOwner) { list ->
            if (list.isEmpty()) {
                listProgressBar.visibility = View.GONE
                listErrorMessage.visibility = View.VISIBLE
            } else {
                (listCrypto.adapter as CurrencyRecyclerViewAdapter).submitList(list)
                listProgressBar.visibility = View.GONE
                listErrorMessage.visibility = View.GONE
            }
        }
    }
}