package com.enjoy_project.networkhandling

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface RetrofitInterface {

    // MusicQuiz에서는 call이었는데 여기선 response로 업그레이드 했었다가 다시 바꿈. 그 DietProject처럼 사용하려고 이렇게 함.

//    Response<List<UserEntity>>를 직접 사용하는 것은 Retrofit에서 권장되는 방식이 아닙니다. 보통은 간단한 Call<List<UserEntity>>를 사용하고,
//    Retrofit이 내부적으로 Response를 처리하도록 하는 것이 일반적입니다.
    @GET("user/all")
    fun userList(): Call<NetworkResult<List<UserEntity>?>>

//    @GET("user/all")
//    fun userList(): Response<List<UserEntity>>

    @GET("user")
    fun getUserByName(@Query("name") name: String): Call<UserEntity>

}