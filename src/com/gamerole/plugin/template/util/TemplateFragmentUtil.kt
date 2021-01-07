package com.gamerole.plugin.template.util

import com.gamerole.plugin.template.TemplateConfig
import java.io.File
import java.util.regex.Pattern

object TemplateFragmentUtil {
    @JvmStatic
    fun generateFragmentConst(path: String) {
        var file = File(path)
        var oldContent = FileUtil.readFileNew(path)
        var content = """
       const val ${TemplateConfig.currentModule.toUpperCase()}"_FRAGMENT"+${TemplateConfig.fragmentName.underLine().toUpperCase()}="/${TemplateConfig.currentModule.toLowerCase()}/fragment${TemplateConfig.fragmentName}"
        """.trimIndent()
        //
        var pattern = Pattern.compile("const val ${TemplateConfig.currentModule.toUpperCase()}_[\\s\\S].*?[A-Z|a-z]\"", Pattern.DOTALL)
        var matcher = pattern.matcher(oldContent)
        if (matcher.find()) {
            var lastMatch = matcher.group(matcher.groupCount())
            oldContent = oldContent?.replace(lastMatch, lastMatch + content)
        } else {
            var patternNew = Pattern.compile("""(class RoutePath[\s\S].*?)}""".trimIndent(), Pattern.DOTALL)
            var matcherNew = patternNew.matcher(oldContent)
            if (matcherNew.find()) {
                var oldReplace = matcherNew.group()
                var newReplace = matcherNew.group(1) + "\t"+content + "\n\t}"
                oldContent = oldContent?.replace(oldReplace, newReplace)
            }
        }

        FileUtil.writeFile(file, oldContent!!)
    }

    @JvmStatic
    fun generateFragmentRepository(path: String) {
        var file = File(path)
        var content = """
package com.gamerole.${TemplateConfig.currentModule}.repository

import com.gamerole.${TemplateConfig.currentModule}.service.HttpService
import javax.inject.Inject
import com.skydoves.sandwich.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import com.gamerole.common.http.Resource
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
import com.gamerole.common.RoutePath
import com.gamerole.common.base.BaseFragment
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
    fun generateFragmentViewModel(path: String) {
        var file = File(path)
        var content = """
package com.gamerole.${TemplateConfig.currentModule}.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import com.gamerole.common.base.BaseViewModel
import com.gamerole.${TemplateConfig.currentModule}.repository.${TemplateConfig.fragmentName.capitalize()}FragmentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gamerole.common.entity.HttpData
import com.gamerole.common.http.NetworkAdapter
import com.gamerole.common.http.Resource

class ${TemplateConfig.fragmentName.capitalize()}FragmentViewModel @ViewModelInject constructor(private var repository: ${TemplateConfig.fragmentName.capitalize()}FragmentRepository) :
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
}