package www.mp5a5.com.ueye.net.interceptor


import okhttp3.logging.HttpLoggingInterceptor
import www.mp5a5.com.kotlinmvp.util.LogUtils
import java.io.UnsupportedEncodingException

/**
 * @author ：王文彬 on 2018/5/23 13：24
 * @describe：
 * @email：wwb199055@126.com
 */
class LoggingInterceptor {
    
    companion object {
        //日志拦截器
        val loggingInterceptor: HttpLoggingInterceptor
            get() {
                val interceptor = HttpLoggingInterceptor { message ->
                    try {
                     /*   val msg= message.replace("%(?![0-9a-fA-F]{2})", "%25")
                        val m = msg.replace("\\", "%2B")
                        val text = URLDecoder.decode(m, "utf-8")*/
                        LogUtils.e("OKHttp-----", message)
                    } catch (e: UnsupportedEncodingException) {
                        e.printStackTrace()
                        LogUtils.e("OKHttp-----", message)
                    }
                }
                
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                return interceptor
            }
    }
    
    
}
