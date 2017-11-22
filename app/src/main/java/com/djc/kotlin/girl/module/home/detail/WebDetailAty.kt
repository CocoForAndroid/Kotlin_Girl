package com.djc.kotlin.girl.module.home.detail

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.KeyEvent
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.djc.kotlin.girl.BaseAty
import com.djc.kotlin.girl.R
import com.djc.kotlin.girl.utils.showToast

/**
 * @author dong
 * @date 2017/11/15 10:15
 * @description 展示列表内容
 */
class WebDetailAty : BaseAty() {
    private lateinit var mWebView: WebView
    private var url: String? = ""
    override fun getLayoutId(): Int {
        return R.layout.activity_web
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun initViews() {
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
                this@WebDetailAty.showToast("开始loading", 1000)
            }

            //结束加载
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                this@WebDetailAty.showToast("finish loading", 1000)

            }
        }

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(event?.action==KeyEvent.ACTION_DOWN&&mWebView.canGoBack()){
            mWebView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}