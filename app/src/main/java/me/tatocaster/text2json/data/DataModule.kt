package me.tatocaster.text2json.data

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import me.tatocaster.text2json.data.api.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by tatocaster on 12/1/17.
 */
@Module
class DataModule {

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS

        return Retrofit.Builder()
                .baseUrl("https://google.com") //any url. changing baseUrl at runtime
                .client(OkHttpClient.Builder()
                        .addInterceptor(loggingInterceptor)
                        .build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

}