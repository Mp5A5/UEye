package www.mp5a5.com.ueye.base.app

import android.os.Environment
import www.mp5a5.com.ueye.util.ConstantUtil
import zlc.season.rxdownload3.core.DownloadConfig
import zlc.season.rxdownload3.extension.ApkInstallExtension
import zlc.season.rxdownload3.extension.ApkOpenExtension
import zlc.season.rxdownload3.http.OkHttpClientFactoryImpl
import zlc.season.rxdownload3.notification.NotificationFactoryImpl

/**
 * @describe
 * @author ：king9999 on 2018/6/21 13：57
 * @email：wwb199055@enn.cn
 */
class BaseApplication : BaseCommonApplication() {
    
    override fun setBaseUrl(): String {
        return ConstantUtil.BASE_ULR
    }
    
    override fun onCreate() {
        super.onCreate()
        
        val path: String = Environment.getExternalStorageDirectory().absoluteFile.toString() + "UEyeCache"
        val builder = DownloadConfig.Builder.create(this)
                //.setFps(20)                                      //设置更新频率
                .setDebug(true)
                .enableAutoStart(true)                   //自动开始下载
                //.setDefaultPath(path)                          //设置默认的下载地址
                //.enableDb(true)                             //启用数据库
                //.setDbActor(CustomSqliteActor(this))       //自定义数据库
                .enableService(true)                 //启用Service
                .enableNotification(true)           //启用Notification
                .setNotificationFactory(NotificationFactoryImpl()) 	    //自定义通知
                .setOkHttpClientFacotry(OkHttpClientFactoryImpl())
                .addExtension(ApkInstallExtension::class.java)
                .addExtension(ApkOpenExtension::class.java)
        
        DownloadConfig.init(builder)
    }
    
    
}

