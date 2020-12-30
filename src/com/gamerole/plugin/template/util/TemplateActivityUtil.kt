package com.gamerole.plugin.template.util

import com.gamerole.plugin.template.TemplateConfig
import java.io.File
import java.util.regex.Pattern
import java.util.regex.Pattern.DOTALL

object TemplateActivityUtil {

    @JvmStatic
    fun generateManifest(path: String) {
        var file = File(path)
        var oldContent = FileUtil.readFileNew(path)
        var content = """
        <activity android:name=".ui.${TemplateConfig.activityName.capitalize()}Activity" />
        """.trimIndent()
        oldContent = oldContent?.replace("</application>", "\t$content\n    </application>")

        FileUtil.writeFile(file, oldContent!!)
    }


    @JvmStatic
    fun generateConst(path: String) {
        var file = File(path)
        var oldContent = FileUtil.readFileNew(path)
        var content = """
       const val ${TemplateConfig.currentModule.toUpperCase()}${TemplateConfig.activityName.underLine().toUpperCase()}="/${TemplateConfig.currentModule.toLowerCase()}/${TemplateConfig.activityName.firstLow()}"
        """.trimIndent()
        //
        var pattern = Pattern.compile("const val ${TemplateConfig.currentModule.toUpperCase()}_[\\s\\S].*?[A-Z|a-z]\"", DOTALL)
        var matcher = pattern.matcher(oldContent)
        if (matcher.find()) {
            var lastMatch = matcher.group(matcher.groupCount())
            oldContent = oldContent?.replace(lastMatch, lastMatch + content)
        } else {
            var patternNew = Pattern.compile("""(class RoutePath[\s\S].*?)}""".trimIndent(), DOTALL)
            var matcherNew = patternNew.matcher(oldContent)
            if (matcherNew.find()) {
                var oldReplace = matcherNew.group()
                var newReplace = matcherNew.group(1) + "\t" + content + "\n\t}"
                oldContent = oldContent?.replace(oldReplace, newReplace)
            }
        }

        FileUtil.writeFile(file, oldContent!!)
    }

    @JvmStatic
    fun generateRepository(path: String) {
        var file = File(path)
        var content = """
package com.gamerole.${TemplateConfig.currentModule}.repository

import com.gamerole.${TemplateConfig.currentModule}.service.HttpService
import javax.inject.Inject
import com.skydoves.sandwich.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import com.gamerole.commom.http.Resource
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
    fun generateViewModel(path: String) {
        var file = File(path)
        var content = """
package com.gamerole.${TemplateConfig.currentModule}.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import com.gamerole.commom.base.BaseViewModel
import com.gamerole.${TemplateConfig.currentModule}.repository.${TemplateConfig.activityName.capitalize()}Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gamerole.commom.entity.HttpData
import com.gamerole.commom.http.NetworkAdapter
import com.gamerole.commom.http.Resource

class ${TemplateConfig.activityName.capitalize()}ViewModel @ViewModelInject constructor(private var repository: ${TemplateConfig.activityName.capitalize()}Repository) :
    BaseViewModel() {

}
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