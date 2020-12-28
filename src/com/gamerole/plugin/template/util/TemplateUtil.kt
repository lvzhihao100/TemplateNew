package com.gamerole.plugin.template.util

import com.gamerole.plugin.template.TemplateConfig
import java.io.File

object TemplateUtil {






    @JvmStatic
    fun generateBuildGradle(path: String) {
        var file = File(path)
        var content = """
plugins {
    id 'com.android.library'
    id 'kotlin-android'
    id 'kotlin-kapt'
    id 'dagger.hilt.android.plugin'
}

android {
    compileSdkVersion 30
    defaultConfig {
        minSdkVersion 21
        targetSdkVersion 30
        versionCode 1
        versionName "1"
        kapt { arguments { arg("AROUTER_MODULE_NAME", project.getName()) } }
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    resourcePrefix "${TemplateConfig.module}"
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = '1.8'
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    /*这个必须跟着，只放在common里会报错*/
    api "com.google.dagger:hilt-android:2.28-alpha"
    kapt "com.google.dagger:hilt-android-compiler:2.28-alpha"
    kapt 'androidx.hilt:hilt-compiler:1.0.0-alpha02'
    kapt 'com.alibaba:arouter-compiler:1.2.2'

    api project(path: ':commom')



}
        """.trimIndent()
        FileUtil.writeFile(file, content)
    }

    @JvmStatic
    fun generateGitIgnore(path: String) {
        var file = File(path)
        var content = """
/build
        """.trimIndent()
        FileUtil.writeFile(file, content)
    }

    @JvmStatic
    fun generateProguard(path: String) {
        var file = File(path)
        var content = """

        """.trimIndent()
        FileUtil.writeFile(file, content)
    }

    @JvmStatic
    fun generateAndroidManifest(path: String) {
        var file = File(path)
        var content = """
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.gamerole.${TemplateConfig.module}">

    <application>

    </application>

</manifest>
        """.trimIndent()
        FileUtil.writeFile(file, content)
    }

    @JvmStatic
    fun generateColorXml(path: String) {
        var file = File(path)
        var content = """
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <!--换肤产品，颜色-->
    <color name="${TemplateConfig.module}TextTitle">@color/commonTextTitle</color>
    <color name="${TemplateConfig.module}TextContent">@color/commonTextContent</color>
    <color name="${TemplateConfig.module}TextHint">@color/commonTextHint</color>
    <color name="${TemplateConfig.module}BgDeep">@color/commonBgDeep</color>
    <color name="${TemplateConfig.module}Bg">@color/commonBg</color>
    <color name="${TemplateConfig.module}Select">@color/commonSelect</color>
    <color name="${TemplateConfig.module}UnSelect">@color/commonUnSelect</color>
    <color name="${TemplateConfig.module}Divider">@color/commonDivider</color>
    <color name="${TemplateConfig.module}DividerDeep">@color/commonDividerDeep</color>
    <color name="${TemplateConfig.module}App">@color/commonApp</color>
</resources>
        """.trimIndent()
        FileUtil.writeFile(file, content)
    }

    @JvmStatic
    fun generateStringXml(path: String) {
        var file = File(path)
        var content = """
<?xml version="1.0" encoding="utf-8"?>
<resources>

</resources>
        """.trimIndent()
        FileUtil.writeFile(file, content)
    }

    @JvmStatic
    fun generateSetting(path: String) {
        var file = File(path)
        var content = (FileUtil.readFileNew(path) ?: "") + """
include ':${TemplateConfig.module}'
        """.trimIndent()
        FileUtil.writeFile(file, content)
    }

    @JvmStatic
    fun generateNetworkModuleKTL(path: String) {
        var file = File(path)
        var content = """
package com.gamerole.${TemplateConfig.module}.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.gamerole.${TemplateConfig.module}.service.HttpService
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideHttpService(retrofit: Retrofit): HttpService {
        return retrofit.create(HttpService::class.java)
    }
}
        """.trimIndent()
        FileUtil.writeFile(file, content)
    }

    @JvmStatic
    fun generateHttpServiceKTL(path: String) {
        var file = File(path)
        var content = """
package com.gamerole.${TemplateConfig.module}.service

import com.gamerole.commom.entity.HttpData
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HttpService {


}
        """.trimIndent()
        FileUtil.writeFile(file, content)
    }

    @JvmStatic
    fun generateRepository(path: String) {
        var file = File(path)
        var content = """
package com.gamerole.${TemplateConfig.currentModule}.repository

import com.gamerole.${TemplateConfig.currentModule}.service.HttpService
import javax.inject.Inject

class ${TemplateConfig.activityName.capitalize()}Repository @Inject constructor(private var httpService: HttpService) {

}
        """.trimIndent()
        FileUtil.writeFile(file, content)
    }

    @JvmStatic
    fun generateFragmentRepository(path: String) {
        var file = File(path)
        var content = """
package com.gamerole.${TemplateConfig.currentModule}.repository

import com.gamerole.${TemplateConfig.currentModule}.service.HttpService
import javax.inject.Inject

class ${TemplateConfig.fragmentName.capitalize()}FragmentRepository @Inject constructor(private var httpService: HttpService) {

}
        """.trimIndent()
        FileUtil.writeFile(file, content)
    }

