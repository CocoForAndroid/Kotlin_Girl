package com.djc.kotlin.girl.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.request.RequestOptions
import com.djc.kotlin.girl.GlideApp
import com.djc.kotlin.girl.R
import com.djc.kotlin.girl.bean.GankData
import com.djc.kotlin.girl.utils.LocalUtils
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

/**
 * @author dong
 * @date 2017/11/13 14:59
 * @description 公共类型列表的适配器
 */
class CommonAdapter(private val ctx: Context?, private var data: ArrayList<GankData>?)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is ViewHolder) {
            //文章
            holder.tv?.text = data?.get(position)?.desc
        } else if (holder is ImgViewHolder) {
            //图片
            val url = data?.get(position)?.url
            holder.iv?.apply {
                layoutParams.height = (LocalUtils.getScreenHeight(context) / 3)
                layoutParams.width = (LocalUtils.getScreenWidth(context) / 2)
            }
            GlideApp.with(ctx)
                    .load(url)
                    .placeholder(R.drawable.dog)
                    .fitCenter()
                    .apply(RequestOptions.bitmapTransform(RoundedCornersTransformation(10, 5)))
                    .into(holder.iv)
        }
    }

    private var inflate: LayoutInflater = LayoutInflater.from(ctx)


    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            0 -> {
                val view = inflate.inflate(R.layout.item_common_article_list, parent, false)
                return ViewHolder(view)
            }

            1 -> {
                val view = inflate.inflate(R.layout.item_common_img_list, parent, false)
                return ImgViewHolder(view)
            }

        }
        return ViewHolder(null)
    }

    override fun getItemViewType(position: Int): Int {
        val type = data?.get(position)?.type
        var typeFlag = -1
        //0文章 1 图片
        when (type) {
            "Android" -> typeFlag = 0
            "iOS" -> typeFlag = 0
            "拓展资源" -> typeFlag = 0
            "前端" -> typeFlag = 0
            "福利" -> typeFlag = 1
        }
        return typeFlag
    }

    class ViewHolder(view: View?) : RecyclerView.ViewHolder(view) {
        var tv: TextView? = null

        init {
            with(view) {
                tv = this?.findViewById(R.id.desc_tv)
            }
        }

    }

    class ImgViewHolder(view: View?) : RecyclerView.ViewHolder(view) {
        var iv: ImageView? = null

        init {
            with(view) {
                iv = this?.findViewById(R.id.item_iv)
            }
        }
    }


}