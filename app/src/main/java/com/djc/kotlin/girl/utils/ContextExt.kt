package com.djc.kotlin.girl.utils

import android.content.Context
import android.view.View
import android.widget.Toast

/**
 * @author dong
 * @date 2017/11/13 16:23
 * @description context扩展方法
 */

    /**
     * 弹吐司
     */
    fun Context.showToast(msg: String, duration: Int) {
        Toast.makeText(this, msg, duration).show()
    }
