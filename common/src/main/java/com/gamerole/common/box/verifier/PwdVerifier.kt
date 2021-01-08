package com.gamerole.common.box.verifier

import com.github.yoojia.inputs.EmptyableVerifier
import com.github.yoojia.inputs.Texts

class PwdVerifier : EmptyableVerifier() {
    @Throws(Exception::class)
    override fun performTestNotEmpty(notEmptyInput: String): Boolean {
        return Texts.regexMatch(notEmptyInput, "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$")
    }
}