package dev.olaore.realestateonmars.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

//  Base URL
private const val BASE_URL = "https://mars.udacity.com/"

//  Retrofit Instance
private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(ScalarsConverterFactory.create())
    .build()

//  Retrofit Service API
interface MarsApiService {

    @GET("realestate")
    fun getProperties(): Call<String>

}

object MarsApi {

    val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }

}

