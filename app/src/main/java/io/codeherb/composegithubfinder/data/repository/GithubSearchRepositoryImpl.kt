package io.codeherb.composegithubfinder.data.repository

import io.codeherb.composegithubfinder.data.model.GithubUsers
import io.codeherb.composegithubfinder.data.source.remote.GithubService
import io.codeherb.composegithubfinder.domain.model.GithubUserSearchParam
import io.codeherb.composegithubfinder.domain.repository.GithubSearchRepository
import kotlinx.coroutines.flow.Flow

class GithubSearchRepositoryImpl(
    private val githubService: GithubService
): GithubSearchRepository {
    override fun getGithubUsers(queryParam: GithubUserSearchParam): Flow<GithubUsers> =
        githubService.getUsers(queryParam.query, queryParam.page, queryParam.perPage)
}