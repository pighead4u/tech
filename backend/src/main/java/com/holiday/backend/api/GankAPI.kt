package com.holiday.backend.api

import com.holiday.backend.model.CategoryResult
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface GankAPI {
    /**
     * 根据category获取Android、iOS等干货数据
     * @param category  类别
     * @param count     条目数目
     * @param page      页数
     */
    @GET("data/{category}/{count}/{page}")
    fun getCategoryData(@Path("category") category: String, @Path("count") count: Int, @Path("page") page: Int): Flowable<CategoryResult>

}