package com.djc.kotlin.girl.module.home


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.util.Log
import com.djc.kotlin.girl.BaseAty
import com.djc.kotlin.girl.R
import com.djc.kotlin.girl.bean.AppResult
import com.djc.kotlin.girl.bean.GankData
import com.djc.kotlin.girl.module.home.common.CommonFragment
import com.djc.kotlin.girl.module.home.video.VideoFragment

/**
 * @author dong
 * @date 2017/11/10 15:41
 * @description 主页
 */
class HomeAty : BaseAty(), HomeContract.View {



    private lateinit var mToolbar: Toolbar
    private lateinit var mTabLayout: TabLayout
    private lateinit var mViewPager: ViewPager

    override lateinit var presenter: HomeContract.Presenter

    //tab标题
    private val titles: ArrayList<String> = ArrayList()
    //tab内容
    private val fragments: ArrayList<Fragment> = ArrayList()

    override fun initViews() {
        mToolbar = findViewById(R.id.toolbar)
        mTabLayout = findViewById(R.id.tab_layout)
        mViewPager = findViewById(R.id.vp_content)

        //初始化toolBar
        initToolbar()

        //tab分类
        titles.add("福利")
        titles.add("Android")
        titles.add("iOS")
        titles.add("拓展资源")
        titles.add("前端")
        titles.add("休息视频")
        //设置tab
        initTab()
    }

    private fun initToolbar() {
        mToolbar.title = "妹纸"
    }

    private fun initTab() {
        //repeat函数:重复执行代码块
        repeat(titles.size - 1) {
            fragments.add(CommonFragment.newInstance(it, null))
            Log.d(TAG, "position = $it")
            mTabLayout.addTab(mTabLayout.newTab())
        }
        //休息视频
        fragments.add(VideoFragment.newInstance(null, null))
        mTabLayout.addTab(mTabLayout.newTab())

        mTabLayout.tabMode = TabLayout.MODE_SCROLLABLE

        Log.d(TAG, fragments.toString())
        val adapter = ContentAdapter(supportFragmentManager, titles, fragments)
        mViewPager.offscreenPageLimit = 0
        mViewPager.adapter = adapter

        mTabLayout.setupWithViewPager(mViewPager)

//        mTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//            }
//
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                val currentFragment = tab?.position?.let { fragments[it] }
//                val bundle = Bundle()
//                tab?.position?.let { it }?.
//                        let { bundle.putInt("current_pos", it)
//                        Log.d(TAG,"it = $it")
//                        }
//                currentFragment?.arguments= bundle
//            }
//
//        })

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }


}