package com.djc.kotlin.girl.module.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.djc.kotlin.girl.R
import com.djc.kotlin.girl.utils.HttpResultFunc
import com.djc.kotlin.girl.utils.HttpUtils
import com.djc.kotlin.girl.utils.IGankIo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author dong
 * @date 2017/11/10 15:41
 * @description 主页
 */
class HomeAty : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        HttpUtils.createService(IGankIo::class.java)
                .getTest("福利", 10, 1)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .map(HttpResultFunc())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        //onNext
                        { result ->
                            Log.d("草泥马", result.toString())

                        },
                        //onError
                        { error ->
                            error.printStackTrace()
                        }
                )
    }
}