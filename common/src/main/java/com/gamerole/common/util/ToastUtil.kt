package com.gamerole.common.util

import com.gamerole.common.base.App
import es.dmoral.toasty.Toasty

object ToastUtil {
    fun showShort(message: CharSequence?) {

        Toasty.normal(App.INSTANCE, message!!).show()
    }
}