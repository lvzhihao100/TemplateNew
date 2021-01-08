package com.gamerole.common.base

import android.os.Bundle
import android.view.View

abstract class BaseFragment constructor(contentLayoutId : Int): BindingFragment(contentLayoutId) {
    private lateinit var baseViewModel: BaseViewModel



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseViewModel= getViewModel()
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