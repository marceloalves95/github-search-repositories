package br.com.github_search_repositories.network.event

sealed class Event<out T>{
    data class Data<T>(val data: T) : Event<T>()
    data class Error(val error: Throwable) : Event<Nothing>()
    object Loading: Event<Nothing>()

    companion object{
        fun <T> data(data: T): Event<T> = Data(data)
        fun <T> error(error:Throwable): Event<T> = Error(error)
        fun <T> loading(isLoading: Boolean): Event<T> = Loading
    }
}