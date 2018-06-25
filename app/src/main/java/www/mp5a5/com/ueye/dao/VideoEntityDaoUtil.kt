package www.mp5a5.com.ueye.dao

import android.content.Context
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import www.mp5a5.com.ueye.greenDao.gen.VideoEntityCacheDao

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
        fun insertData(context: Context, entityCache: VideoEntityCache) {
            Observable.create<VideoEntityCache> { emitter: ObservableEmitter<VideoEntityCache> ->
                KDbManager.getInstance(context)!!.getDaoSession(context)!!.videoEntityCacheDao.insert(entityCache)
            }.subscribeOn(Schedulers.io())
        }
        
        /**
         * @desc 将数据实体通过事务添加至数据库
         **/
        fun insertData(context: Context, list: List<VideoEntityCache>) {
            Observable.create<List<VideoEntityCache>> { emitter: ObservableEmitter<List<VideoEntityCache>> ->
                if (!list.isEmpty()) {
                    return@create
                }
                KDbManager.getInstance(context)!!.getDaoSession(context)!!.videoEntityCacheDao.insertInTx(list)
            }.subscribeOn(Schedulers.io())
        }
        
        /**
         * 添加数据至数据库，如果存在，将原来的数据覆盖
         * 内部代码判断了如果存在就update(entity);不存在就insert(entity)；
         */
        fun saveData(context: Context, entityCache: VideoEntityCache) {
            Observable.create<VideoEntityCache> { emitter: ObservableEmitter<VideoEntityCache> ->
                KDbManager.getInstance(context)!!.getDaoSession(context)!!.videoEntityCacheDao.save(entityCache)
            }.subscribeOn(Schedulers.io())
        }
        
        
        /**
         * @desc 删除数据
         **/
        fun deleteData(context: Context, entityCache: VideoEntityCache) {
            Observable.create<VideoEntityCache> { emitter: ObservableEmitter<VideoEntityCache> ->
                KDbManager.getInstance(context)!!.getDaoSession(context)!!.videoEntityCacheDao.delete(entityCache)
            }.subscribeOn(Schedulers.io())
        }
        
        /**
         * 根据id删除数据至数据库
         
         * @param context
         * *
         * @param id      删除具体内容
         */
        
        fun deleteByKeyData(context: Context, id: Int) {
            Observable.create<VideoEntityCache> { emitter: ObservableEmitter<VideoEntityCache> ->
                KDbManager.getInstance(context)!!.getDaoSession(context)!!.videoEntityCacheDao.deleteByKey(id)
            }.subscribeOn(Schedulers.io())
        }
        
        /**
         * @desc 删除全部数据
         **/
        fun deleteAll(context: Context) {
            Observable.create<VideoEntityCache> { emitter: ObservableEmitter<VideoEntityCache> ->
                KDbManager.getInstance(context)!!.getDaoSession(context)!!.videoEntityCacheDao.deleteAll()
            }.subscribeOn(Schedulers.io())
        }
        
        /**
         * @desc 更新数据
         **/
        fun updateData(context: Context, entityCache: VideoEntityCache) {
            Observable.create<VideoEntityCache> { emitter: ObservableEmitter<VideoEntityCache> ->
                KDbManager.getInstance(context)!!.getDaoSession(context)!!.videoEntityCacheDao.update(entityCache)
            }.subscribeOn(Schedulers.io())
        }
        
        /**
         * @desc 查询所有数据
         **/
        fun queryAll(context: Context): Observable<MutableList<VideoEntityCache>> {
            val observable = Observable.create<MutableList<VideoEntityCache>> { emitter: ObservableEmitter<MutableList<VideoEntityCache>> ->
                val list = KDbManager.getInstance(context)!!.getDaoSession(context)!!.videoEntityCacheDao.queryBuilder().build().list()
                emitter.onNext(list)
            }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            return observable
        }
        
        /**
         * 根据id查询，其他的字段类似
         
         * @param context
         * *
         * @param id
         * *
         * @return
         */
        fun queryForId(context: Context, id: Int): Observable<MutableList<VideoEntityCache>?> {
            val observable = Observable.create<MutableList<VideoEntityCache>> { emitter: ObservableEmitter<MutableList<VideoEntityCache>> ->
                val list = KDbManager.getInstance(context)!!.getDaoSession(context)!!.videoEntityCacheDao.queryBuilder().where(VideoEntityCacheDao.Properties.Id.eq(id)).list()
                emitter.onNext(list)
            }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            return observable
        }
    }
}