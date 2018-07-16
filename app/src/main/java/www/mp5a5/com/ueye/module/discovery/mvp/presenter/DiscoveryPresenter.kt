package www.mp5a5.com.ueye.module.discovery.mvp.presenter

import www.mp5a5.com.ueye.base.mvp.BasePresenter
import www.mp5a5.com.ueye.base.view.act.BaseActivity
import www.mp5a5.com.ueye.module.discovery.mvp.contract.DiscoveryContract
import www.mp5a5.com.ueye.module.discovery.net.DiscoveryService
import www.mp5a5.com.ueye.util.CollectionUtils
import www.mp5a5.com.ueye.util.switchSchedulers

/**
 * @describe
 * @author ：king9999 on 2018/7/16 10：30
 * @email：wangwenbinc@enn.cn
 */
class DiscoveryPresenter : BasePresenter<DiscoveryContract.View>(), DiscoveryContract.Presenter {
    
    override fun requestNetWork(activity: BaseActivity, refresh: Boolean) {
        DiscoveryService.getDiscoveryData()
                .switchSchedulers(activity)
                .subscribe({ t ->
                    
                    if (CollectionUtils.isNotEmpty(t)) {
                        v.setNewData(t)
                    } else {
                        v.showMsg("网络错误！")
                        v.setEmptyView()
                    }
                    
                }, {
                    v.showMsg(it.toString())
                    v.setEmptyView()
                })
    }
}