package com.gamerole.common.mvchelper

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.gamerole.common.R
import com.gamerole.common.base.App
import com.gamerole.common.glide.GlideApp
import com.shizhefei.mvc.ILoadViewFactory
import com.shizhefei.mvc.ILoadViewFactory.*
import com.shizhefei.view.vary.VaryViewHelper

/**
 * Created by lvzhihao on 2016/11/24.
 */
class LoadViewFactory : ILoadViewFactory {
    override fun madeLoadMoreView(): ILoadMoreView {
        return LoadMoreHelper()
    }

    override fun madeLoadView(): ILoadView {
        return LoadViewHelper()
    }

    private class LoadMoreHelper : ILoadMoreView {
        protected var footView: TextView? = null
        protected var onClickRefreshListener: View.OnClickListener? = null
        override fun init(
            footViewHolder: FootViewAdder,
            onClickRefreshListener: View.OnClickListener
        ) {
            val contentView = footViewHolder.contentView
            val context = contentView.context
            val textView = TextView(context)
            textView.setTextColor(Color.GRAY)
            textView.setPadding(0, dip2px(context, 16f), 0, dip2px(context, 16f))
            textView.gravity = Gravity.CENTER
            footViewHolder.addFootView(textView)
            footView = textView
            this.onClickRefreshListener = onClickRefreshListener
            showNormal()
        }

        override fun showNormal() {
            footView!!.visibility = View.VISIBLE
            footView!!.text = "点击加载更多"
            footView!!.setOnClickListener(onClickRefreshListener)
        }

        override fun showLoading() {
            footView!!.visibility = View.VISIBLE
            footView!!.text = "正在加载中.."
            footView!!.setOnClickListener(null)
        }

        override fun showFail(exception: Exception) {
            footView!!.visibility = View.VISIBLE
            footView!!.text = "加载失败，点击重新加载"
            footView!!.setOnClickListener(onClickRefreshListener)
        }

        override fun showNomore() {
            footView!!.visibility = View.GONE
            footView!!.text = ""
            footView!!.setOnClickListener(null)
        }
    }

    private class LoadViewHelper : ILoadView {
        private var helper: VaryViewHelper? = null
        private var onClickRefreshListener: View.OnClickListener? = null
        private var context: Context? = null
        override fun init(switchView: View, onClickRefreshListener: View.OnClickListener) {
            context = switchView.context.applicationContext
            this.onClickRefreshListener = onClickRefreshListener
            helper = VaryViewHelper(switchView)
        }

        override fun restore() {
            helper!!.restoreView()
        }

        override fun showLoading() {
            val loadingView =
                LayoutInflater.from(context).inflate(R.layout.common_mvc_loading_view, null)
            val ivLoading = loadingView.findViewById<ImageView>(R.id.ivLoading)
            GlideApp.with(App.INSTANCE)
                .load(R.mipmap.common_loading)
                .into(ivLoading)
            helper!!.showLayout(loadingView)
        }

        override fun tipFail(exception: Exception) {
            Toast.makeText(App.INSTANCE, exception.cause!!.message, Toast.LENGTH_LONG).show()
        }

        override fun showFail(exception: Exception) {
            val msg = exception.cause!!.message
            val context = helper!!.context
            val errorView =
                LayoutInflater.from(context).inflate(R.layout.common_mvc_error_view, null)
            errorView.findViewById<View>(R.id.btRetry).setOnClickListener(onClickRefreshListener)
            (errorView.findViewById<View>(R.id.tvError) as TextView).text =
                if (TextUtils.isEmpty(msg)) "网络请求错误" else msg
            helper!!.showLayout(errorView)
        }

        override fun showEmpty() {
            val context = helper!!.context
            val emptyView =
                LayoutInflater.from(context).inflate(R.layout.common_mvc_empty_view, null)
            emptyView.setOnClickListener(onClickRefreshListener)
            emptyView.findViewById<View>(R.id.ivEmpty).setOnClickListener(onClickRefreshListener)
            helper!!.showLayout(emptyView)
        }
    }

    companion object {
        /**
         * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
         */
        fun dip2px(context: Context, dpValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (dpValue * scale + 0.5f).toInt()
        }
    }
}