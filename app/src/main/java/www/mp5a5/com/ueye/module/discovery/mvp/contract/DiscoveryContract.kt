package www.mp5a5.com.ueye.module.discovery.mvp.contract

import www.mp5a5.com.ueye.base.mvp.BaseView
import www.mp5a5.com.ueye.base.view.act.BaseActivity

/**
 * @describe
 * @author ：king9999 on 2018/7/13 17：23
 * @email：wangwenbinc@enn.cn
 */
interface DiscoveryContract {
    
    interface View : BaseView {
        
        fun showMsg(msg: String)
        
        fun <E> setNewData(list: MutableList<E>)
        
        fun setEmptyView()
        
    }
    
    interface Presenter {
        
        fun requestNetWork(activity: BaseActivity, refresh: Boolean)
        
    }
}