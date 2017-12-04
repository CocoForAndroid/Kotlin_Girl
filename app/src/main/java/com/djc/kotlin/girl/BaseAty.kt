package com.djc.kotlin.girl

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

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
    }
}