package io.codeherb.composegithubfinder.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.codeherb.composegithubfinder.data.source.local.dao.GithubUserDao
import io.codeherb.composegithubfinder.data.source.local.entity.GithubUserEntity

@Database(entities = [GithubUserEntity::class], version = 1)
abstract class MyAppDatabase: RoomDatabase() {

    abstract fun getGithubUserDao() : GithubUserDao

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: MyAppDatabase? = null

        fun getInstance(context: Context): MyAppDatabase {
            return instance ?: synchronized(this) {
                instance
                    ?: buildDatabase(
                        context
                    ).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): MyAppDatabase {
            return Room.databaseBuilder(context, MyAppDatabase::class.java, DATABASE_NAME).build()
        }
    }
}

private const val DATABASE_NAME = "github-users-db"