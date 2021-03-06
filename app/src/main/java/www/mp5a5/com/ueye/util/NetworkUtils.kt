package www.mp5a5.com.ueye.util

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.telephony.TelephonyManager

/**
 * 作者：王文彬 on 2017/12/3 12：00
 * 邮箱：wwb199055@126.com
 *
 * 网络相关工具类
 */

class NetworkUtils private constructor() {
    
    init {
        throw UnsupportedOperationException("can't instantiate ...")
    }
    
    enum class NetworkType {
        NETWORK_WIFI,
        NETWORK_4G,
        NETWORK_3G,
        NETWORK_2G,
        NETWORK_UNKNOWN,
        NETWORK_NO
    }
    
    companion object {
        
        /**
         * 打开网络设置界面
         *
         * 3.0以下打开设置界面
         */
        fun openWirelessSettings() {
            if (android.os.Build.VERSION.SDK_INT > 10) {
                AppContextUtils.getContext().startActivity(Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS).setFlags(Intent
                        .FLAG_ACTIVITY_NEW_TASK))
            } else {
                AppContextUtils.getContext().startActivity(Intent(android.provider.Settings.ACTION_SETTINGS).setFlags(Intent
                        .FLAG_ACTIVITY_NEW_TASK))
            }
        }
        
        /**
         * 获取活动网络信息
         *
         * 需添加权限 `<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>`
         *
         * @return NetworkInfo
         */
        private val activeNetworkInfo: NetworkInfo?
            get() {
                val cm = AppContextUtils.getContext()
                        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                return cm.activeNetworkInfo
            }
        
        
        /**
         * 判断网络是否可用
         * <p>需添加权限 android.permission.ACCESS_NETWORK_STATE</p>
         */
        val isAvailable: Boolean
            get() {
                val info = activeNetworkInfo
                return info != null && info.isAvailable()
            }
        /**
         * 判断网络是否连接
         *
         * 需添加权限 `<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>`
         *
         * @return `true`: 是<br></br>`false`: 否
         */
        val isConnected: Boolean
            get() {
                val info = activeNetworkInfo
                return info != null && info.isConnected
            }
        
