package com.gamerole.common.box.verifier;

import com.github.yoojia.inputs.EmptyableVerifier;

import static com.github.yoojia.inputs.Texts.regexMatch;

public class SimpleMobileVerifier extends EmptyableVerifier {

    @Override
    public boolean performTestNotEmpty(String notEmptyInput) throws Exception {
        return regexMatch(notEmptyInput, "^[1]\\d{10}$");
    }
}
