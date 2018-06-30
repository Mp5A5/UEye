package www.mp5a5.com.ueye.util

/**
 * @describe
 * @author ：king9999 on 2018/6/30 08：50
 * @email：wangwenbinc@enn.cn
 */
class CollectionUtils {
    
    companion object {
        
        fun isNotEmpty(list: List<*>?): Boolean = list != null && list.size > 0
        
        
        fun isEmpty(list: List<*>?): Boolean = list != null && list.isEmpty()
    }
}