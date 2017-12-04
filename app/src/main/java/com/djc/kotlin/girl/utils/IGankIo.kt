package com.djc.kotlin.girl.utils

import com.djc.kotlin.girl.bean.AppResult
import com.djc.kotlin.girl.bean.GankData
import com.djc.kotlin.girl.bean.Update
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author dong
 * @date 2017/11/10 15:44
 * @description 接口配置
 */
interface IGankIo {
    /**
     * 获取gankIo干货数据
     */
    @GET("data/{type}/{count}/{page}")
    fun getGankData(@Path("type") type: String?, @Path("count") count: Int?, @Path("page") page: Int?)
            : Flowable<AppResult<ArrayList<GankData>>>

    /**
     * 获取app版本号
     */

    @GET("latest/{id}")
    fun getAppVersion(@Path("id") id: String?,@Query("api_token") token:String?):Flowable<Update>
}