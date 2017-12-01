package me.tatocaster.text2json.features.main

import dagger.Component
import me.tatocaster.text2json.AppComponent
import me.tatocaster.text2json.di.scope.ActivityScope

/**
 * Created by tatocaster on 12/1/17.
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(MainModule::class))
interface MainComponent {
    fun inject(view: MainActivity)

}