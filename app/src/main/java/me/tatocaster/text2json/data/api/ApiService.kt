package me.tatocaster.text2json.data.api

import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by tatocaster on 12/1/17.
 */
interface ApiService {
    @GET
    fun getSite(@Url url: String): Flowable<ResponseBody>
}