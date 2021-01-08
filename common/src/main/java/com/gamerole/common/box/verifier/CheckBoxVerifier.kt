package com.gamerole.common.box.verifier

import com.github.yoojia.inputs.EmptyableVerifier

class CheckBoxVerifier : EmptyableVerifier() {
    @Throws(Exception::class)
    override fun performTestNotEmpty(notEmptyInput: String): Boolean {
        return java.lang.Boolean.valueOf(notEmptyInput)
    }
}