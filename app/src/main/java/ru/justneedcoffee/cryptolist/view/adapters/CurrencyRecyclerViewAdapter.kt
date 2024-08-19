package ru.justneedcoffee.cryptolist.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import ru.justneedcoffee.cryptolist.R
import ru.justneedcoffee.cryptolist.model.models.CurrencyItem

class CurrencyRecyclerViewAdapter(private val onClickListener: (CurrencyItem) -> Unit) :
    ListAdapter<CurrencyItem, CurrencyViewHolder>(CurrencyDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return CurrencyViewHolder(view, onClickListener)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.item = getItem(position)
    }
}

class CurrencyViewHolder(private val view: View, private val onClickListener: (CurrencyItem) -> Unit)
    : ViewHolder(view) {
    var item: CurrencyItem? = null
        set(value) {
            value?.let { currency ->
                field = currency
                view.setOnClickListener { onClickListener(currency) }
                Picasso.get().load(currency.image).into(view.findViewById<ImageView>(R.id.listCurrencyImage))
                view.findViewById<TextView>(R.id.listCurrencyFullName).text = currency.fullName
                view.findViewById<TextView>(R.id.listCurrencyShortName).text = currency.shortName.uppercase()
                view.findViewById<TextView>(R.id.listCurrencyPrice).text = currency.price
                view.findViewById<TextView>(R.id.listCurrencyDynamic).text = currency.priceChange
            }
        }
}

class CurrencyDiffCallback : DiffUtil.ItemCallback<CurrencyItem>() {
    override fun areItemsTheSame(oldItem: CurrencyItem, newItem: CurrencyItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CurrencyItem, newItem: CurrencyItem): Boolean {
        return oldItem == newItem
    }
}