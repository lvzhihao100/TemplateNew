package com.gamerole.plugin.template.util

import java.io.*


object FileUtil {
    @JvmStatic
    @Throws(IOException::class)
    fun readFile(path: String): String? {
        val file = File(path)
        if (!file.exists()) {
            return null
        }
        val fis = FileInputStream(file)
        var buf = ByteArray(1024)
        val sb = StringBuffer()
        while (fis.read(buf) != -1) {
            sb.append(String(buf, charset("utf-8")))
            buf = ByteArray(1024)//重新生成，避免和上次读取的数据重复
        }
        return sb.toString()
    }
    fun readFileNew(path: String): String? {
        val reader = BufferedReader(FileReader(path))
        var str: String?
        var stringBuilder=StringBuilder()
        while (reader.readLine().also { str = it } != null) {
            stringBuilder.append(str+"\n")
        }
        return stringBuilder.toString()
    }

    /**
     *  文件写入
     */
    @JvmStatic
    @Throws(IOException::class)
    fun writeFile(file: File, content: String) {
        if (!file.exists())
            file.createNewFile()
        val out = FileOutputStream(file, false)
        val sb = StringBuffer()
        sb.append(content)
        out.write(sb.toString().toByteArray(charset("utf-8")))
        out.close()
    }
    @JvmStatic
    fun capitalize(str: String):String{
        return str.capitalize()
    }
    @JvmStatic
    fun underLine(str: String):String{
        return str.underLine()
    }
}