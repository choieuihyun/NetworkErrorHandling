package com.enjoy_project.networkhandling

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitImpl {

    private val networkErrorHandler: NetworkErrorHandler = NetworkErrorHandlerImpl()

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

    // 인터페이스에서 Response로 반환하면 이렇게 사용하는데. 이게 권장되는 방법이 아니래. 예전꺼라 그런가?
/*    suspend fun getUserList() : NetworkResult<List<UserModel>?> {

        val response = RetrofitClient.userService.userList()

        return try {

            // 네트워크 연결 성공 시
            NetworkResult.Success(response.body()).mapNetworkResult { it ->

                it?.map {
                    it.toModel()
                }

            }
        } catch (e: Exception) {
            val errorType = networkErrorHandler.handleNetworkError(e)
            NetworkResult.Error(errorType)
        }


    }*/

    // 콜백 반환 타입을 인터페이스랑 똑같이 맞춰줘야지.
    fun getUserList(callback: (NetworkResult<List<UserModel>?>) -> Unit) {

        RetrofitClient.userService.userList()
            .enqueue(object : Callback<NetworkResult<List<UserEntity>?>> {

            override fun onResponse(
                call: Call<NetworkResult<List<UserEntity>?>>,
                response: Response<NetworkResult<List<UserEntity>?>>
            ) {

                CoroutineScope(Dispatchers.IO).launch {

                    val networkResult = response.body()?.mapNetworkResult { userList ->
                        userList?.map {
                            it.toModel()
                        }
                    }
                    if (networkResult != null) {
                        callback(networkResult)
                    }

                }
            }

            override fun onFailure(call: Call<NetworkResult<List<UserEntity>?>>, t: Throwable) {
            }

        })


    }


}


