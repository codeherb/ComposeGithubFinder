package io.codeherb.composegithubfinder.domain.repository

import io.codeherb.composegithubfinder.data.model.GithubUsers
import io.codeherb.composegithubfinder.domain.model.GithubUserSearchParam
import kotlinx.coroutines.flow.Flow

interface GithubSearchRepository {
    fun getGithubUsers(queryParam: GithubUserSearchParam): Flow<GithubUsers>
}