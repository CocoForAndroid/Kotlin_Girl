package com.djc.kotlin.girl.module.home.common


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

/**
 * author： djc
 * date： 2017/2/15 16:05
 * version： 1.0
 * description：fragment懒加载基类
 */
abstract class LazyLoadFragment : Fragment() {
    private var mRootView: View? = null
    protected lateinit var mActivity: AppCompatActivity
    //是否可见
    private var isVisibled: Boolean = false


    //是否初始化完成
    //防止空指针
    private var isPrepare: Boolean = false


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.mActivity = (context as AppCompatActivity?)!!
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = inflater!!.inflate(0, container, false)
        return mRootView
    }


    /**
     * activity创建完后加载数据
     *
     * @param savedInstanceState
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isPrepare = true
    }


    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (userVisibleHint) {
            //用户可见时加载数据
            isVisibled = true
            onVisible()
        } else {
            isVisibled = false
            //用户不可见时 doSomething
            onInVisible()
        }
    }

    /**
     * 对用户可见
     */
    private fun onVisible() {
        lazyLoad()
    }

    /**
     * 懒加载
     */
    abstract fun lazyLoad()


    /**
     * 对用户不可见
     */
    abstract fun onInVisible()


}
