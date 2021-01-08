package com.gamerole.common.box.verifier;

import android.text.TextUtils;

import com.github.yoojia.inputs.EmptyableVerifier;

public class NumberLetterVerifier extends EmptyableVerifier {
    @Override
    public boolean performTestNotEmpty(String notEmptyInput) throws Exception {
//        String strPattern = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,18}$";
        String strPattern = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]*$";
        if (TextUtils.isEmpty(strPattern)) {
            return false;
        } else {
            return notEmptyInput.matches(strPattern);
        }
    }
}
