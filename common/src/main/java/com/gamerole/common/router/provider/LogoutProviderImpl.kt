package com.gamerole.common.router.provider

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.gamerole.common.RoutePath
import com.gamerole.common.base.AppManager
import com.gamerole.common.util.DataStoreUtils.clearSync

@Route(path = RoutePath.COMMON_LOGOUT)
class LogoutProviderImpl : LogoutProvider {
    override fun logout() {
        clearSync()
        AppManager.getAppManager().finishAllActivity()
        ARouter.getInstance().build(RoutePath.LOGIN_LOGIN).navigation()
    }

    override fun logout(msg: String?) {}
    override fun init(context: Context) {}
}