package com.gamerole.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.viewbinding.ViewBinding

abstract class BaseDialog : AppCompatDialogFragment() {
    abstract fun getViewModel(): BaseViewModel
    abstract fun getViewBinding(): ViewBinding


    private lateinit var baseViewModel: BaseViewModel
    private lateinit var viewBinding: ViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = getViewBinding()
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseViewModel = getViewModel()
        baseViewModel.progress.observe(viewLifecycleOwner, {
            if (it)
                showLoading()
            else
                hideLoading()
        })
        initView()
        initData()
        initEvent()
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//
//    }

    open fun initEvent() {}
    open fun initData() {}
    open fun initView() {}
    private fun showLoading() {
        if (activity is BaseActivity) {
            (activity as BaseActivity).showLoading()
        }
    }

    private fun hideLoading() {
        if (activity is BaseActivity) {
            (activity as BaseActivity).hideLoading()
        }
    }
}