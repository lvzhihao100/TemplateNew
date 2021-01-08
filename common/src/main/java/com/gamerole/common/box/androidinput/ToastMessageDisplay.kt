package com.gamerole.common.box.androidinput

import com.gamerole.common.util.ToastUtil
import com.github.yoojia.inputs.Input
import com.github.yoojia.inputs.MessageDisplay

class ToastMessageDisplay : MessageDisplay {
    override fun show(input: Input, message: String) {
        ToastUtil.showShort(message)
    }
}