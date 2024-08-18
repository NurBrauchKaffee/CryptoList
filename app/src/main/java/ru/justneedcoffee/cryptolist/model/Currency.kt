package ru.justneedcoffee.cryptolist.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Currency(
    @Expose
    val id: String,
    @Expose
    val symbol: String,
    @Expose
    val name: String,
    @Expose
    val image: String,
    @SerializedName("current_price")
    @Expose
    val price: Int,
    @SerializedName("price_change_percentage_24h")
    @Expose
    val priceChange: Int,
    @Expose
    val description: Map<String, String>,
    @Expose
    val categories: List<String>
)