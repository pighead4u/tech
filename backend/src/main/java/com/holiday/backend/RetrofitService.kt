package com.holiday.backend

import com.holiday.backend.api.GankAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 作者：pighead4u
 * 时间：2019-12-17
 * 描述：
 **/
object RetrofitService {

    private var gankApi: GankAPI? = null
    private val okHttpClient = OkHttpClient()

    fun getGankApi(): GankAPI {
        if (gankApi == null) {
            val retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://gank.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            gankApi = retrofit.create(GankAPI::class.java)
        }
        return gankApi!!
    }
}