package io.codeherb.composegithubfinder.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import io.codeherb.composegithubfinder.data.repository.GithubSearchRepositoryImpl
import io.codeherb.composegithubfinder.data.source.local.dao.GithubUserDao
import io.codeherb.composegithubfinder.data.source.remote.GithubService
import io.codeherb.composegithubfinder.domain.repository.GithubSearchRepository

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun provideGithubSearchRepository(
//        localGithubUserDao: GithubUserDao,
        githubService: GithubService
    ): GithubSearchRepository =
        GithubSearchRepositoryImpl(githubService)
}