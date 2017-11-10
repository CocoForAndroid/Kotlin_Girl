package com.djc.kotlin.girl.utils

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author dong
 * @date 2017/11/10 15:19
 * @description
 */
object HttpUtils {
    //读取超时
    private val READ_TIME_OUT = 5L
    //连接超时
    private val CONN_TIME_OUT = 10L

    /**
     * 获取http报文的body
     */
    private fun getHttpLogInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    /**
     * 创建retrofit服务
     */
    fun <T> createService(clazz: Class<T>): T {
        val builder = OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(CONN_TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(getHttpLogInterceptor()).build()
        val retrofit = Retrofit.Builder()
                .baseUrl(ApiConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder).build()
        return retrofit.create(clazz)
    }

}