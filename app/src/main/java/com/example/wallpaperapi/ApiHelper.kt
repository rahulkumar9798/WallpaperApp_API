package com.example.wallpaperapi

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiHelper {

//    @Headers("Authorization : dxOcX6Vnx8G4a59ltaDGGTLcnuHWzR6wWfT3aPovmD3MvdkjDXTRcl1G")


    @GET("curated")
    fun getcuratedWallpaper(
        @Header("Authorization") Authorization : String,
        @Query("query") query: String,
    ): Call<WallpaperModal>

    @GET("search")
    fun getSraechEallpaper(
        @Header("Authorization") Authorization : String,
        @Query("query") query: String,
        @Query("per_page") per_page : Int,
        @Query("color") color : String
    ) : Call<WallpaperModal>

    companion object{

        val BASE_URL = "https://api.pexels.com/v1/"

        fun getInstance() : ApiHelper{
            val retrofitClint = Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofitClint.create(ApiHelper::class.java)
        }
    }

}