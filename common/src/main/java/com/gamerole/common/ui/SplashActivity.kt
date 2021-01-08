package com.gamerole.common.ui

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.gamerole.common.DataStoreConfig
import com.gamerole.common.R
import com.gamerole.common.RoutePath
import com.gamerole.common.util.DataStoreUtils


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!this.isTaskRoot) {
            val mainIntent = intent
            val action = mainIntent.action
            if (mainIntent.hasCategory(Intent.CATEGORY_LAUNCHER) && action == Intent.ACTION_MAIN) {
                finish()
                return
            }
        }
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        NavigationBarStatusBar(this, true)
        setContentView(R.layout.common_activity_splash)
        object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                if (TextUtils.isEmpty(DataStoreUtils.readStringData(DataStoreConfig.TOKEN, ""))) {
                    ARouter.getInstance().build(RoutePath.LOGIN_LOGIN).navigation()
                } else {
                    ARouter.getInstance().build(RoutePath.APP_MAIN).navigation()
                }
                finish()
            }
        }.start()


    }

    companion object {
        /**
         * 导航栏，状态栏隐藏
         *
         * @param activity
         */
        fun NavigationBarStatusBar(activity: Activity, hasFocus: Boolean) {
            if (hasFocus && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                val decorView = activity.window.decorView
                decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            }
        }
    }
}