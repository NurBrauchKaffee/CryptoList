package ru.justneedcoffee.cryptolist.view.fragments

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
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
        val currencyDescriptionTitle: TextView = view.findViewById(R.id.currencyDescriptionTitle)
        val currencyCategoriesTitle: TextView = view.findViewById(R.id.currencyCategoriesTitle)
        val currencyDescription: TextView = view.findViewById(R.id.currencyDescription)
        val currencyCategories: TextView = view.findViewById(R.id.currencyCategories)
        val detailToolbarTitle: TextView = view.findViewById(R.id.detailToolbarTitle)

        val detailProgressBar: ProgressBar = view.findViewById(R.id.detailProgressBar)
        detailProgressBar.visibility = View.GONE

        val detailErrorMessage = view.findViewById<View>(R.id.detailErrorMessage)
        detailErrorMessage.visibility = View.GONE

        arguments?.let { bundle ->
            detailProgressBar.visibility = View.VISIBLE

            bundle.getString(CURRENCY_ID)?.let {
                viewModel.currencyLiveData(it).observe(viewLifecycleOwner) { currency ->
                    when (currency) {
                        null -> {
                            detailProgressBar.visibility = View.GONE
                            detailErrorMessage.visibility = View.VISIBLE
                        }
                        else -> {
                            Picasso.get().load(currency.image["large"]).into(currencyImage)
                            currencyDescriptionTitle.text = getString(R.string.description)
                            currencyCategoriesTitle.text = getString(R.string.categories)
                            currencyDescription.text = Html.fromHtml(
                                currency.description["en"],
                                Html.FROM_HTML_MODE_COMPACT
                            )
                            currencyCategories.text = currency.categories.joinToString()

                            detailToolbarTitle.text = currency.name
                            detailProgressBar.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }
}