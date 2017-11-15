package com.djc.kotlin.girl.module.home.common

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.djc.kotlin.girl.R
import com.djc.kotlin.girl.adapter.CommonAdapter
import com.djc.kotlin.girl.bean.GankData
import com.djc.kotlin.girl.module.home.detail.WebDetailAty
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator


class CommonFragment : LazyLoadFragment(), CommonContract.View {
    /**
     * lazyLoad
     */
    override fun lazyLoad() {


    }

    override fun onInVisible() {

    }


    override lateinit var presenter: CommonContract.Presenter

    private lateinit var mCommonList: RecyclerView

    private var mParam1: Int? = null
    private var mParam2: String? = null
    private lateinit var mAdapter: CommonAdapter
    private var mListener: OnFragmentInteractionListener? = null
    //刷新布局
    private lateinit var mRefreshLayout: SwipeRefreshLayout
    //数据源
    private var mDatas: ArrayList<GankData> = ArrayList()
    //是否是下拉刷新
    private var isRefreshed = false
    private var page: Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments.getInt(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater!!.inflate(R.layout.fragment_blank, container, false)
        val type = mParam1
        var typeParam: String? = null
        when (type) {
            0 -> typeParam = "福利"
            1 -> typeParam = "Android"
            2 -> typeParam = "iOS"
            3 -> typeParam = "拓展资源"
            4 -> typeParam = "前端"
        }
        Log.d(this.javaClass.simpleName, typeParam)
        //初始化presenter
        presenter = CommonPresenter(typeParam, page, 10, this)
        //开始加载
        presenter.start()
        //with用法, 以对象为参数在函数块内以this指代对象 返回值为最后一行或用return指定
        with(root) {
            mCommonList = findViewById(R.id.common_list)
            mRefreshLayout = findViewById(R.id.srf_layout)
        }
        //init adapter
        initAdapter()
        //下拉刷新
        mRefreshLayout.setOnRefreshListener {
            isRefreshed = true
            presenter.start()
        }
        //加载更多
        mCommonList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!mRefreshLayout.isRefreshing) {
                    if (recyclerView?.layoutManager is LinearLayoutManager) {
                        val layoutManager: LinearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                        val lastPos = layoutManager.findLastVisibleItemPosition()
                        val totalCount = layoutManager.itemCount
                        if (lastPos == totalCount - 1 && newState == RecyclerView.SCROLL_STATE_IDLE) {
                            isRefreshed = false
                            page++
                            loadMore()
                        }
                    }
                }
            }
        })
        //进入详情
        mAdapter.setClickListener {
            val url = mDatas[it].url
            if (mParam1 != 0) {
                //不是图片
                val i = Intent(context, WebDetailAty::class.java)
                i.putExtra("url",url)
                startActivity(i)
            }else{
                //TODO 大图预览
            }
        }
        return root
    }

    /**
     * 加载更多
     */
    private fun loadMore() {
        mRefreshLayout.isRefreshing = true
        presenter.loadMoreData(page)
    }


    /**
     * 初始化适配器
     */
    private fun initAdapter() {
        mAdapter = CommonAdapter(context, mDatas)
        mCommonList.apply {
            layoutManager = if (mParam1 == 0) {
                GridLayoutManager(context, 2)
            } else {
                LinearLayoutManager(context)
            }
            if (mParam1 == 0) {
                removeItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            } else {
                addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            }
            itemAnimator = SlideInLeftAnimator()
            adapter = mAdapter
        }
    }


    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        }

    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface OnFragmentInteractionListener {

        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        fun newInstance(param1: Int, param2: String?): CommonFragment {
            val fragment = CommonFragment()
            val args = Bundle()
            args.putInt(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

    /**
     * 显示列表内容
     */
    override fun showList(data: ArrayList<GankData>) {
        //停止刷新
        if (mRefreshLayout.isRefreshing) {
            mRefreshLayout.isRefreshing = false
        }

        //刷新重置
        if (isRefreshed) {
            page = 1
            mDatas.clear()
        }
        mDatas.addAll(data)
        mAdapter.notifyDataSetChanged()
    }


}
