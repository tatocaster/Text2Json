package me.tatocaster.text2json.features.main

import com.google.gson.GsonBuilder
import me.tatocaster.text2json.entity.ParsedResponse
import me.tatocaster.text2json.utils.parseEmojis
import me.tatocaster.text2json.utils.parseLinks
import me.tatocaster.text2json.utils.parseMentions
import javax.inject.Inject


/**
 * Created by tatocaster on 12/1/17.
 */
class MainPresenter @Inject constructor(private var view: MainContract.View) : MainContract.Presenter {
    override fun onDestroy() {
    }

    override fun parseText(input: String) {
        val links = input.parseLinks()
        val mentions = input.parseMentions()
        val emojis = input.parseEmojis()
        val parsedResponse = ParsedResponse(emojis, links, mentions)
        val gson = GsonBuilder().setPrettyPrinting().create()

        view.showMessage(gson.toJson(parsedResponse))
    }
}