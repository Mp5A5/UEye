package www.mp5a5.com.ueye.net

import www.mp5a5.com.ueye.util.SpUtils


/**
 * @describe
 * @author ：king9999 on 2018/6/20 15：45
 * @email：wwb199055@enn.cn
 */
object ApiConfig {
    
    private var APP_TOKEN = "app_token"
    private var DEFAULT_TIMEOUT: Long = 10000
    private var URL: String = ""
    
    var serverUrl: String
        get() = URL
        set(value) {
            URL = value
        }
    
    var appToken: String
        get() = SpUtils.getString(APP_TOKEN)
        set(token) {
            SpUtils.setString(APP_TOKEN, token)
        }
    
    
    var defaultTime: Long
        get() = DEFAULT_TIMEOUT
        set(value) {
            DEFAULT_TIMEOUT = value
        }
}

