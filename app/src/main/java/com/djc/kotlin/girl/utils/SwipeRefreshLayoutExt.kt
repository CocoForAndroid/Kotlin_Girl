package com.djc.kotlin.girl.utils

import android.support.design.widget.AppBarLayout
import android.support.v4.widget.SwipeRefreshLayout

/**
 * @author dong
 * @date 2017/12/4 16:29
 * @description
 */
fun SwipeRefreshLayout.init(l: SwipeRefreshLayout.OnRefreshListener, appBarLayout: AppBarLayout? = null) {
    this.setOnRefreshListener(l)
    this.setColorSchemeColors(resources.getColor(android.R.color.background_dark))
    appBarLayout?.addOnOffsetChangedListener { _, verticalOffset ->
        this.isEnabled = verticalOffset <= 0
    }
}
