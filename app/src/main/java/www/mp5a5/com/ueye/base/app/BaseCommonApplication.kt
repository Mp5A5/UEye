package www.mp5a5.com.ueye.base.app

import android.app.Application
import www.mp5a5.com.ueye.net.ApiConfig
import www.mp5a5.com.ueye.util.AppContextUtils

/**
 * @describe
 * @author ：king9999 on 2018/6/21 10：06
 * @email：wwb199055@enn.cn
 */
abstract class BaseCommonApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        AppContextUtils.init(this)
        ApiConfig.serverUrl = setBaseUrl()
    }
    
    abstract fun setBaseUrl(): String
}