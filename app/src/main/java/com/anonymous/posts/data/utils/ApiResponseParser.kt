package com.anonymous.posts.data.utils

import com.anonymous.posts.data.mapper.NetworkToDomainMapper
import com.anonymous.posts.domain.utils.NetworkResult
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.onSuccess

internal fun <I, O> convertToNetworkResultWithOtWrapper(
    data: ApiResponse<I>,
    mapper: NetworkToDomainMapper<I, O>
): O? {
    var result: O? = null

    data.onSuccess {
        result = mapper.mapTo(this.data)
    }.onError {
        result = null
    }.onException {
        result = null
    }

    return result
}

internal fun <I, O> convertToNetworkResult(
    data: ApiResponse<I>,
    mapper: NetworkToDomainMapper<I, O>
): NetworkResult<O> {
    var result: NetworkResult<O>? = null

    data.onSuccess {
        result = NetworkResult.Success(mapper.mapTo(this.data))
    }.onError {
        result = NetworkResult.Error()
    }.onException {
        result = NetworkResult.Error()
    }

    return result ?: NetworkResult.Error()
}
