package com.djc.kotlin.girl.widget

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupWindow
import com.djc.kotlin.girl.R
import com.djc.kotlin.girl.utils.LocalUtils

/**
 * @author dong
 * @date 2017/12/5 15:24
 * @description
 */
class ImgPopupwindow(ctx: Context) : PopupWindow() {
    init {
        width = LocalUtils.getScreenWidth(ctx)
        height = ViewGroup.LayoutParams.WRAP_CONTENT
        contentView = LayoutInflater.from(ctx).inflate(R.layout.img_pop, null)
        isOutsideTouchable = true
        isFocusable = true
        setBackgroundDrawable(ColorDrawable(0xffffff))
        animationStyle = R.style.Pop_anim

    }

}