        /**
         * 判断移动数据是否打开
         *
         * @return `true`: 是<br></br>`false`: 否
         */
        /**
         * 打开或关闭移动数据
         *
         * 需系统应用 需添加权限`<uses-permission android:name="android.permission.MODIFY_PHONE_STATE"/>`
         *
         * @param enabled `true`: 打开<br></br>`false`: 关闭
         */
        var dataEnabled: Boolean
            get() {
                try {
                    val tm = AppContextUtils.getContext().getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                    val getMobileDataEnabledMethod = tm.javaClass.getDeclaredMethod("getDataEnabled")
                    if (null != getMobileDataEnabledMethod) {
                        return getMobileDataEnabledMethod.invoke(tm) as Boolean
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                
                return false
            }
            set(enabled) = try {
                val tm = AppContextUtils.getContext().getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                val setMobileDataEnabledMethod = tm.javaClass.getDeclaredMethod("setDataEnabled", Boolean::class.javaPrimitiveType)
                setMobileDataEnabledMethod!!.invoke(tm, enabled) as Unit
            } catch (e: Exception) {
                e.printStackTrace()
            }
        
        /**
         * 判断网络是否是4G
         *
         * 需添加权限 `<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>`
         *
         * @return `true`: 是<br></br>`false`: 否
         */
        val is4G: Boolean
            get() {
                val info = activeNetworkInfo
                return info != null && info.isAvailable && info.subtype == TelephonyManager.NETWORK_TYPE_LTE
            }
        
        /**
         * 判断wifi是否打开
         *
         * 需添加权限 `<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>`
         *
         * @return `true`: 是<br></br>`false`: 否
         */
        /**
         * 打开或关闭wifi
         *
         * 需添加权限 `<uses-permission android:name="android.permission.CHANGE_WIFI_STATE"/>`
         *
         * @param enabled `true`: 打开<br></br>`false`: 关闭
         */
        var wifiEnabled: Boolean
            get() {
                val wifiManager = AppContextUtils.getContext().applicationContext.getSystemService(Context
                        .WIFI_SERVICE) as WifiManager
                return wifiManager.isWifiEnabled
            }
            set(enabled) {
                val wifiManager = AppContextUtils.getContext().applicationContext.getSystemService(Context
                        .WIFI_SERVICE) as WifiManager
                if (enabled) {
                    if (!wifiManager.isWifiEnabled) {
                        wifiManager.isWifiEnabled = true
                    }
                } else {
                    if (wifiManager.isWifiEnabled) {
                        wifiManager.isWifiEnabled = false
                    }
                }
            }
        
        /**
         * 判断wifi是否连接状态
         *
         * 需添加权限 `<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>`
         *
         * @return `true`: 连接<br></br>`false`: 未连接
         */
        val isWifiConnected: Boolean
            get() {
                val cm = AppContextUtils.getContext()
                        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                return (cm != null && cm.activeNetworkInfo != null
                        && cm.activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI)
            }
        
        /**
         * 获取网络运营商名称
         *
         * 中国移动、如中国联通、中国电信
         *
         * @return 运营商名称
         */
        val networkOperatorName: String?
            get() {
                val tm = AppContextUtils.getContext().getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                return tm?.networkOperatorName
            }
        
        private val NETWORK_TYPE_GSM = 16
        private val NETWORK_TYPE_TD_SCDMA = 17
        private val NETWORK_TYPE_IWLAN = 18
        
        /**
         * 获取当前网络类型
         *
         * 需添加权限 `<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>`
         *
         * @return 网络类型   * [NetworkType.NETWORK_WIFI]   * [NetworkType.NETWORK_4G]
         *  * [NetworkType.NETWORK_3G]   * [NetworkType.NETWORK_2G]   * [ ][NetworkType.NETWORK_UNKNOWN]   * [NetworkType.NETWORK_NO]
         */
        val networkType: NetworkType
            get() {
                var netType = NetworkType.NETWORK_NO
                val info = activeNetworkInfo
                if (info != null && info.isAvailable) {
                    
                    if (info.type == ConnectivityManager.TYPE_WIFI) {
                        netType = NetworkType.NETWORK_WIFI
                    } else if (info.type == ConnectivityManager.TYPE_MOBILE) {
                        when (info.subtype) {
                            
                            NETWORK_TYPE_GSM,
                            TelephonyManager.NETWORK_TYPE_GPRS,
                            TelephonyManager.NETWORK_TYPE_CDMA,
                            TelephonyManager.NETWORK_TYPE_EDGE,
                            TelephonyManager.NETWORK_TYPE_1xRTT,
                            TelephonyManager.NETWORK_TYPE_IDEN ->
                                netType = NetworkType.NETWORK_2G
                            NETWORK_TYPE_TD_SCDMA,
                            TelephonyManager.NETWORK_TYPE_EVDO_A,
                            TelephonyManager.NETWORK_TYPE_UMTS,
                            TelephonyManager.NETWORK_TYPE_EVDO_0,
                            TelephonyManager.NETWORK_TYPE_HSDPA,
                            TelephonyManager.NETWORK_TYPE_HSUPA,
                            TelephonyManager.NETWORK_TYPE_HSPA,
                            TelephonyManager.NETWORK_TYPE_EVDO_B,
                            TelephonyManager.NETWORK_TYPE_EHRPD,
                            TelephonyManager.NETWORK_TYPE_HSPAP ->
                                netType = NetworkType.NETWORK_3G
                            NETWORK_TYPE_IWLAN,
                            TelephonyManager.NETWORK_TYPE_LTE ->
                                netType = NetworkType.NETWORK_4G
                            else -> {
                                
                                val subtypeName = info.subtypeName
                                if (subtypeName.equals("TD-SCDMA", ignoreCase = true)
                                        || subtypeName.equals("WCDMA", ignoreCase = true)
                                        || subtypeName.equals("CDMA2000", ignoreCase = true)) {
                                    netType = NetworkType.NETWORK_3G
                                } else {
                                    netType = NetworkType.NETWORK_UNKNOWN
                                }
                            }
                        }
                    } else {
                        netType = NetworkType.NETWORK_UNKNOWN
                    }
                }
                return netType
            }
    }
    
}