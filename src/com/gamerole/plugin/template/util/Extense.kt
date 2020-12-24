package com.gamerole.plugin.template.util

import java.util.regex.Pattern

inline fun String.underLine(): String {
    val humpPattern = Pattern.compile("[A-Z]")
    var matcher = humpPattern.matcher(this)
    var sb = StringBuffer()
    while (matcher.find()) {
        matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
    }
    matcher.appendTail(sb);
    return sb.toString();
}

fun main(args: Array<String>) {
    var a = "UnderLine"
    println(a.underLine())
}