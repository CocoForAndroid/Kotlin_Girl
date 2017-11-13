package com.djc.kotlin.girl.module.home

import com.djc.kotlin.girl.BasePresenter
import com.djc.kotlin.girl.BaseView
import com.djc.kotlin.girl.bean.AppResult
import com.djc.kotlin.girl.bean.GankData

/**
 * @author dong
 * @date 2017/11/13 11:38
 * @description
 */
interface HomeContract {
    interface Presenter : BasePresenter

    interface View : BaseView<Presenter> {

        //展示请求列表
        fun showList(result: AppResult<List<GankData>>)
    }
}