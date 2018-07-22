package www.mp5a5.com.ueye.module.discovery.net

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import www.mp5a5.com.ueye.net.entity.DiscoveryEntity
import www.mp5a5.com.ueye.net.entity.DiscoveryListInfo
import www.mp5a5.com.ueye.util.ConstantUtil

/**
 * @describe
 * @author ：king9999 on 2018/7/13 17：30
 * @email：wangwenbinc@enn.cn
 */
interface DiscoveryApi {
    
    //发现页面
    @GET(ConstantUtil.URL_HOST + "v2/categories?udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    fun getDiscoveryData(): Observable<MutableList<DiscoveryEntity>>
    
    
    //获取发现频道详情信息
    @GET(ConstantUtil.URL_HOST +"v3/videos")
    fun getDiscoveryDetailOneData(@Query("categoryName") categoryName: String,
                          @Query("strategy") strategy: String,
                          @Query("udid") udid: String,
                          @Query("vc") vc: Int): Observable<DiscoveryListInfo>
    
    //获取发现详情加载更多消息
    @GET(ConstantUtil.URL_HOST +"v3/videos")
    fun getDiscoveryDetailMoreData(@Query("start") start: Int,
                              @Query("num") num: Int,
                              @Query("categoryName") categoryName: String,
                              @Query("strategy") strategy: String): Observable<DiscoveryListInfo>
    
}