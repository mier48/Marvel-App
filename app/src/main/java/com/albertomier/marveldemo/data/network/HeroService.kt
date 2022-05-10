package com.albertomier.marveldemo.data.network

import com.albertomier.marveldemo.core.Utils
import com.albertomier.marveldemo.data.model.HeroListResponse
import com.albertomier.marveldemo.data.model.HeroModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class HeroService @Inject constructor(private val api: HeroApiClient) {

    private val apiKey = "9b25e80468a87cad38e8382e7f9a4925"
    private val privateKey = "0bd4251f70b4f1c0cda3c35f9d8b904be5493fbf"

    suspend fun getHeroes(limit: Int): List<HeroModel> {
        return withContext(Dispatchers.IO) {
            val ts = getTs()
            val response: Response<HeroListResponse> =
                api.getHeroes(apiKey, getHash(ts), ts, limit)

            response.body()?.data?.results ?: emptyList()
        }
    }

    suspend fun getHeroesByName(name: String): List<HeroModel> {
        return withContext(Dispatchers.IO) {
            val ts = getTs()
            val response: Response<HeroListResponse> =
                api.getHeroesByName(name, apiKey, getHash(ts), ts)

            response.body()?.data?.results ?: emptyList()
        }
    }

    suspend fun getHeroById(id: Int): List<HeroModel> {
        return withContext(Dispatchers.IO) {
            val ts = getTs()
            val response: Response<HeroListResponse> = api.getHeroById(id, apiKey, getHash(ts), ts)

            response.body()?.data?.results ?: emptyList()
        }
    }

    private fun getTs(): String {
        return java.sql.Timestamp(System.currentTimeMillis()).toString()
    }

    private fun getHash(ts: String): String {
        return Utils.md5(ts + privateKey + apiKey)
    }
}