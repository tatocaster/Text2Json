package me.tatocaster.text2json.utils

import me.tatocaster.text2json.entity.MetaItem
import me.tatocaster.text2json.entity.Url
import java.util.regex.Pattern

/**
 * Created by tatocaster on 30.11.17.
 */
fun String.parseMentions() = parseString("@+[\\w-]+", this)

fun String.parseEmojis() = parseString(":+[\\w-]+:", this)

fun String.parseLinks(): MutableList<Url> {
    val foundItems = mutableListOf<Url>()
    val m = Pattern.compile("((https?)://[\\w-]+\\.[a-z]+)").matcher(this)
    while (m.find()) {
        foundItems.add(Url("", m.group(0)))
    }
    return foundItems
}

fun parseString(pattern: String, input: String): MutableList<MetaItem> {
    val foundItems = mutableListOf<MetaItem>()
    val m = Pattern.compile(pattern).matcher(input)
    while (m.find()) {
        foundItems.add(MetaItem(m.group(0)))
    }
    return foundItems
}