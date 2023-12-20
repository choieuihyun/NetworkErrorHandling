package com.enjoy_project.networkhandling

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitImpl(private val retrofitInterface: RetrofitInterface,
                   private val networkErrorHandler: NetworkErrorHandler) {

/*    fun getUserList() {

        RetrofitClient.userService.userList().enqueue(object : Callback<List<User>> {

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if(!response.isSuccessful) {

                } else {

                    val user = response.body()

                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.e("retrofitImpl", "연결 실패")
                Log.e("retrofitImpl", t.toString())
            }

        })

    }*/

    fun getUserList() : NetworkResult<List<User>?> {

        val response = retrofitInterface.userList()

        return try {
            NetworkResult.Success(response.body())
        } catch (e: Exception) {
            val errorType = networkErrorHandler.handleNetworkError(e)
            NetworkResult.Error(errorType)
        }


    }

}

