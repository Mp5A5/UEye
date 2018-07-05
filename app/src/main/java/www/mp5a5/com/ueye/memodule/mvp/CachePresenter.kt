package www.mp5a5.com.ueye.memodule.mvp

import com.google.gson.Gson
import www.mp5a5.com.ueye.base.mvp.BasePresenter
import www.mp5a5.com.ueye.base.view.act.BaseActivity
import www.mp5a5.com.ueye.dao.VideoEntityDaoUtil
import www.mp5a5.com.ueye.net.entity.VideoBean

/**
 * @describe
 * @author ：king9999 on 2018/7/2 16：34
 * @email：wangwenbinc@enn.cn
 */
class CachePresenter : BasePresenter<CacheContract.View>(), CacheContract.Presenter {
    
    override fun requestDao(context: BaseActivity) {
        
        val list = VideoEntityDaoUtil.queryAll();
        if (list!!.size > 0) {
            val mList = mutableListOf<VideoBean>()
            list!!.forEach {
                val gson = Gson()
                val videoBean = gson.fromJson(it.videoBean, VideoBean::class.java)
                mList.add(videoBean)
            }
            v.setData(mList)
        }
    }
}