package com.albertomier.marveldemo.data.network

import com.albertomier.marveldemo.data.model.HeroListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HeroApiClient {

    @GET("characters")
    suspend fun getHeroes(
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String,
        @Query("limit") limit: Int
    ): Response<HeroListResponse>

    @GET("characters")
    suspend fun getHeroesByName(
        @Query("nameStartsWith") nameStartsWith: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String
    ): Response<HeroListResponse>

    @GET("characters/{characterId}")
    suspend fun getHeroById(
        @Path("characterId") characterId: Int,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String
    ): Response<HeroListResponse>
}