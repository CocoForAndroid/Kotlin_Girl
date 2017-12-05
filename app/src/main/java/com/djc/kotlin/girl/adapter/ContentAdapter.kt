package com.djc.kotlin.girl.adapter

import android.content.Context
import android.widget.ImageView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.djc.kotlin.girl.GlideApp
import com.djc.kotlin.girl.R
import com.djc.kotlin.girl.bean.GankData
import com.djc.kotlin.girl.utils.LocalUtils

/**
 * @author dong
 * @date 2017/12/5 10:08
 * @description
 */
class ContentAdapter(ctx: Context, data: List<GankData>) : BaseMultiItemQuickAdapter<GankData, BaseViewHolder>(data) {
    init {
        addItemType(GankData.TYPE_TXT, R.layout.item_common_article_list)
        addItemType(GankData.TYPE_IMG, R.layout.item_common_img_list)
    }

    override fun convert(helper: BaseViewHolder?, item: GankData?) {
        when (helper?.itemViewType) {
            GankData.TYPE_IMG -> {
                helper.getView<ImageView>(R.id.item_iv)?.apply {
                    layoutParams.height = (LocalUtils.getScreenHeight(context) / 3)
                    layoutParams.width = (LocalUtils.getScreenWidth(context) / 2)
                }
                GlideApp.with(mContext)
                        .load(item?.url)
                        .placeholder(R.drawable.dog)
                        .fitCenter()
                        .into(helper.getView(R.id.item_iv))
            }
            GankData.TYPE_TXT -> {
                //文章
                helper.setText(R.id.desc_tv,item?.desc)
            }
        }
    }

}