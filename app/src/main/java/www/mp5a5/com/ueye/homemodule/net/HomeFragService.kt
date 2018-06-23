package www.mp5a5.com.ueye.homemodule.net

import io.reactivex.Observable
import www.mp5a5.com.ueye.net.RetrofitFactor
import www.mp5a5.com.ueye.net.entity.HomeBean

/**
 * @describe
 * @author ：king9999 on 2018/6/22 20：42
 * @email：wwb199055@enn.cn
 */
object HomeFragService {
    
    private val mHomeFragApi: HomeApiService = RetrofitFactor.create(HomeApiService::class.java)
    
    fun getHomeFragFirsData(): Observable<HomeBean> {
        return mHomeFragApi.getHomeData()
    }
    
    fun getHomeFragNextData(data: String, num: String): Observable<HomeBean> {
        return mHomeFragApi.getHomeMoreData(data, num)
    }
}