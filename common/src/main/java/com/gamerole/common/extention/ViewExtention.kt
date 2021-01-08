package com.gamerole.common.extention

import android.view.View
import org.jetbrains.anko.AnkoException

fun View.click(click: (View) -> Unit) {
    setOnClickListener { click.invoke(it) }
}

var android.widget.TextView.textColor: Int
    get() = throw AnkoException("Property does not have a getter")
    set(v) = setTextColor(v)
