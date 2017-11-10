package com.djc.kotlin.girl.utils

import com.djc.kotlin.girl.bean.AppResult
import io.reactivex.functions.Function


/**
 * @author dong
 * @date 2017/11/10 16:29
 * @description
 */
class HttpResultFunc<T> : Function<AppResult<T>, T> {
    override fun apply(t: AppResult<T>): T {
        if (!t.error) {
            throw ApiException()
        }
        return t.results
    }

}