package com.gamerole.common.entity

import com.flyco.tablayout.listener.CustomTabEntity

class TabItem(var title: String, var unSelectedIcon: Int, var selectedIcon: Int) : CustomTabEntity {
    override fun getTabTitle(): String {
        return title
    }

    override fun getTabSelectedIcon(): Int {
        return selectedIcon
    }

    override fun getTabUnselectedIcon(): Int {
        return unSelectedIcon
    }
}