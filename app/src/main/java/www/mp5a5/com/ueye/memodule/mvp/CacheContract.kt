package www.mp5a5.com.ueye.memodule.mvp

import www.mp5a5.com.ueye.base.mvp.BaseView
import www.mp5a5.com.ueye.dao.VideoEntityCache

/**
 * @describe
 * @author ：king9999 on 2018/7/2 16：29
 * @email：wangwenbinc@enn.cn
 */
interface CacheContract {
    
    interface View : BaseView {
        
        fun showMsg(msg: String)
        
        fun <E> setData(list: List<E>)
        
        fun setNullData()
        
    }
    
    
    interface Presenter {
        
        fun requestDao()
        
        fun deleteAllCache()
        
        fun deleteOneCache(entityCache: VideoEntityCache)
    }
}