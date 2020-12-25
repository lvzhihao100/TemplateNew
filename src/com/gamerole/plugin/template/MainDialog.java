package com.gamerole.plugin.template;


import com.gamerole.plugin.template.util.FileUtil;
import com.intellij.openapi.ui.Messages;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.gamerole.plugin.template.util.TemplateUtil.*;


public class MainDialog extends JDialog {
    JPanel contentPane;
    JTextField etModule;
    JButton btModule;
    JTextField etActivity;
    JButton btActivity;
    JTextField etFragment;
    JButton btFragment;
    JTextPane tvDesc;

    private void createActivity() {
        String daimaPath = TemplateConfig.basePath + "/" + TemplateConfig.currentModule + "/" + TemplateConfig.gamerolePath + TemplateConfig.currentModule.toLowerCase();
        String resPath = TemplateConfig.basePath + "/" + TemplateConfig.currentModule + "/" + TemplateConfig.resPath;
        generateRepository(daimaPath + "/repository/" + FileUtil.capitalize(TemplateConfig.activityName) + "Repository.kt");
        generateActivity(daimaPath + "/ui/" + FileUtil.capitalize(TemplateConfig.activityName) + "Activity.kt");
        generateViewModel(daimaPath + "/viewmodel/" + FileUtil.capitalize(TemplateConfig.activityName) + "ViewModel.kt");
        generateXml(resPath + "/layout/" + TemplateConfig.currentModule.toLowerCase() + "_activity" + FileUtil.underLine(TemplateConfig.activityName) + ".xml");
    }

    private void createFragment() {
        String daimaPath = TemplateConfig.basePath + "/" + TemplateConfig.currentModule + "/" + TemplateConfig.gamerolePath + TemplateConfig.currentModule.toLowerCase();
        String resPath = TemplateConfig.basePath + "/" + TemplateConfig.currentModule + "/" + TemplateConfig.resPath;
        generateFragmentRepository(daimaPath + "/repository/" + FileUtil.capitalize(TemplateConfig.fragmentName) + "FragmentRepository.kt");
        generateFragment(daimaPath + "/ui/fragment/" + FileUtil.capitalize(TemplateConfig.fragmentName) + "Fragment.kt");
        generateFragmentViewModel(daimaPath + "/viewmodel/" + FileUtil.capitalize(TemplateConfig.fragmentName) + "FragmentViewModel.kt");
        generateFragmentXml(resPath + "/layout/" + TemplateConfig.currentModule.toLowerCase() + "_fragment" + FileUtil.underLine(TemplateConfig.fragmentName) + ".xml");
    }

    private void createModule(String basePath, String module) {
        createDir(basePath, module);
        createFile(basePath, module);
    }

    private void createFile(String basePath, String module) {
        String resPath = basePath + "/" + module + "/" + TemplateConfig.resPath;
        String daimaPath = basePath + "/" + module + "/" + TemplateConfig.gamerolePath + module.toLowerCase();
        generateBuildGradle(basePath + "/" + module + "/build.gradle");
        generateGitIgnore(basePath + "/" + module + "/.gitignore");
        generateProguard(basePath + "/" + module + "/proguard-rules.pro");
        generateAndroidManifest(basePath + "/" + module + "/src/main/AndroidManifest.xml");
        generateColorXml(resPath + "/values/colors.xml");
        generateStringXml(resPath + "/values/strings.xml");
        generateNetworkModuleKTL(daimaPath + "/di/NetworkModule.kt");
        generateHttpServiceKTL(daimaPath + "/service/HttpService.kt");
        generateSetting(basePath + "/settings.gradle");
    }

    private void createDir(String basePath, String module) {
        List dirs = new ArrayList<String>();
        String daimaPath = basePath + "/" + module + "/" + TemplateConfig.gamerolePath + module.toLowerCase();
        //java
        dirs.add(daimaPath + "/di");
        dirs.add(daimaPath + "/entity");
        dirs.add(daimaPath + "/network");
        dirs.add(daimaPath + "/repository");
        dirs.add(daimaPath + "/router");
        dirs.add(daimaPath + "/service");
        dirs.add(daimaPath + "/ui");
        dirs.add(daimaPath + "/ui/fragment");
        dirs.add(daimaPath + "/util");
        dirs.add(daimaPath + "/viewmodel");
        //res
        String resPath = basePath + "/" + module + "/" + TemplateConfig.resPath;
        dirs.add(resPath + "/drawable");
        dirs.add(resPath + "/layout");
        dirs.add(resPath + "/mipmap-xxxhdpi");
        dirs.add(resPath + "/values");
        dirs.forEach((Consumer<String>) s -> {
            File file = new File(s);
            if (file.mkdirs()) {
                tvDesc.setText(file.getPath() + "文件夹创建成功");
            }
        });
        tvDesc.setText("文件夹创建成功");

    }

    public static void main(String[] args) {
        MainDialog dialog = new MainDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public MainDialog() {
        setTitle("测试");
        setLocation(100, 100);
        setBounds(0, 0, 500, 500);
        setContentPane(contentPane);
        setModal(true);
        btModule.addActionListener(actionEvent -> {
            String text = etModule.getText();
            TemplateConfig.module = text;
            createModule(TemplateConfig.basePath, text);
            Messages.showMessageDialog(TemplateConfig.project, text + "模块创建完成", "提示", Messages.getInformationIcon());
        });
        btActivity.addActionListener(actionEvent -> {
            String text = etActivity.getText();
            TemplateConfig.activityName = text;
            createActivity();
            Messages.showMessageDialog(TemplateConfig.project, text + "Activity创建完成", "提示", Messages.getInformationIcon());
        });
        btFragment.addActionListener(actionEvent -> {
            String text = etFragment.getText();
            TemplateConfig.fragmentName = text;
            createFragment();
            Messages.showMessageDialog(TemplateConfig.project, text + "Fragment创建完成", "提示", Messages.getInformationIcon());
        });

    }
}