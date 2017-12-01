package me.tatocaster.text2json.features.main

/**
 * Created by tatocaster on 12/1/17.
 */
class MainContract {
    interface View {
        fun showMessage(s: String)
    }

    interface Presenter {
        fun onCreate()

        fun onDestroy()
    }
}