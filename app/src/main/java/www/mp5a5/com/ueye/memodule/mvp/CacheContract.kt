package www.mp5a5.com.ueye.memodule.mvp

import www.mp5a5.com.ueye.base.mvp.BaseView
import www.mp5a5.com.ueye.base.view.act.BaseActivity

/**
 * @describe
 * @author ：king9999 on 2018/7/2 16：29
 * @email：wangwenbinc@enn.cn
 */
interface CacheContract {
    
    interface View : BaseView {
        
        fun showMsg(msg: String)
        
        fun <E> setData(list: List<E>)
        
    }
    
    
    interface Presenter {
        
        fun requestDao(context: BaseActivity)
        
    }
}