package com.djc.kotlin.girl.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.djc.kotlin.girl.R

/**
 * @author dong
 * @date 2017/12/4 14:58
 * @description 空加载局部
 */
class EmptyLayout @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private var mBgColor: Int = 0
    //布局当前状态
    private var mCurrentStatus: Int = STATUS_LOADING
    private lateinit var mTvTip: TextView
    private lateinit var mEmptyLayout: FrameLayout
    private lateinit var mPbLoading: ProgressBar
    //重试回调
    private lateinit var mRetryListener: () -> Unit

    /**
     * 定义各种状态
     */
    companion object {
        val STATUS_HIDE: Int = 1001
        val STATUS_LOADING: Int = 1002
        val STATUS_NO_NET: Int = 1003
        val STATUS_NO_DATA: Int = 1004
    }

    /**
     * 重新点击
     */
    fun onRetryListener(listener: () -> Unit) {
        this.mRetryListener = listener
    }

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.EmptyLayout)
        mBgColor = a.getColor(R.styleable.EmptyLayout_colorBg, Color.WHITE)
        a.recycle()
        //加载布局
        View.inflate(context, R.layout.empty_layout, this)

        with(this) {
            mTvTip = findViewById(R.id.tv_no_net_tip)
            mEmptyLayout = findViewById(R.id.empty_layout)
            mPbLoading = findViewById(R.id.pb_loading)
        }
        mEmptyLayout.setBackgroundColor(mBgColor)
        changeLayoutStatus(STATUS_LOADING)

    }

    /**
     * 隐藏视图
     */
    fun hide() {
        mCurrentStatus = STATUS_HIDE
        changeLayoutStatus(mCurrentStatus)
    }

    /**
     * 改变当前布局
     */
    fun setCurrentStatus(status: Int) {
        mCurrentStatus = status
        changeLayoutStatus(mCurrentStatus)
    }

    /***
     * 切换当前布局状态
     */
    private fun changeLayoutStatus(status: Int) {
        when (status) {
            STATUS_LOADING -> {
                this.visibility = View.VISIBLE
                mTvTip.visibility = View.GONE
                mPbLoading.visibility = View.VISIBLE
            }
            STATUS_HIDE -> {
                this.visibility = View.GONE
            }
            STATUS_NO_DATA -> {
                //暂时懒得写
            }
            STATUS_NO_NET -> {
                this.visibility = View.VISIBLE
                mTvTip.visibility = View.VISIBLE
                mPbLoading.visibility = View.GONE
            }
        }
    }
}