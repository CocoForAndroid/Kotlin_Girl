package com.djc.kotlin.girl.bean

/**
 * @author dong
 * @date 2017/11/10 16:16
 * @description
 */
data class AppResult<T>(var error:Boolean,var results:T)