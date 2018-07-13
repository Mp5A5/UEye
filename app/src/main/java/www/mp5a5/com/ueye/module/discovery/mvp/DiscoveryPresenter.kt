package www.mp5a5.com.ueye.module.discovery.mvp

import www.mp5a5.com.ueye.base.mvp.BaseView
import www.mp5a5.com.ueye.base.view.act.BaseActivity

/**
 * @describe
 * @author ：king9999 on 2018/7/13 17：23
 * @email：wangwenbinc@enn.cn
 */
interface DiscoveryPresenter {
    
    interface View : BaseView {
        
        fun <E> addData(list: List<E>)
        
        fun <E> setNewData(list: List<E>)
        
        fun setEmptyView()
        
        fun loadMoreEnd(isEnd: Boolean)
        
        /**
         * 刷新
         */
        fun enableRefreshing()
        
        /**
         * 不刷新
         */
        fun unableRefreshing()
        
        /**
         * 加载完成
         */
        fun loadMoreComplete()
        
        /**
         * 加载失败
         */
        fun loadMoreFail()
        
        /**
         * 允许加载
         */
        fun enableLoadMore()
        
        /**
         * 不允许加载
         */
        fun unableLoadMore()
    }
    
    interface Presenter {
        
        fun requestNetWork(activity: BaseActivity, refresh: Boolean)
        
    }
}