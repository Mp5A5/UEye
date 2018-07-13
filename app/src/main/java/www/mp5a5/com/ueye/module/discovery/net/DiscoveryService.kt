package www.mp5a5.com.ueye.module.discovery.net

import io.reactivex.Observable
import www.mp5a5.com.ueye.net.RetrofitFactor
import www.mp5a5.com.ueye.net.entity.DiscoveryEntity

/**
 * @describe
 * @author ：king9999 on 2018/7/13 17：33
 * @email：wangwenbinc@enn.cn
 */
object DiscoveryService {
    
    private val mDiscoveryApiService = RetrofitFactor.create(DiscoveryApiService::class.java)
    
    fun getDiscoveryData(): Observable<MutableList<DiscoveryEntity>> {
        return mDiscoveryApiService.getDiscoveryData()
    }
}