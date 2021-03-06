package com.djc.kotlin.girl

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.view.Window
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.Logger.addLogAdapter



/**
 * @author dong
 * @date 2017/11/10 16:43
 * @description activity基类
 */
abstract class BaseAty : AppCompatActivity() {
    protected val TAG = this.javaClass.simpleName
    //获取布局id
    abstract fun getLayoutId(): Int

    abstract fun initViews()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initViews()
        //初始化logger
        Logger.addLogAdapter(AndroidLogAdapter())
    }
}