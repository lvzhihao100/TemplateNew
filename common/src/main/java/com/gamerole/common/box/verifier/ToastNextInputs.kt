package com.gamerole.common.box.verifier

import com.gamerole.common.box.androidinput.ToastMessageDisplay
import com.github.yoojia.inputs.AndroidNextInputs
class ToastNextInputs : AndroidNextInputs() {
    init {
        setMessageDisplay(ToastMessageDisplay())
    }
}