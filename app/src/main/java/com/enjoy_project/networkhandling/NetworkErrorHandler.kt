package com.enjoy_project.networkhandling

interface NetworkErrorHandler {

    fun handleNetworkError(exception: Throwable) : NetworkError

}