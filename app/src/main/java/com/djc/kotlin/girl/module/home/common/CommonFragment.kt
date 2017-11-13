package com.djc.kotlin.girl.module.home.common

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.djc.kotlin.girl.R
import com.djc.kotlin.girl.adapter.CommonAdapter
import com.djc.kotlin.girl.bean.GankData


class CommonFragment : Fragment(), CommonContract.View {
    /**
     * 显示列表内容
     */
    override fun showList(data: ArrayList<GankData>) {
//        context.showToast(data[0].desc ?: "sb", 5000)
        mAdapter = CommonAdapter(context, data)
        mCommonList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    override lateinit var presenter: CommonContract.Presenter

    private lateinit var mCommonList: RecyclerView

    private var mParam1: Int = 0
    private var mParam2: String? = null
    private lateinit var mAdapter: CommonAdapter
    private var mListener: OnFragmentInteractionListener? = null

    //请求数据类型
    private var type: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments.getInt(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater!!.inflate(R.layout.fragment_blank, container, false)

        type = mParam1
        var typeParam: String? = null
        when (type) {
            0 -> typeParam = "福利"
            1 -> typeParam = "Android"
            2 -> typeParam = "iOS"
            3 -> typeParam = "拓展资源"
            4 -> typeParam = "前端"
        }
        Log.d("index","caonima")
        //初始化presenter
        presenter = CommonPresenter(typeParam, 1, 10, this)
        //with用法, 以对象为参数在函数块内以this指代对象 返回值为最后一行或用return指定

        with(root) {
            mCommonList = findViewById(R.id.common_list)
        }
        return root
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
//        } else {
//            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
//        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CommonFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: Int, param2: String?): CommonFragment {
            val fragment = CommonFragment()
            val args = Bundle()
            args.putInt(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor