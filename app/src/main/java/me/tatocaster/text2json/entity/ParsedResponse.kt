package me.tatocaster.text2json.entity

/**
 * Created by tatocaster on 12/1/17.
 */
data class ParsedResponse(val emojis: MutableList<MetaItem> = mutableListOf(),
                          val links: MutableList<Url> = mutableListOf(),
                          val mentions: MutableList<MetaItem> = mutableListOf())