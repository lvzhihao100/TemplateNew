package com.gamerole.plugin.template.util

import com.gamerole.plugin.template.TemplateConfig
import java.io.File
import java.util.regex.Pattern

object TemplateDialogUtil {
    @JvmStatic
    fun generateDialogConst(path: String) {
        var file = File(path)
        var oldContent = FileUtil.readFileNew(path)
        var content = """
       const val ${TemplateConfig.currentModule.toUpperCase()}"_DIALOG"+${TemplateConfig.dialogName.underLine().toUpperCase()}="/${TemplateConfig.currentModule.toLowerCase()}/dialog${TemplateConfig.dialogName}"
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
    fun generateDialogRepository(path: String) {
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

class ${TemplateConfig.dialogName.capitalize()}DialogRepository @Inject constructor(private var httpService: HttpService) {

}
        """.trimIndent()
        FileUtil.writeFile(file, content)
    }

    @JvmStatic
    fun generateDialog(path: String) {

        var file = File(path)
        if (!file.parentFile.exists()) {
            file.parentFile.mkdirs()
        }
        var content = """
package com.gamerole.${TemplateConfig.currentModule}.ui.dialog

import androidx.fragment.app.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.gamerole.commom.RoutePath
import com.gamerole.commom.base.BaseDialog
import com.gamerole.${TemplateConfig.currentModule}.databinding.${TemplateConfig.currentModule.capitalize()}Dialog${TemplateConfig.dialogName.capitalize()}Binding
import com.gamerole.${TemplateConfig.currentModule}.viewmodel.${TemplateConfig.dialogName.capitalize()}DialogViewModel
import com.hi.dhl.binding.viewbind
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = RoutePath.${TemplateConfig.currentModule.toUpperCase()}_DIALOG${TemplateConfig.dialogName.underLine().toUpperCase()})
class ${TemplateConfig.dialogName.capitalize()}Dialog : BaseDialog() {

    private val viewModel${TemplateConfig.dialogName.capitalize()}Dialog: ${TemplateConfig.dialogName.capitalize()}DialogViewModel by viewModels()
    override fun getViewModel() = viewModel${TemplateConfig.dialogName.capitalize()}Dialog
    private val binding: ${TemplateConfig.currentModule.capitalize()}Dialog${TemplateConfig.dialogName.capitalize()}Binding by viewbind()
    override fun getViewBinding() = binding

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
    fun generateDialogViewModel(path: String) {
        var file = File(path)
        var content = """
package com.gamerole.${TemplateConfig.currentModule}.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import com.gamerole.commom.base.BaseViewModel
import com.gamerole.${TemplateConfig.currentModule}.repository.${TemplateConfig.dialogName.capitalize()}DialogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gamerole.commom.entity.HttpData
import com.gamerole.commom.http.NetworkAdapter
import com.gamerole.commom.http.Resource

class ${TemplateConfig.dialogName.capitalize()}DialogViewModel @ViewModelInject constructor(private var repository: ${TemplateConfig.dialogName.capitalize()}DialogRepository) :
    BaseViewModel() {

}
        """.trimIndent()
        FileUtil.writeFile(file, content)
    }

    @JvmStatic
    fun generateDialogXml(path: String) {

        var file = File(path)
        var content = """
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    xmlns:tools="http://schemas.android.com/tools"
    android:orientation="vertical"
    android:background="@color/commonBgDeep"
    tools:context=".ui.dialog.${TemplateConfig.dialogName.capitalize()}Dialog"
    android:layout_height="match_parent">

</LinearLayout>
        """.trimIndent()
        FileUtil.writeFile(file, content)
    }
}