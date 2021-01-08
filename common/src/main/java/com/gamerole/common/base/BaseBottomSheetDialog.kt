package com.gamerole.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


abstract class BaseBottomSheetDialog :BottomSheetDialogFragment(){
    @Nullable
    override fun onCreateView(
        @NonNull inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }
    override fun onStart() {
        super.onStart()
//        //获取dialog对象
//        //获取dialog对象
//        val dialog = dialog as BottomSheetDialog?
//        //把windowsd的默认背景颜色去掉，不然圆角显示不见
//        //把windowsd的默认背景颜色去掉，不然圆角显示不见
//        dialog!!.window!!.findViewById<View>(android.support.R.id.design_bottom_sheet)
//            .setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        //获取diglog的根部局
//        //获取diglog的根部局
//        val bottomSheet = dialog!!.delegate.findViewById<FrameLayout>(R.id.design_bottom_sheet)
//        if (bottomSheet != null) {
//            //获取根部局的LayoutParams对象
//            val layoutParams = bottomSheet.layoutParams as CoordinatorLayout.LayoutParams
//            layoutParams.height = getPeekHeight()
//            //修改弹窗的最大高度，不允许上滑（默认可以上滑）
//            bottomSheet.layoutParams = layoutParams
//            val behavior = BottomSheetBehavior.from(bottomSheet)
//            //peekHeight即弹窗的最大高度
//            behavior.peekHeight = getPeekHeight()
//            // 初始为展开状态
//            behavior.state = BottomSheetBehavior.STATE_EXPANDED
//
//
//        }
        initView()
        initData()
        initEvent()
    }
    open fun initEvent() {}

    protected abstract val layoutId: Int
    open fun initData() {}
    open fun initView() {}
    /**
     * 弹窗高度，默认为屏幕高度的四分之三
     * 子类可重写该方法返回peekHeight
     *
     * @return height
     */
    protected fun getPeekHeight(): Int {
        val peekHeight = resources.displayMetrics.heightPixels
        //设置弹窗高度为屏幕高度的3/4
        return peekHeight - peekHeight / 3
    }
}