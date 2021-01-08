package com.gamerole.common.box.verifier

import com.github.yoojia.inputs.EmptyableVerifier
import com.github.yoojia.inputs.Texts

class EmailVerifier : EmptyableVerifier() {
    @Throws(Exception::class)
    override fun performTestNotEmpty(notEmptyInput: String): Boolean {
        return Texts.regexMatch(
            notEmptyInput,
            "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$"
        )
    }
}