package com.gamerole.plugin.template

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.editor.impl.EditorImpl
import com.intellij.openapi.ui.Messages

class Main : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        Messages.showMessageDialog(TemplateConfig.project, "测试", "提示", Messages.getInformationIcon())
        val mEditor = e.getData(PlatformDataKeys.EDITOR)
        val project = e.getRequiredData(CommonDataKeys.PROJECT)
        if (null == mEditor) {
            Messages.showMessageDialog(TemplateConfig.project, "请先用鼠标选中要操作的模块", "提示", Messages.getInformationIcon())
            return
        }
        val substring = (mEditor as EditorImpl).virtualFile.path.substring(project.basePath!!.length + 1)
        TemplateConfig.currentModule = substring.substring(0, substring.indexOf("/"))
        TemplateConfig.basePath=project.basePath
        TemplateConfig.project=project
        val dialog = MainDialog()
        dialog.pack()
        dialog.isVisible = true
    }
}