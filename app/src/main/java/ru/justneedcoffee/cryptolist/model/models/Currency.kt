package ru.justneedcoffee.cryptolist.model.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CurrencyItem(
    @Expose
    val id: String,
    @Expose
    @SerializedName("symbol")
    val shortName: String,
    @Expose
    @SerializedName("name")
    val fullName: String,
    @Expose
    val image: String,
    @SerializedName("current_price")
    @Expose
    val price: String,
    @SerializedName("price_change_percentage_24h")
    @Expose
    val priceChange: String,
    var priceType: String
)

data class CurrencyDetail(
    @Expose
    val name: String,
    @Expose
    val image: Map<String, String>,
    @Expose
    val description: Map<String, String>,
    @Expose
    val categories: List<String>
)
