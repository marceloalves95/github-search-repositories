package br.com.github_search_repositories.core.di

import br.com.github_search_repositories.data.BASE_URL
import br.com.github_search_repositories.data.GithubSearchRepositoryImpl
import br.com.github_search_repositories.data.api.GithubSearchRepositoryApi
import br.com.github_search_repositories.domain.repository.GithubSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object GithubSearchRepositoriesModule {

    @Singleton
    @Provides
    fun createService(): GithubSearchRepositoryApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(createHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubSearchRepositoryApi::class.java)
    }

    private fun createHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addNetworkInterceptor(httpLoggingInterceptor)
            .connectTimeout(1, TimeUnit.SECONDS)
            .readTimeout(40, TimeUnit.SECONDS)
            .writeTimeout(40, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRepository(githubSearchRepositoryApi: GithubSearchRepositoryApi): GithubSearchRepository =
        GithubSearchRepositoryImpl(githubSearchRepositoryApi)

}