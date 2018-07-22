package www.mp5a5.com.ueye.module.discovery.net

import io.reactivex.Observable
import www.mp5a5.com.ueye.net.RetrofitFactor
import www.mp5a5.com.ueye.net.entity.DiscoveryEntity
import www.mp5a5.com.ueye.net.entity.DiscoveryListInfo

/**
 * @describe
 * @author ：king9999 on 2018/7/13 17：33
 * @email：wangwenbinc@enn.cn
 */
object DiscoveryService {
    
    private val mDiscoveryApiService = RetrofitFactor.create(DiscoveryApi::class.java)
    
    fun getDiscoveryData(): Observable<MutableList<DiscoveryEntity>> {
        return mDiscoveryApiService.getDiscoveryData()
    }
    
    fun getDiscoveryListOneData(categoryName: String, strategy: String, uid: String, vc: Int): Observable<DiscoveryListInfo> {
        return mDiscoveryApiService.getDiscoveryDetailOneData(categoryName, strategy, uid, vc)
    }
    
    fun getDiscoveryListMoreData(start: Int, num: Int, categoryName: String, strategy: String): Observable<DiscoveryListInfo> {
        return mDiscoveryApiService.getDiscoveryDetailMoreData(start, num, categoryName, strategy)
    }
}