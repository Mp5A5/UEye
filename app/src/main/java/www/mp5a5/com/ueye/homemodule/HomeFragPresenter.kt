package www.mp5a5.com.ueye.homemodule

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import www.mp5a5.com.ueye.R
import www.mp5a5.com.ueye.base.mvp.BasePresenter
import www.mp5a5.com.ueye.base.view.act.BaseActivity
import www.mp5a5.com.ueye.net.BaseObserver
import www.mp5a5.com.ueye.net.entity.HomeBean
import www.mp5a5.com.ueye.util.ConstantUtil

/**
 * @describe
 * @author ：king9999 on 2018/6/22 20：51
 * @email：wwb199055@enn.cn
 */
class HomeFragPresenter : BasePresenter<HomeFragContract.View>(), HomeFragContract.Presenter {
    
    override fun requestNetworkFirst(activity: BaseActivity, refresh: Boolean) {
        v.unableLoadMore()
        HomeFragService.getHomeFragFirsData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(activity.bindToLifecycle())
                .subscribe(object : BaseObserver<HomeBean>() {
                    override fun onSuccess(response: HomeBean) {
                        v.setData(response)
                        var mList = ArrayList<HomeBean.IssueListBean.ItemListBean>()
                        response.issueList!!
                                .flatMap { it.itemList!! }
                                .filter { it.type == "video" }
                                .forEach { mList.add(it) }
                        v.setData(mList)
                        v.enableLoadMore()
                        v.unableRefreshing()
                    }
                    
                    override fun onFailing(response: HomeBean) {
                        super.onFailing(response)
                        v.showMsg(activity.getString(R.string.no_network))
                        v.enableLoadMore()
                        v.unableRefreshing()
                    }
                    
                    override fun onError(e: Throwable) {
                        super.onError(e)
                        v.showMsg(activity.getString(R.string.no_network))
                        v.enableLoadMore()
                        v.unableRefreshing()
                    }
                })
    }
    
    override fun requestNetworkNext(activity: BaseActivity, data: String, num: String, refresh: Boolean) {
        HomeFragService.getHomeFragNextData(data, num)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(activity.bindToLifecycle())
                .subscribe(object : BaseObserver<HomeBean>() {
                    override fun onSuccess(response: HomeBean) {
                        v.setData(response)
                        var mList = ArrayList<HomeBean.IssueListBean.ItemListBean>()
                        response.issueList!!
                                .flatMap { it.itemList!! }
                                .filter { it.type == "video" }
                                .forEach { mList.add(it) }
                        v.addData(mList)
                        
                        if (mList.size < ConstantUtil.SHOW_MAX_PAGE_SIZE) {
                            // 第一页如果不够一页就不显示没有更多数据布局
                            v.loadMoreEnd(refresh)
                        } else {
                            v.loadMoreComplete()
                        }
                    }
                    
                    override fun onFailing(response: HomeBean) {
                        super.onFailing(response)
                        v.showMsg(activity.getString(R.string.no_network))
                        v.loadMoreFail()
                    }
                    
                    override fun onError(e: Throwable) {
                        super.onError(e)
                        v.showMsg(activity.getString(R.string.no_network))
                        v.loadMoreFail()
                    }
                })
    }
}