package com.djc.kotlin.girl.bean

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * @author dong
 * @date 2017/11/10 16:17
 * @description 测试接口
 */
data class GankData(
        val _id: String,
        val type: String,
        val url: String,
        val desc: String
) : MultiItemEntity {
    override fun getItemType(): Int {
        var typeFlag = -1
        when (type) {
            "Android" -> typeFlag = 0
            "iOS" -> typeFlag = 0
            "拓展资源" -> typeFlag = 0
            "前端" -> typeFlag = 0
            "福利" -> typeFlag = 1
        }
        return typeFlag
    }

    companion object {
        val TYPE_TXT = 0
        val TYPE_IMG = 1
    }
}
