package com.djc.kotlin.girl.module.home.detail

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.KeyEvent
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.djc.kotlin.girl.BaseAty
import com.djc.kotlin.girl.R
import com.djc.kotlin.girl.utils.showToast
import com.djc.kotlin.girl.widget.EmptyLayout

/**
 * @author dong
 * @date 2017/11/15 10:15
 * @description 展示列表内容
 */
class WebDetailAty : BaseAty() {
    private lateinit var mWebView: WebView
    private var url: String? = ""
    override fun getLayoutId(): Int = R.layout.activity_web
    private lateinit var mEmptyLayout: EmptyLayout
    @SuppressLint("SetJavaScriptEnabled")
    override fun initViews() {
        //设置全屏
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        mEmptyLayout = findViewById(R.id.empty_layout)
        //详情地址
        url = intent.extras.getString("url")
        mWebView = findViewById(R.id.web)
        //设置执行脚本语言
        mWebView.settings.javaScriptEnabled = true

        mWebView.loadUrl(url)
        //不通过浏览器打开网页
        mWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?)
                    : Boolean {
                val newUrl = request?.url
                view?.loadUrl(newUrl.toString())
                return true
            }


            //开始加载 显示loading
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
//                this@WebDetailAty.showToast("开始loading", 1000)
                mEmptyLayout.setCurrentStatus(EmptyLayout.STATUS_LOADING)
            }

            //结束加载
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                mEmptyLayout.setCurrentStatus(EmptyLayout.STATUS_HIDE)


            }
        }

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (event?.action == KeyEvent.ACTION_DOWN && mWebView.canGoBack()) {
            mWebView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}