package www.mp5a5.com.ueye.memodule.mvp

import www.mp5a5.com.ueye.base.mvp.BasePresenter
import www.mp5a5.com.ueye.dao.VideoEntityCache
import www.mp5a5.com.ueye.dao.VideoEntityDaoUtil
import www.mp5a5.com.ueye.net.entity.VideoBean
import www.mp5a5.com.ueye.util.CollectionUtils
import www.mp5a5.com.ueye.util.GsonUtils

/**
 * @describe
 * @author ：king9999 on 2018/7/2 16：34
 * @email：wangwenbinc@enn.cn
 */
class CachePresenter : BasePresenter<CacheContract.View>(), CacheContract.Presenter {
    
    
    override fun deleteOneCache(entityCache: VideoEntityCache) {
        VideoEntityDaoUtil.deleteData(entityCache )
    }
    
    override fun deleteAllCache() {
        VideoEntityDaoUtil.deleteAll()
        v.setNullData()
    }
    
    override fun requestDao() {
        val list = VideoEntityDaoUtil.queryAll()
        if (CollectionUtils.isNotEmpty(list)) {
            val mList = mutableListOf<VideoBean>()
            list!!.forEach {
                val videoBean = GsonUtils.json2Bean(it.videoBean, VideoBean::class.java)
                mList.add(videoBean)
            }
            v.setData(mList)
        }
    }
    
    
}