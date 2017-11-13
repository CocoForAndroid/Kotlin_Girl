package com.djc.kotlin.girl.module.home.common

import android.util.Log
import com.djc.kotlin.girl.BasePresenter
import com.djc.kotlin.girl.utils.HttpResultFunc
import com.djc.kotlin.girl.utils.HttpUtils
import com.djc.kotlin.girl.utils.IGankIo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author dong
 * @date 2017/11/13 16:01
 * @description 请求数据
 */
class CommonPresenter(private var type: String?
                      , private var page: Int?
                      , private var pageSize: Int?
                      , private var v: CommonContract.View)
    : CommonContract.Presenter {

    override fun start() {
        HttpUtils.createService(IGankIo::class.java)
                .getGankData(type = type, count = pageSize, page = page)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(HttpResultFunc())
                .subscribe({
                    //onNext
                    result ->
                    v.showList(result)

                }, {
                    //onError
                    e ->
                    e.printStackTrace()
                })

    }
}