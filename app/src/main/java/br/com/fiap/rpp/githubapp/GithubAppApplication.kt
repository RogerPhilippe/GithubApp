package br.com.fiap.rpp.githubapp

import android.app.Application
import com.facebook.stetho.Stetho

class GithubAppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }

}