package com.gamerole.app

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.facebook.stetho.Stetho
import com.gamerole.app.ui.MainActivity
import com.gamerole.common.base.BaseActivityLifecycleCallbacks
import com.gamerole.common.mvchelper.LoadViewFactory
import com.gamerole.common.util.DataStoreUtils
import com.shizhefei.mvc.MVCHelper
import com.shizhefei.view.coolrefreshview.CoolRefreshView
import com.shizhefei.view.coolrefreshview.IPullHeaderFactory
import com.shizhefei.view.coolrefreshview.PullHeader
import com.shizhefei.view.coolrefreshview.header.MaterialHeader
import com.zxy.recovery.core.Recovery
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class HiltApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        ARouter.init(this)
        DataStoreUtils.init(this)
        Recovery.getInstance()
            .debug(true)
            .recoverInBackground(false)
            .recoverStack(true)
            .mainPage(MainActivity::class.java)
            .recoverEnabled(true)
            .silent(false, Recovery.SilentMode.RECOVER_ACTIVITY_STACK)
            .init(this)

        MVCHelper.setLoadViewFractory(LoadViewFactory())
        CoolRefreshView.setPullHeaderFactory(object : IPullHeaderFactory {
            override fun made(context: Context): PullHeader {
                return MaterialHeader(context)
            }

            override fun isPinContent(): Boolean {
                return true
            }
        })
        registerActivityLifecycleCallbacks(BaseActivityLifecycleCallbacks())

    }
}