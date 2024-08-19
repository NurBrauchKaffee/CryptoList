package ru.justneedcoffee.cryptolist.view.fragments

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import ru.justneedcoffee.cryptolist.R
import ru.justneedcoffee.cryptolist.utils.CURRENCY_ID
import ru.justneedcoffee.cryptolist.viewModel.viewModels.DetailViewModel

class DetailFragment : Fragment(R.layout.detail_fragment) {

    private val viewModel by viewModels<DetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnBack: ImageButton = view.findViewById(R.id.arrow_back)
        btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        val currencyImage: ImageView = view.findViewById(R.id.currencyImage)
        val currencyDescription: TextView = view.findViewById(R.id.currencyDescription)
        val currencyCategories: TextView = view.findViewById(R.id.currencyCategories)

        arguments?.let { bundle ->
            bundle.getString(CURRENCY_ID)?.let {
                viewModel.currencyLiveData(it).observe(viewLifecycleOwner) { currency ->
                    Picasso.get().load(currency.image["large"]).into(currencyImage)
                    currencyDescription.text = Html.fromHtml(currency.description["en"],
                        Html.FROM_HTML_MODE_COMPACT)
                    currencyCategories.text = currency.categories.joinToString()
                }
            }
        }
    }
}