package com.gamerole.common.base

import android.os.Bundle
import android.view.View
import com.gamerole.common.R
import com.gamerole.common.dialog.LoadingProgressDialog
import com.gamerole.common.extention.click
import com.gyf.immersionbar.ImmersionBar

abstract class BaseActivity : BindingActivity() {
    var loadingDialog: LoadingProgressDialog? = null

    var request = 0
    private lateinit var baseViewModel:BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (getImmersion()) {
            ImmersionBar.with(this)
                .fitsSystemWindows(false)  //使用该属性必须指定状态栏的颜色，不然状态栏透明，很难看
                .init();
        } else {
            ImmersionBar.with(this)
                .fitsSystemWindows(true)
                .statusBarColor(R.color.commonWhite)
                .statusBarDarkFont(true)
                .init()
        }

        baseViewModel= getViewModel()
        baseViewModel.progress.observe(this, {
            if (it)
                showLoading()
            else
                hideLoading()
        })


        initView()
        initData()
        initEvent()

        val back: View? = findViewById(R.id.ivBack)
        back?.click { onBackPressed() }


    }


    open fun initEvent() {}


    open fun initData() {}
    open fun initView() {}


    open fun showLoading() {
        request++
        if (request == 1) {
            if (loadingDialog == null) {
                loadingDialog = LoadingProgressDialog(this)
            }
            if (!loadingDialog!!.isShowing()) {
                loadingDialog!!.show()
            }
        }
    }
    @Synchronized
    open fun hideLoading() {
        request--
        if (request == 0) {
            loadingDialog?.dismiss()
        }
    }

}