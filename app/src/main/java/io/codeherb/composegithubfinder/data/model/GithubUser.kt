package io.codeherb.composegithubfinder.data.model

import com.google.gson.annotations.SerializedName

data class GithubUser(
    val id: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("login")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
)