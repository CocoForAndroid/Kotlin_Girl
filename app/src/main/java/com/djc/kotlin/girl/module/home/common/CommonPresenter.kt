package com.djc.kotlin.girl.module.home.common

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
                      , private var page: Int
                      , private var pageSize: Int
                      , private var v: CommonContract.View)
    : CommonContract.Presenter {
    override fun loadMoreData(currentPage: Int) {
        HttpUtils.createService(IGankIo::class.java)
                .getGankData(type = type, count = pageSize, page = currentPage)
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

    override fun start(isRefresh: Boolean) {
        HttpUtils.createService(IGankIo::class.java)
                .getGankData(type = type, count = pageSize, page = 1)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .doOnSubscribe({
                    if (!isRefresh) {
                        v.showLoading()
                    }else{
                        //结束下拉刷新
                    }

                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(HttpResultFunc())
                .subscribe({
                    //onNext
                    result ->
                    v.hideLoading()
                    v.showList(result)

                }, {
                    //onError
                    e ->
                    e.printStackTrace()
                    v.showNoNet()
                })

    }
}