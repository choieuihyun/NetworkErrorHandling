package com.enjoy_project.networkhandling


private fun <T> NetworkResult<T>.toNetworkResult(): T =
    (this as NetworkResult.Success).data

private fun <R> changeNetworkData(replaceData: R): NetworkResult<R> {
    return NetworkResult.Success(replaceData)
}

suspend fun <T, R> NetworkResult<T>.mapNetworkResult(getData: suspend (T) -> R): NetworkResult<R> {
    return changeNetworkData(getData(toNetworkResult()))
}



