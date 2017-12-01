package me.tatocaster.text2json.features.main

import android.util.Log
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.tatocaster.text2json.data.api.ApiService
import me.tatocaster.text2json.entity.ParsedResponse
import me.tatocaster.text2json.utils.parseEmojis
import me.tatocaster.text2json.utils.parseLinks
import me.tatocaster.text2json.utils.parseMentions
import javax.inject.Inject


/**
 * Created by tatocaster on 12/1/17.
 */
class MainPresenter @Inject constructor(private var view: MainContract.View,
                                        private var apiService: ApiService) : MainContract.Presenter {
    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun parseText(input: String) {
        val links = input.parseLinks()
        val mentions = input.parseMentions()
        val emojis = input.parseEmojis()
        val parsedResponse = ParsedResponse(emojis, links, mentions)
        val gson = GsonBuilder().setPrettyPrinting().create()

        disposables.add(apiService.getSite(links[0].url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            println(result.string())
                        },
                        { e ->
                            Log.e("error", e.message, e)
                        }
                ))

        view.showMessage(gson.toJson(parsedResponse))
    }


    override fun onDestroy() {
        disposables.clear()
    }
}