package www.mp5a5.com.ueye.dao

import android.content.Context
import www.mp5a5.com.ueye.greenDao.gen.VideoEntityCacheDao
import www.mp5a5.com.ueye.util.CollectionUtils

/**
 * @describe
 * @author ：king9999 on 2018/6/25 14：01
 * @email：wwb199055@enn.cn
 */
class VideoEntityDaoUtil private constructor() {
    
    companion object {
        
        /**
         * @desc 添加数据至数据库
         **/
        fun insertData(entityCache: VideoEntityCache) {
            KDbManager.daoSession.videoEntityCacheDao.insert(entityCache)
        }
        
        /**
         * @desc 将数据实体通过事务添加至数据库
         **/
        fun insertData(list: List<VideoEntityCache>) {
            if (CollectionUtils.isNotEmpty(list)) {
                KDbManager.daoSession.videoEntityCacheDao.insertInTx(list)
            }
        }
        
        /**
         * 添加数据至数据库，如果存在，将原来的数据覆盖
         * 内部代码判断了如果存在就update(entity);不存在就insert(entity)；
         */
        fun saveData(entityCache: VideoEntityCache) {
            KDbManager.daoSession.videoEntityCacheDao.save(entityCache)
        }
        
        
        /**
         * @desc 删除数据
         **/
        fun deleteData(entityCache: VideoEntityCache) {
            KDbManager.daoSession.videoEntityCacheDao.delete(entityCache)
        }
        
        /**
         * 根据id删除数据至数据库
         
         * @param context
         * *
         * @param id      删除具体内容
         */
        
        fun deleteByKeyData(id: Int) {
            KDbManager.daoSession.videoEntityCacheDao.deleteByKey(id)
        }
        
        /**
         * @desc 删除全部数据
         **/
        fun deleteAll(context: Context) {
            KDbManager.daoSession.videoEntityCacheDao.deleteAll()
        }
        
        /**
         * @desc 更新数据
         **/
        fun updateData(entityCache: VideoEntityCache) {
            KDbManager.daoSession.videoEntityCacheDao.update(entityCache)
        }
        
        /**
         * @desc 查询所有数据
         **/
        fun queryAll(): MutableList<VideoEntityCache>? {
            return KDbManager.daoSession.videoEntityCacheDao.queryBuilder().build().list()
        }
        
        /**
         * 根据id查询，其他的字段类似
         
         * @param context
         * *
         * @param id
         * *
         * @return
         */
        fun queryForId(id: Int): MutableList<VideoEntityCache>? {
            return KDbManager.daoSession.videoEntityCacheDao.queryBuilder().where(VideoEntityCacheDao.Properties.Id.eq(id)).list()
        }
    }
}