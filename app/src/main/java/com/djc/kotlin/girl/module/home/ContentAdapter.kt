package com.djc.kotlin.girl.module.home

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log

/**
 * @author dong
 * @date 2017/11/13 14:07
 * @description
 */
class ContentAdapter(fm: FragmentManager?
                     , private var titles: ArrayList<String>
                     , private var fragments: ArrayList<Fragment>)
    : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        Log.d("djc","fragments size = ${fragments.size}")
        Log.d("djc","titles size = ${titles.size}")

        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence {

        return titles[position]
    }

//    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
//        return view == `object`
//    }


}