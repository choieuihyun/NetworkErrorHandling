package com.enjoy_project.networkhandling

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface RetrofitInterface {

    // MusicQuiz에서는 call이었는데 여기선 response로 업그레이드. 그 DietProject처럼 사용하려고 이렇게 함.
    @GET("user/all")
    fun userList(): Response<List<User>>

    @GET("user")
    fun getUserByName(@Query("name") name: String): Call<User>

}