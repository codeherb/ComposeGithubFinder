package io.codeherb.composegithubfinder.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.codeherb.composegithubfinder.data.source.remote.GithubService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object GithubModule {

    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideGithubService(retrofit: Retrofit): GithubService =
        retrofit.create(GithubService::class.java)

//    @Provides
//    fun provideGithubUserDao(
//        @ApplicationContext context: Context
//    ): GithubUserDao =
//        MyAppDatabase.getInstance(context).getGithubUserDao()
}