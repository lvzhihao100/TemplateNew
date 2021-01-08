package com.gamerole.common.extention

import android.app.Activity
import android.graphics.drawable.Drawable
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.gamerole.common.base.App
import com.yalantis.ucrop.util.ScreenUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

fun Activity.color(@ColorRes color: Int): Int {
    return ContextCompat.getColor(App.INSTANCE, color)
}

fun Activity.drawable(@DrawableRes id: Int): Drawable? {
    return ContextCompat.getDrawable(App.INSTANCE, id)
}

fun Fragment.color(@ColorRes color: Int): Int {
    return ContextCompat.getColor(App.INSTANCE, color)
}

fun Activity.dpToPx(dp: Float): Int {
    return ScreenUtils.dip2px(App.INSTANCE, dp)
}

fun Activity.sendCode(tvGetCode: TextView) {
    MainScope().launch {
        tvGetCode.isEnabled = false
        flow {
            for (i in 60 downTo 1) {
                emit(i)
                delay(1000)
            }
        }.flowOn(Dispatchers.IO).onCompletion {
            tvGetCode.text = "获取验证码"
            tvGetCode.isEnabled = true
        }.collect {
            tvGetCode.text = "${it}s"
        }
    }
}