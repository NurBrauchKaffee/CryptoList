package ru.justneedcoffee.cryptolist.api

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import ru.justneedcoffee.cryptolist.model.models.CurrencyDetail
import ru.justneedcoffee.cryptolist.model.models.CurrencyItem
import ru.justneedcoffee.cryptolist.utils.API_KEY
import ru.justneedcoffee.cryptolist.utils.PER_PAGE
import ru.justneedcoffee.cryptolist.utils.RESPONSE_TYPE

interface CurrencyEndpoints {
    @GET("api/v3/coins/markets")
    suspend fun getCurrencyList(@Query("vs_currency") currencyType: String,
                                @Query("per_page") perPage: Int = PER_PAGE,
                                @Header("x-cg-demo-api-key") key: String = API_KEY,
                                @Header("accept") type: String = RESPONSE_TYPE): List<CurrencyItem>

    @GET("api/v3/coins/{currencyId}")
    suspend fun getCurrencyById(@Path("currencyId") currencyId: String,
                                @Header("x-cg-demo-api-key") key: String = API_KEY,
                                @Header("accept") type: String = RESPONSE_TYPE): CurrencyDetail
}