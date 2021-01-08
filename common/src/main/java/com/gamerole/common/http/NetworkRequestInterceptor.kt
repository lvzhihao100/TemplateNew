package com.gamerole.common.http

import android.text.TextUtils
import com.gamerole.common.DataStoreConfig
import com.gamerole.common.util.DataStoreUtils
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class NetworkRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
//        if (originalRequest.body == null || originalRequest.header("Content-Encoding") != null) {
//            return chain.proceed(originalRequest)
//        }

        var token = DataStoreUtils.getSyncData(DataStoreConfig.TOKEN, "")
        if (!TextUtils.isEmpty(token)) {

            val compressedRequest: Request = originalRequest.newBuilder()
                .header("token", token)
                .build()
            return chain.proceed(compressedRequest)
        } else {
            return chain.proceed(originalRequest)
        }
    }
}