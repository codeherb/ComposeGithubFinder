package io.codeherb.composegithubfinder.domain.model

data class GithubUserSearchParam(
    val query: String,
    val page: Int = 1,
    val perPage: Int = 30
)

fun GithubUserSearchParam.nextPage(): GithubUserSearchParam =
    this.copy(page = page + 1)

fun GithubUserSearchParam.prevPage(): GithubUserSearchParam =
    this.copy(page = (page - 1).takeIf { it > 0 } ?: 1)