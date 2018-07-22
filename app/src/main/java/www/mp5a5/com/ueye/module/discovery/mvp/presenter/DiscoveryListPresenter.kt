package www.mp5a5.com.ueye.module.discovery.mvp.presenter

import www.mp5a5.com.ueye.R
import www.mp5a5.com.ueye.base.mvp.BasePresenter
import www.mp5a5.com.ueye.base.view.act.BaseActivity
import www.mp5a5.com.ueye.module.discovery.mvp.contract.DiscoveryListContract
import www.mp5a5.com.ueye.module.discovery.net.DiscoveryService
import www.mp5a5.com.ueye.net.BaseObserver
import www.mp5a5.com.ueye.net.entity.DiscoveryListInfo
import www.mp5a5.com.ueye.util.ConstantUtil
import www.mp5a5.com.ueye.util.switchSchedulers

/**
 * @describe
 * @author ：king9999 on 2018/7/22 16：35
 * @email：wangwenbinc@enn.cn
 */
class DiscoveryListPresenter : BasePresenter<DiscoveryListContract.View>(), DiscoveryListContract.Presenter {
    override fun requestNetWork(activity: BaseActivity, categoryName: String, strategy: String, refresh: Boolean) {
        v.unableLoadMore()
        v.resetIndex()
        DiscoveryService
                .getDiscoveryListOneData(categoryName, strategy, "26868b32e808498db32fd51fb422d00175e179df", 83)
                .switchSchedulers(activity)
                .subscribe(object : BaseObserver<DiscoveryListInfo>() {
                    override fun onSuccess(response: DiscoveryListInfo) {
                        val mList = mutableListOf<DiscoveryListInfo.ItemListBean.DataBean>()
                        response.itemList!!.forEach { mList.add(it.data!!) }
                        v.setNewData(mList)
                        v.enableLoadMore()
                        v.unableRefresh()
                    }
                    
                    override fun onFailing(response: DiscoveryListInfo) {
                        super.onFailing(response)
                        v.showMsg(activity.getString(R.string.no_network))
                        v.enableLoadMore()
                        v.unableRefresh()
                    }
                    
                    override fun onError(e: Throwable) {
                        super.onError(e)
                        v.showMsg(activity.getString(R.string.no_network))
                        v.enableLoadMore()
                        v.unableRefresh()
                    }
                    
                })
    }
    
    override fun requestMoreNetWork(activity: BaseActivity, start: Int, categoryName: String, strategy: String, refresh: Boolean) {
        DiscoveryService
                .getDiscoveryListMoreData(start, 10, categoryName, strategy)
                .switchSchedulers(activity)
                .subscribe(object : BaseObserver<DiscoveryListInfo>() {
                    override fun onSuccess(response: DiscoveryListInfo) {
                        v.addIndex()
                        val mList = mutableListOf<DiscoveryListInfo.ItemListBean.DataBean>()
                        response.itemList!!.forEach { mList.add(it.data!!) }
                        v.addData(mList)
                        if (mList.size < ConstantUtil.SHOW_MAX_PAGE_SIZE) v.loadMoreEnd(refresh) else v.loadMoreComplete()
                    }
                    
                    override fun onFailing(response: DiscoveryListInfo) {
                        super.onFailing(response)
                        v.showMsg(activity.getString(R.string.no_network))
                        v.loadMoreFailed()
                    }
                    
                    override fun onError(e: Throwable) {
                        super.onError(e)
                        v.showMsg(activity.getString(R.string.no_network))
                        v.loadMoreFailed()
                    }
                })
    }
    
    
}