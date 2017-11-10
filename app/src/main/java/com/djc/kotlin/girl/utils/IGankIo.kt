package com.djc.kotlin.girl.utils

import com.djc.kotlin.girl.bean.AppResult
import com.djc.kotlin.girl.bean.Test
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author dong
 * @date 2017/11/10 15:44
 * @description 接口配置
 */
interface IGankIo {
    @GET("data/{type}/{count}/{page}")
    fun getTest(@Path("type") type: String, @Path("count") count: Int, @Path("page") page: Int)
            :Flowable<AppResult<Array<Test>>>
}