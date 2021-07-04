package io.codeherb.composegithubfinder

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GithubFinderApp: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}