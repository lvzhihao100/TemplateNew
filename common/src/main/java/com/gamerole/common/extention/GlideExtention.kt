package com.gamerole.common.extention

import android.widget.ImageView
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import com.gamerole.common.base.App
import com.gamerole.common.glide.GlideApp
import com.gamerole.common.glide.GlideRoundTransformCenterCrop


fun requestOptions(): RequestOptions {
    return RequestOptions()
        .format(DecodeFormat.PREFER_ARGB_8888)

}

@JvmSynthetic
inline fun ImageView.image(
    data: Any?,
    builder: RequestOptions.() -> RequestOptions = { this }
) {

    val requestOptions = requestOptions()
    builder.invoke(requestOptions)
    GlideApp.with(App.INSTANCE)
        .load(data)
        .apply(requestOptions)
        .into(this)
}

@JvmSynthetic
inline fun ImageView.circle(
    data: Any?,
    builder: RequestOptions.() -> RequestOptions = { this }
) {

    val requestOptions = requestOptions().circleCrop()

    builder.invoke(requestOptions)
    GlideApp.with(App.INSTANCE)
        .load(data)
        .apply(requestOptions)
        .into(this)
}

@JvmSynthetic
inline fun ImageView.round(
    data: Any?,
    radius: Int = 20,
    builder: RequestOptions.() -> Unit = {}
) {
    val requestOptions = requestOptions()
    requestOptions.transform(GlideRoundTransformCenterCrop(radius))
    builder.invoke(requestOptions)

    GlideApp.with(App.INSTANCE)
        .load(data)
        .apply(requestOptions)
        .into(this)
}

