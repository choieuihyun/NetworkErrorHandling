package com.enjoy_project.networkhandling

sealed class NetworkError {

    object Unknown : NetworkError()

    object Timeout : NetworkError()

    object InternalServer : NetworkError()

    object NoInternetConnection : NetworkError()

    object ServerNotFound : NetworkError()

    class BadRequest(val code: Int) : NetworkError()

}