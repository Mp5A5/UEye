package www.mp5a5.com.ueye.homemodule

import www.mp5a5.com.ueye.base.mvp.BaseView
import www.mp5a5.com.ueye.base.view.act.BaseActivity
import www.mp5a5.com.ueye.net.entity.HomeBean

/**
 * @describe
 * @author ：king9999 on 2018/6/22 20：29
 * @email：wwb199055@enn.cn
 */
interface HomeFragContract {
    
    interface View : BaseView {
        
        fun showMsg(msg: String)
        
        fun isRefresh(refresh: Boolean)
        
        fun loadMoreEnd(isEnd: Boolean)
        
        /**
         * 刷新
         */
        fun enableRefreshing()
        
        /**
         * 不刷新
         */
        fun unableRefreshing()
        
        fun loadMoreComplete()
        
        fun loadMoreFail()
        
        fun enableLoadMore()
        
        fun unableLoadMore()
        
        /**
         * 索引自增
         */
        fun addIndex()
        
        /**
         * 索引自减
         */
        fun subIndex()
        
        /**
         * 索引重置
         */
        fun resetIndex()
        
        fun <E> addData(list: List<E>)
        
        fun <E> setData(list: List<E>)
        
        fun setData(bean: HomeBean)
        
    }
    
    interface Presenter {
        
        fun requestNetworkFirst(activity: BaseActivity, refresh: Boolean)
        
        fun requestNetworkNext(activity: BaseActivity, data: String, num: String, refresh: Boolean)
    }
}