    @JvmStatic
    fun generateFragment(path: String) {

        var file = File(path)
        if (!file.parentFile.exists()) {
            file.parentFile.mkdirs()
        }
        var content = """
package com.gamerole.${TemplateConfig.currentModule}.ui.fragment

import androidx.fragment.app.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.gamerole.commom.RoutePath
import com.gamerole.commom.base.BaseFragment
import com.gamerole.${TemplateConfig.currentModule}.databinding.${TemplateConfig.currentModule.capitalize()}Fragment${TemplateConfig.fragmentName.capitalize()}Binding
import com.gamerole.${TemplateConfig.currentModule}.viewmodel.${TemplateConfig.fragmentName.capitalize()}FragmentViewModel
import com.hi.dhl.binding.viewbind
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = RoutePath.${TemplateConfig.currentModule.toUpperCase()}_FRAGMENT${TemplateConfig.fragmentName.underLine().toUpperCase()})
class ${TemplateConfig.fragmentName.capitalize()}Fragment : BaseFragment(R.layout.${TemplateConfig.currentModule.toLowerCase() + "_fragment" + FileUtil.underLine(TemplateConfig.fragmentName)}) {

    private val viewModel${TemplateConfig.fragmentName.capitalize()}Fragment: ${TemplateConfig.fragmentName.capitalize()}FragmentViewModel by viewModels()
    override fun getViewModel() = viewModel${TemplateConfig.fragmentName.capitalize()}Fragment
    private val binding: ${TemplateConfig.currentModule.capitalize()}Fragment${TemplateConfig.fragmentName.capitalize()}Binding by viewbind()

    override fun initView() {
        with(binding) {
        }
    }
    override fun initData() {

    }
}
        """.trimIndent()
        FileUtil.writeFile(file, content)
    }

    @JvmStatic
    fun generateActivity(path: String) {
        var file = File(path)
        var content = """
package com.gamerole.${TemplateConfig.currentModule}.ui

import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.gamerole.commom.RoutePath
import com.gamerole.commom.base.BaseActivity
import com.gamerole.${TemplateConfig.currentModule}.databinding.${TemplateConfig.currentModule.capitalize()}Activity${TemplateConfig.activityName.capitalize()}Binding
import com.gamerole.${TemplateConfig.currentModule}.viewmodel.${TemplateConfig.activityName.capitalize()}ViewModel
import com.hi.dhl.binding.viewbind
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = RoutePath.${TemplateConfig.currentModule.toUpperCase()}${TemplateConfig.activityName.underLine().toUpperCase()})
class ${TemplateConfig.activityName.capitalize()}Activity : BaseActivity() {
    override fun getImmersion() = false

    private val viewModel${TemplateConfig.activityName.capitalize()}: ${TemplateConfig.activityName.capitalize()}ViewModel by viewModels()
    override fun getViewModel() = viewModel${TemplateConfig.activityName.capitalize()}
    private val binding: ${TemplateConfig.currentModule.capitalize()}Activity${TemplateConfig.activityName.capitalize()}Binding by viewbind()

    override fun initView() {
        with(binding) {
        }
    }
    override fun initData() {

    }
}
        """.trimIndent()
        FileUtil.writeFile(file, content)
    }

    @JvmStatic
    fun generateFragmentViewModel(path: String) {
        var file = File(path)
        var content = """
package com.gamerole.${TemplateConfig.currentModule}.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import com.gamerole.commom.base.BaseViewModel
import com.gamerole.${TemplateConfig.currentModule}.repository.${TemplateConfig.fragmentName.capitalize()}FragmentRepository

class ${TemplateConfig.fragmentName.capitalize()}FragmentViewModel @ViewModelInject constructor(private var repository: ${TemplateConfig.fragmentName.capitalize()}FragmentRepository) :
    BaseViewModel() {

}
        """.trimIndent()
        FileUtil.writeFile(file, content)
    }

    @JvmStatic
    fun generateViewModel(path: String) {
        var file = File(path)
        var content = """
package com.gamerole.${TemplateConfig.currentModule}.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import com.gamerole.commom.base.BaseViewModel
import com.gamerole.${TemplateConfig.currentModule}.repository.${TemplateConfig.activityName.capitalize()}Repository

class ${TemplateConfig.activityName.capitalize()}ViewModel @ViewModelInject constructor(private var repository: ${TemplateConfig.activityName.capitalize()}Repository) :
    BaseViewModel() {

}
        """.trimIndent()
        FileUtil.writeFile(file, content)
    }

    @JvmStatic
    fun generateFragmentXml(path: String) {

        var file = File(path)
        var content = """
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    xmlns:tools="http://schemas.android.com/tools"
    android:orientation="vertical"
    android:background="@color/commonBgDeep"
    tools:context=".ui.fragment.${TemplateConfig.fragmentName.capitalize()}Fragment"
    android:layout_height="match_parent">

</LinearLayout>
        """.trimIndent()
        FileUtil.writeFile(file, content)
    }

    @JvmStatic
    fun generateXml(path: String) {

        var file = File(path)
        var content = """
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    xmlns:tools="http://schemas.android.com/tools"
    android:orientation="vertical"
    android:background="@color/commonBgDeep"
    tools:context=".ui.${TemplateConfig.activityName}Activity"
    android:layout_height="match_parent">
    <include layout="@layout/common_include_head" android:id="@+id/llIncludeHead" />

</LinearLayout>
        """.trimIndent()
        FileUtil.writeFile(file, content)
    }
}