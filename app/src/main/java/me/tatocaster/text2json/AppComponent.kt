package me.tatocaster.text2json

import android.content.Context
import android.content.res.Resources
import dagger.Component
import me.tatocaster.text2json.data.DataComponent
import me.tatocaster.text2json.data.DataModule
import java.util.*
import javax.inject.Singleton

/**
 * Created by tatocaster on 12/1/17.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, DataModule::class))
interface AppComponent : DataComponent {

    fun inject(app: App)

    // expose dependencies to scope graph
    fun exposeAppContext(): Context

    fun exposeResources(): Resources

    fun exposeLocale(): Locale

}