package com.gamerole.common.glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.gamerole.common.base.App
import com.gamerole.common.di.ExampleContentProviderEntryPoint
import dagger.hilt.android.EntryPointAccessors
import java.io.InputStream

@GlideModule
class MyGlideModule : AppGlideModule() {

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        val hiltEntryPoint = EntryPointAccessors.fromApplication(
            App.INSTANCE,
            ExampleContentProviderEntryPoint::class.java
        )

        val okHttpClient = hiltEntryPoint.okHttpClient()

        registry.replace(
            GlideUrl::class.java,
            InputStream::class.java,
            OkHttpUrlLoader.Factory(okHttpClient)
        )
    }

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}