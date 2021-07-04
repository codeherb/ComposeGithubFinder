package io.codeherb.composegithubfinder.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "github_users",
    indices = [Index("name")]
)
data class GithubUserEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name= "avatar_url") val avatarUrl: String,
    val name: String,
    val description: String,
    @ColumnInfo(name= "stargazers_count") val stargazersCount: Int,
    @ColumnInfo(name= "is_like") val isLike: Boolean
)