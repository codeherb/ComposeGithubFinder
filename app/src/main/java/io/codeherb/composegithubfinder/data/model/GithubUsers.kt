package io.codeherb.composegithubfinder.data.model

import com.google.gson.annotations.SerializedName

data class GithubUsers(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("incomplete_results")
    val incompleteResult: Boolean,
    val items: List<GithubUser>
)