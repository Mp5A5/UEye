package www.mp5a5.com.ueye.util


import android.graphics.Bitmap
import android.text.TextUtils
import android.util.Log
import java.io.*
import java.nio.ByteBuffer
import java.nio.channels.FileChannel

class FileUtils {
    
    companion object {
        
        private val tag = "FileUtils"
        
        /**
         * 判断是否有sd卡
         *
         * @return true 有sd卡
         */
        fun hasSdcard(): Boolean {
            return android.os.Environment.getExternalStorageState() == android.os.Environment.MEDIA_MOUNTED
        }
        
        /**
         * 过滤文件并删除文件
         *
         * @param dir 目录
         */
        fun delete(dir: String, filenameFilter: FilenameFilter) {
            val f = File(dir)
            val files = f.list(filenameFilter)
            if (files != null && files.size > 0) {
                for (file in files) {
                    delFile(file)
                }
            }
        }
        
        /**
         * 删除单个文件
         *
         * @param fileName 要删除的文件的文件名
         * @return 单个文件删除成功返回true，否则返回false
         */
        fun delFile(fileName: String): Boolean {
            val file = File(fileName)
            // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
            return if (file.exists() && file.isFile) {
                file.delete()
            } else {
                false
            }
        }
        
        /**
         * 从文件读入输入流
         */
        fun getFileInputStream(filename: String?): InputStream? {
            var inputstream: InputStream? = null
            if (null == filename || filename.isEmpty()) {
                return null
            }
            
            try {
                val pfile = File(filename)
                if (pfile.exists() && !pfile.isDirectory) {
                    inputstream = FileInputStream(pfile)
                }
            } catch (e: IOException) {
                Log.e(tag, "getFileInputStream(): " + e.message)
                return null
            }
            
            return inputstream
        }
        
        /**
         * 复制文件
         */
        fun copyFile(srcFile: File?, destFile: File?): Boolean {
            var destFile = destFile
            //先经过错误过滤
            if (null == srcFile || null == destFile || !srcFile.exists() || srcFile.isDirectory) {
                return false
            }
            
            if (!destFile.exists()) {
                destFile = createFile(destFile.absolutePath)
                return false
            }
            
            var isOK = true
            var out: FileChannel? = null
            var `in`: FileChannel? = null
            try {
                out = FileOutputStream(destFile).channel
                `in` = FileInputStream(srcFile).channel
                val buffer = ByteBuffer.allocate(102400)
                var position = 0
                var length = 0
                while (true) {
                    length = `in`!!.read(buffer, position.toLong())
                    if (length <= 0) {
                        break
                    }
                    buffer.flip()
                    out!!.write(buffer, position.toLong())
                    position += length
                    buffer.clear()
                }
            } catch (e: Exception) {
                isOK = false
                Log.e(tag, "copyFile(): " + e.message)
            } finally {
                if (out != null) {
                    try {
                        out.close()
                    } catch (e: IOException) {
                        Log.e(tag, "copyFile(): " + e.message)
                    }
                    
                }
                if (`in` != null) {
                    try {
                        `in`.close()
                    } catch (e: IOException) {
                        Log.e(tag, "copyFile(): " + e.message)
                    }
                    
                }
            }
            return isOK
        }
        
        /**
         * 创建文件
         *
         * @param path 文件名 以“/”开头表示绝对路径
         * @return 文件File
         */
        fun createFile(path: String): File {
            var path = path
            if (path.startsWith("./")) {
                path = path.substring(2)
            }
            
            var file: File? = null
            // 是一个绝对路径文件
            if (path.startsWith("/")) {
                file = File(path)
            }
            
            if (file!!.exists()) { // 文件存在删掉存在文件
                file.delete()
            }
            
            try {
                file.createNewFile()
            } catch (e: Exception) {
                Log.e(tag, "createFile(): " + e.message)
                return try {
                    val parent = file.parent + "/"
                    val pfile = File(parent)
                    pfile.mkdirs()
                    file.createNewFile()
                    file
                } catch (x: Exception) {
                    Log.e(tag, "createFile(): " + e.message)
                    File("")
                }
                
            }
            
            return file
        }
        
        /**
         * 删除文件或目录
         */
        fun delFileOrDir(path: String): Boolean {
            val f = File(path)
            var ret = true
            
            if (!f.exists()) {
                ret = false
            } else if (f.exists()) {
                if (f.isDirectory) {
                    val files = f.listFiles()
                    for (i in files.indices) {
                        if (!delFileOrDir(files[i].path)) {
                            ret = false
                        }
                    }
                } else if (!f.delete()) {
                    ret = false
                }
            }
            return ret
        }
        
        /**
         * 按目录删除文件夹文件
         */
        fun deleteFolderFile(filePath: String, deleteThisPath: Boolean): Boolean {
            try {
                val file = File(filePath)
                if (file.isDirectory) {
                    val files = file.listFiles()
                    for (file1 in files) {
                        deleteFolderFile(file1.absolutePath, true)
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory) {
                        file.delete()
                    } else {
                        if (file.listFiles().size == 0) {
                            file.delete()
                        }
                    }
                }
                return true
            } catch (e: Exception) {
                e.printStackTrace()
                return false
            }
            
        }
        
        
        /**
         * 将bitmap保存到文件
         */
        fun saveBitmapToFile(mBitmap: Bitmap?, filepath: String?): Boolean {
            if (null == mBitmap || null == filepath || 0 == filepath.trim { it <= ' ' }.length) {
                return false
            }
            
            var result = true
            val file = createFile(filepath)
            try {
                val fos = FileOutputStream(file)
                result = mBitmap.compress(Bitmap.CompressFormat.JPEG, 85, fos)
                fos.flush()
                fos.close()
            } catch (e: IOException) {
                Log.e(tag, "saveBitmapToFile(): " + e.message)
                result = false
            }
            
            return result
        }
        
        /**
         * 获取文件目录
         * 这个算法 是按字符串取的 是没有对这个文件或者目录进行验证的
         */
        
        fun getFileDir(pathstr: String): String {
            if (!TextUtils.isEmpty(pathstr)) {
                // 获取相对路径
                val i = pathstr.lastIndexOf("/")
                return pathstr.substring(0, i)
            } else {
                return ""
            }
        }
        
        /**
         * 获取文件的后缀名
         * 这个算法 是按字符串取的 是没有对这个文件或者目录进行验证的  下面有个方法是读取真实的后缀名的方法
         */
        fun getExtName(s: String): String {
            val i = s.lastIndexOf(".")
            val leg = s.length
            return if (i > 0) if (i + 1 == leg) " " else s.substring(i, s.length) else " "
        }
        
        
        /**
         * 获得文件路径的文件名
         *
         * @return 文件名
         */
        fun getFileName(path: String): String {
            val i = path.lastIndexOf("/")
            val leg = path.length
            return if (i > 0) if (i + 1 == leg) "" else path.substring(i + 1, path.length) else " "
        }
        
        /**
         * 文件重命名
         *
         * @param oldname 原来的文件名
         * @param newname 新文件名
         */
        fun renameFileToPath(oldname: String, newname: String): Boolean {
            if (oldname != newname) {
                val oldfile = File(oldname)
                val newfile = File(newname)
                if (!oldfile.exists()) {
                    return false
                }
                
                return if (newfile.exists()) {
                    false
                } else {
                    oldfile.renameTo(newfile)
                }
            }
            return false
        }
        
        /**
         * 获取文件夹大小
         *
         * @param filePath 文件路径
         * @return 文件夹大小(单位为MB)
         */
        fun getFolderSize(filePath: String): Long {
            var size: Long = 0
            val file = File(filePath)
            
            try {
                val files = file.listFiles()
                for (i in files.indices) {
                    size += if (files[i].isDirectory) {
                        getFolderSize(files[i].absolutePath)
                    } else {
                        files[i].length()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            
            return size
        }
        
        
        /**
         * 获取指定文件夹内所有文件大小的和
         *
         * @param file 文件
         */
        @Throws(Exception::class)
        fun getFolderSize(file: File): Long {
            var size: Long = 0
            try {
                val fileList = file.listFiles()
                for (aFileList in fileList) {
                    size = if (aFileList.isDirectory) {
                        size + getFolderSize(aFileList)
                    } else {
                        size + aFileList.length()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            
            return size
        }
    }
}
