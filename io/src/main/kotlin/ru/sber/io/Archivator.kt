package ru.sber.io

import java.io.*
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream


/**
 * Реализовать методы архивации и разархивации файла.
 * Для реализиации использовать ZipInputStream и ZipOutputStream.
 */
class Archivator {

    /**
     * Метод, который архивирует файл logfile.log в архив logfile.zip.
     * Архив должен располагаться в том же каталоге, что и исходной файл.
     */
    fun zipLogfile() {
        val files: Array<String> = arrayOf(
            "io/logs/22.01.2001/22-01-2001-1.log",
            "io/logs/22.01.2001/22-01-2001-2.log",
            "io/logs/23.01.2001/23-01-2001-1.log",
            "io/logs/23.01.2001/23-01-2001-2.log",
        )
        for (file in files) {
        val myFile = File(file.substring(0,file.lastIndexOf("."))+".zip")
        myFile.createNewFile()
        ZipOutputStream(BufferedOutputStream(FileOutputStream(myFile, false))).use { out ->

                FileInputStream(file).use { fi ->
                    BufferedInputStream(fi).use { origin ->
                        val entry = ZipEntry(file.substring(file.lastIndexOf("/")))
                        out.putNextEntry(entry)
                        origin.copyTo(out, 1024)
                    }
                }
            }
        }

    }

    /**
     * Метод, который извлекает файл из архива.
     * Извлечь из архива logfile.zip файл и сохарнить его в том же каталоге с именем unzippedLogfile.log
     */
    fun unzipLogfile() {
        val files: Array<String> = arrayOf(
            "io/logs/22.01.2001/22-01-2001-1.zip",
            "io/logs/22.01.2001/22-01-2001-2.zip",
            "io/logs/23.01.2001/23-01-2001-1.zip",
            "io/logs/23.01.2001/23-01-2001-2.zip",
        )
        for (file in files) {
            val zipFile = ZipFile(file)

            val entries = zipFile.entries()

            while (entries.hasMoreElements()) {
                val entry = entries.nextElement()
                val stream = zipFile.getInputStream(entry)
                val myFile = File(file.substring(0,file.lastIndexOf("."))+"(1).log")
                FileOutputStream(myFile,false).use { fo -> fo.write(stream.readAllBytes()) }
            }
        }

    }

}