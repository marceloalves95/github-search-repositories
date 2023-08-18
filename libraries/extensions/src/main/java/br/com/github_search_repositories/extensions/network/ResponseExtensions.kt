package br.com.github_search_repositories.extensions.network

import br.com.github_search_repositories.network.model.ServiceState
import okhttp3.ResponseBody
import retrofit2.Response

fun <T : Any> Response<T>.parseResponse(
    body: T? = this.body(),
    httpCode: Int = this.code(),
    errorBody: ResponseBody? = this.errorBody(),
    message: String = this.message()
): ServiceState<T> {
    return if (isSuccessful && body != null) {
        ServiceState.Success(
            data = body,
            httpCode = httpCode
        )
    } else {
        ServiceState.Error(
            response = body(),
            message = message,
            httpCode = httpCode,
            errorBody = errorBody,
            exception = Throwable()
        )
    }
}

fun <T : Any> ServiceState<T>.toResponse(): T = when (this) {
    is ServiceState.Success -> data
    is ServiceState.Error -> throw exception
}