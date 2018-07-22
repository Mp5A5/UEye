package www.mp5a5.com.ueye.module.discovery.mvp.contract

import www.mp5a5.com.ueye.base.mvp.BaseView
import www.mp5a5.com.ueye.base.view.act.BaseActivity

/**
 * @describe
 * @author ：king9999 on 2018/7/22 16：27
 * @email：wangwenbinc@enn.cn
 */
interface DiscoveryListContract {
    
    interface View : BaseView {
        
        fun showMsg(msg: String)
        
        fun unableRefresh()
        
        fun enableLoadMore()
        
        fun unableLoadMore()
        
        fun loadMoreEnd(isEnd: Boolean)
        
        fun loadMoreComplete()
        
        fun loadMoreFailed()
        
        fun <E> setNewData(list: List<E>)
        
        fun <E> addData(list: List<E>)
        
        fun addIndex()
        
        fun resetIndex()
    }
    
    interface Presenter {
        fun requestNetWork(activity: BaseActivity,
                           categoryName: String,
                           strategy: String,
                           refresh: Boolean)
        
        fun requestMoreNetWork(activity: BaseActivity,
                               start: Int,
                               categoryName: String,
                               strategy: String,
                               refresh: Boolean)
        
    }
}