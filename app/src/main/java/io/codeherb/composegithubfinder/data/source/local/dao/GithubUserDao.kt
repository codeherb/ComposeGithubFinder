package io.codeherb.composegithubfinder.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.codeherb.composegithubfinder.data.source.local.entity.GithubUserEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class GithubUserDao {
    @Query("SELECT * FROM github_users LIMIT :size OFFSET (:page - 1) * :size")
    abstract fun getGithubUsers(page: Int, size: Int): Flow<List<GithubUserEntity>>

    @Insert
    abstract suspend fun insertUser(user: GithubUserEntity)
}