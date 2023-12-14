package com.enjoy_project.networkhandling

import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


class NetworkErrorHandlerImpl : NetworkErrorHandler {

    override fun handleNetworkError(exception: Throwable): NetworkError {

        return when (exception) {

            is SocketTimeoutException -> NetworkError.Timeout

            is HttpException -> {

                when(exception.code()) {

                    in 500..599 -> NetworkError.InternalServer

                    // 너무 많아서 그냥 범위로 설정
                    in 400.. 499 -> {

                        val code = exception.code()
                        NetworkError.BadRequest(code)

                    }

                    else -> NetworkError.Unknown

                }

            }

            is IOException -> {

                when(exception) {

                    is UnknownHostException -> NetworkError.ServerNotFound

                    is ConnectException -> NetworkError.NoInternetConnection

                    else ->  NetworkError.Unknown

                }

            }

            else -> NetworkError.Unknown

        }

    }


}