package com.enjoy_project.networkhandling

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DataApi {

    @GET("/todos/{id}")
    fun getData(@Path("id") id: Int): Call<DataResponse>

}