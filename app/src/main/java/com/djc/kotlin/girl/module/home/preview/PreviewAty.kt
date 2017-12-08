package com.djc.kotlin.girl.module.home.preview

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.support.v4.view.PagerAdapter
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.djc.kotlin.girl.BaseAty
import com.djc.kotlin.girl.GlideApp
import com.djc.kotlin.girl.R
import com.djc.kotlin.girl.widget.ImgPopupwindow
import com.djc.kotlin.girl.widget.PhotoViewPager
import com.github.chrisbanes.photoview.PhotoView
import com.orhanobut.logger.Logger

/**
 * @author dong
 * @date 2017/12/5 10:38
 * @description 图片预览
 */
class PreviewAty : BaseAty() {
    private lateinit var mViewPager: PhotoViewPager
    private var pos = 0
    private lateinit var imgList: List<String>
    private lateinit var mAdapter: Adapter
    private lateinit var mPop: ImgPopupwindow
    override fun getLayoutId(): Int = R.layout.activity_preview

    override fun initViews() {
        //设置全屏
        val option: Int = View.SYSTEM_UI_FLAG_FULLSCREEN
        window.decorView.systemUiVisibility = option
        window.statusBarColor = Color.BLACK
        //接收图片
        pos = intent.getIntExtra("pos", 0)
        imgList = intent.getStringArrayListExtra("imgList")
        //关联适配器
        mAdapter = Adapter()
        mViewPager = findViewById(R.id.pic_vp)
        mViewPager.adapter = mAdapter
        //显示当前的图片
        mViewPager.currentItem = pos

        //设置pop
        mPop = ImgPopupwindow(this)
    }


    inner class Adapter : PagerAdapter() {
        override fun isViewFromObject(view: View?, `object`: Any?): Boolean = view === `object`

        override fun getCount(): Int = imgList.size

        override fun instantiateItem(container: ViewGroup?, position: Int): Any {
            val url = imgList[position]
            val view = LayoutInflater.from(this@PreviewAty).inflate(R.layout.item_preview
                    , container, false)
            val photoView = view.findViewById<PhotoView>(R.id.photo_view)
            GlideApp.with(this@PreviewAty)
                    .load(url)
                    .into(photoView)
            container?.addView(view)
            //长按图片弹出下载，分享泡泡
            photoView.setOnLongClickListener {
                mPop.showAtLocation(findViewById(R.id.root), Gravity.BOTTOM, 0, 0)

                return@setOnLongClickListener true
            }
            return view
        }

        override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
//            super.destroyItem(container, position, `object`)
            container?.removeView(`object` as View?)
        }
    }

    override fun onRestart() {
        super.onRestart()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        window.statusBarColor = Color.BLACK
        Log.d("life—cycle", "onRestart")
    }

    override fun finishAfterTransition() {
        super.finishAfterTransition()
        val i = Intent()
        i.putExtra("index", mViewPager.currentItem)
        //区分返回还是进入
        i.putExtra("isBack", true)
        Logger.d("${mViewPager.currentItem}")
        setResult(Activity.RESULT_OK, i)
    }

}