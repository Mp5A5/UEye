package www.mp5a5.com.ueye.module.discovery.net

import io.reactivex.Observable
import retrofit2.http.GET
import www.mp5a5.com.ueye.net.entity.DiscoveryEntity
import www.mp5a5.com.ueye.util.ConstantUtil

/**
 * @describe
 * @author ：king9999 on 2018/7/13 17：30
 * @email：wangwenbinc@enn.cn
 */
interface DiscoveryApiService {
    
    @GET(ConstantUtil.URL_HOST + "v2/categories?udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    fun getDiscoveryData(): Observable<MutableList<DiscoveryEntity>>
    
}