package me.tatocaster.text2json.features.main

import dagger.Module
import dagger.Provides

/**
 * Created by tatocaster on 12/1/17.
 */

@Module
class MainModule(private val view: MainContract.View) {

    @Provides
    fun provideView(): MainContract.View = this.view

    @Provides
    fun providePresenter(presenter: MainPresenter): MainContract.Presenter = presenter
}