package me.tatocaster.text2json

import android.app.Application
import android.content.Context

/**
 * Created by tatocaster on 12/1/17.
 */
class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        initComponents()
    }

    private fun initComponents() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
        appComponent.inject(this)
    }

    companion object {
        @JvmStatic
        fun getAppContext(context: Context): App = context.applicationContext as App
    }
}