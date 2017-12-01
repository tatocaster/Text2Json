package me.tatocaster.text2json.features.main

import android.util.Log
import com.google.gson.GsonBuilder
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.tatocaster.text2json.data.api.ApiService
import me.tatocaster.text2json.entity.ParsedResponse
import me.tatocaster.text2json.entity.Url
import me.tatocaster.text2json.utils.parseEmojis
import me.tatocaster.text2json.utils.parseLinks
import me.tatocaster.text2json.utils.parseMentions
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import javax.inject.Inject


/**
 * Created by tatocaster on 12/1/17.
 */
class MainPresenter @Inject constructor(private var view: MainContract.View,
                                        private var apiService: ApiService) : MainContract.Presenter {
    private val disposables: CompositeDisposable = CompositeDisposable()
    private val requestQueue: ArrayList<Flowable<ResponseBody>> = arrayListOf()

    override fun parseText(input: String) {
        val gson = GsonBuilder().setPrettyPrinting().create()

        val mentions = input.parseMentions()
        val emojis = input.parseEmojis()
        val links = input.parseLinks()

        zipFlowables(links)
                .subscribe(
                        { result ->
                            val linksWithTitle = arrayListOf<Url>()
                            result.forEachIndexed({ k, v ->
                                val title = Jsoup.parse(v).title()
                                linksWithTitle.add(Url(title, links[k].url))
                            })
                            val parsedResponse = ParsedResponse(emojis, linksWithTitle, mentions)
                            view.showMessage(gson.toJson(parsedResponse))
                            requestQueue.clear()
                        },
                        { e ->
                            val parsedResponse = ParsedResponse(emojis, mutableListOf(), mentions)
                            view.showMessage(gson.toJson(parsedResponse))
                            Log.e("error", e.message, e)
                        }
                )
    }


    private fun zipFlowables(links: MutableList<Url>): Flowable<ArrayList<String>> {
        links.forEach({
            requestQueue.add(
                    Flowable.defer({ apiService.getSite(it.url) })
            )
        })

        return Flowable.zip(requestQueue, { args ->
            val resultArray: ArrayList<String> = arrayListOf()
            args.mapTo(resultArray) {
                (it as ResponseBody).string()
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    }


    override fun onDestroy() {
        disposables.clear()
    }
}