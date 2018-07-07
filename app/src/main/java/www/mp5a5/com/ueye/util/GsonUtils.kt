package www.mp5a5.com.ueye.util

import android.support.annotation.Nullable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * @describe
 * @author ：king9999 on 2018/7/6 13：51
 * @email：wangwenbinc@enn.cn
 */
class GsonUtils {
    
    companion object {
        
        private var mGson: Gson? = null
        
        init {
            if (mGson == null) {
                mGson = Gson()
            }
        }
        
        fun toString(@Nullable any: Any): String {
            return mGson!!.toJson(any)
        }
        
        fun <T> json2Bean(json: String, clazz: Class<T>): T {
            return mGson!!.fromJson(json, clazz)
        }
        
        fun <E> json2List(json: String, clazz: Class<E>): List<E> {
            return mGson!!.fromJson<List<E>>(json, object : TypeToken<List<E>>() {}.type)
        }
        
        fun <E> json2List(json: String): List<Map<String, E>> {
            return mGson!!.fromJson<List<Map<String, E>>>(json,
                    object : TypeToken<List<Map<String, E>>>() {}.type)
        }
        
        fun <E> json2Map(json: String): Map<String, E> {
            return mGson!!.fromJson<Map<String, E>>(json, object : TypeToken<Map<String, E>>() {}.type)
        }
        
    }
}

