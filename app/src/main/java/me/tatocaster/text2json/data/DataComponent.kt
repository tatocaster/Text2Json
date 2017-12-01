package me.tatocaster.text2json.data

import me.tatocaster.text2json.data.api.ApiService

/**
 * Created by tatocaster on 12/1/17.
 */
interface DataComponent {
    fun exposeApiService(): ApiService
}