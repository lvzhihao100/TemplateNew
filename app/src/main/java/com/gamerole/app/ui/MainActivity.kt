package com.gamerole.app.ui

import android.util.SparseArray
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.gamerole.app.R
import com.gamerole.app.databinding.AppActivityMainBinding
import com.gamerole.app.viewmodel.MainViewModel
import com.gamerole.common.RoutePath
import com.gamerole.common.base.BaseActivity
import com.gamerole.common.entity.TabItem
import com.hi.dhl.binding.viewbind
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
@Route(path = RoutePath.APP_MAIN)
class MainActivity : BaseActivity() {

    override fun getImmersion() = true

    private val viewModelMain: MainViewModel by viewModels()
    override fun getViewModel() = viewModelMain
    private val binding: AppActivityMainBinding by viewbind()


    private var curPos = 0
    private lateinit var fragments: ArrayList<Fragment>
    var tabMap: SparseArray<Fragment> = SparseArray<Fragment>()

    override fun initView() {
        with(binding) {
            val tabItems = ArrayList<CustomTabEntity>()
            tabItems.add(
                TabItem(
                    "首页",
                    R.mipmap.app_icon_menu_first_unselect,
                    R.mipmap.app_icon_menu_first_select
                )
            )

            tabItems.add(
                TabItem(
                    "我的",
                    R.mipmap.app_icon_menu_third_unselect,
                    R.mipmap.app_icon_menu_third_select
                )
            )

            commonTabLayout.setTabData(tabItems)
            commonTabLayout.setOnTabSelectListener(object : OnTabSelectListener {
                override fun onTabSelect(position: Int) {
                    changeTab(position)
                }

                override fun onTabReselect(position: Int) {}
            })
            fragments = ArrayList()
            fragments.add(
                (ARouter.getInstance().build(RoutePath.MINE_FRAGMENT_MAIN).navigation() as Fragment)
            )
            fragments.add(
                (ARouter.getInstance().build(RoutePath.MINE_FRAGMENT_MAIN).navigation() as Fragment)
            )

            supportFragmentManager
                .beginTransaction()
                .add(R.id.frameLayout, fragments[0])
                .show(fragments[0])
                .commitAllowingStateLoss()
            tabMap.put(0, fragments[0])

        }
    }

    private fun changeTab(position: Int) {
        if (tabMap[position] == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.frameLayout, fragments[position])
                .hide(fragments[curPos])
                .show(fragments[position])
                .commitAllowingStateLoss()
            tabMap.put(position, fragments[position])
        } else {
            supportFragmentManager
                .beginTransaction()
                .hide(fragments[curPos])
                .show(fragments[position])
                .commitAllowingStateLoss()
        }
        curPos = position
    }

    override fun initData() {

    }

}