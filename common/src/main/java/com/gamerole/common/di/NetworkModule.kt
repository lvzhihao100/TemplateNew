package com.gamerole.common.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.gamerole.common.BuildConfig
import com.gamerole.common.glide.SSLSocketFactoryCompat
import com.gamerole.common.http.NetworkRequestInterceptor
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.inject.Singleton
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideSSLSocketFactory(x509TrustManager: X509TrustManager): SSLSocketFactory {
        return SSLSocketFactoryCompat(x509TrustManager)
    }

    @Provides
    @Singleton
    fun provideHostnameVerifier(): HostnameVerifier {
        return HostnameVerifier { _, _ -> true }
    }

    @Provides
    @Singleton
    fun provideX509TrustManager(): X509TrustManager {
        return object : X509TrustManager {
            @Throws(CertificateException::class)
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
            }

            @Throws(CertificateException::class)
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        sslSocketFactory: SSLSocketFactory,
        x509TrustManager: X509TrustManager
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .sslSocketFactory(sslSocketFactory, x509TrustManager)
            .addNetworkInterceptor(StethoInterceptor())
            .addNetworkInterceptor(NetworkRequestInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.IP)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory())
            .build()
    }

}

@EntryPoint
@InstallIn(ApplicationComponent::class)
interface ExampleContentProviderEntryPoint {
    fun okHttpClient(): OkHttpClient
}