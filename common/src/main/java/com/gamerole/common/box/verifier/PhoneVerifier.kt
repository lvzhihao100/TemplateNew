package com.gamerole.common.box.verifier

import com.github.yoojia.inputs.EmptyableVerifier
import com.github.yoojia.inputs.Texts

class PhoneVerifier : EmptyableVerifier() {
    @Throws(Exception::class)
    override fun performTestNotEmpty(notEmptyInput: String): Boolean {

        return Texts.regexMatch(notEmptyInput, "^\\d*$")
    }
}