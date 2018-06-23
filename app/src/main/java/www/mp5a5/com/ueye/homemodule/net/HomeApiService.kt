package www.mp5a5.com.ueye.homemodule.net

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import www.mp5a5.com.ueye.net.entity.HomeBean
import www.mp5a5.com.ueye.util.ConstantUtil

/**
 * @describe
 * @author ：king9999 on 2018/6/22 20：38
 * @email：wwb199055@enn.cn
 */
interface HomeApiService {
    
    //获取首页第一页数据
    @GET(ConstantUtil.URL_HOST + "v2/feed?num=2&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    fun getHomeData(): Observable<HomeBean>
    
    
    //获取首页第一页之后的数据  ?date=1499043600000&num=2
    @GET(ConstantUtil.URL_HOST + "v2/feed")
    fun getHomeMoreData(@Query("date") date: String, @Query("num") num: String): Observable<HomeBean>
}