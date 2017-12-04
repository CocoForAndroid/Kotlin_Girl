package com.djc.kotlin.girl.module.home.common

import com.djc.kotlin.girl.BasePresenter
import com.djc.kotlin.girl.BaseView
import com.djc.kotlin.girl.bean.GankData

/**
 * @author dong
 * @date 2017/11/13 14:50
 * @description
 */
interface CommonContract {
    interface View : BaseView<Presenter> {
        fun showList(data: ArrayList<GankData>)
        fun showLoading()
        fun hideLoading()
        fun showNoNet()
    }

    interface Presenter : BasePresenter {
        fun loadMoreData(currentPage: Int)
    }
}