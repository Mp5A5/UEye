package www.mp5a5.com.ueye.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import www.mp5a5.com.ueye.greenDao.gen.DaoMaster
import www.mp5a5.com.ueye.greenDao.gen.DaoMaster.DevOpenHelper
import www.mp5a5.com.ueye.greenDao.gen.DaoSession

/**
 * @describe
 * @author ：king9999 on 2018/6/25 14：20
 * @email：wwb199055@enn.cn
 */
class KDbManager private constructor(mContext: Context) {
    private val DB_NAME = "ueye.db"
    private var mDevOpenHelper: DevOpenHelper? = null
    private var mDaoMaster: DaoMaster? = null
    private var mDaoSession: DaoSession? = null
    
    init {
        // 初始化数据库信息
        mDevOpenHelper = DevOpenHelper(mContext, DB_NAME)
        getDaoMaster(mContext)
        getDaoSession(mContext)
    }
    
    companion object {
        @Volatile
        var instance: KDbManager? = null
        
        fun getInstance(mContext: Context): KDbManager? {
            if (instance == null) {
                synchronized(KDbManager::class) {
                    if (instance == null) {
                        instance = KDbManager(mContext)
                    }
                }
            }
            return instance
        }
    }
    
    /**
     * 获取可读数据库
     *
     * @param context
     * @return
     */
    fun getReadableDatabase(context: Context): SQLiteDatabase? {
        if (null == mDevOpenHelper) {
            getInstance(context)
        }
        return mDevOpenHelper?.getReadableDatabase()
    }
    
    /**
     * 获取可写数据库
     *
     * @param context
     * @return
     */
    fun getWritableDatabase(context: Context): SQLiteDatabase? {
        if (null == mDevOpenHelper) {
            getInstance(context)
        }
        
        return mDevOpenHelper?.getWritableDatabase()
    }
    
    /**
     * 获取DaoMaster
     *
     * @param context
     * @return
     */
    fun getDaoMaster(context: Context): www.mp5a5.com.ueye.greenDao.gen.DaoMaster? {
        if (null == mDaoMaster) {
            synchronized(KDbManager::class.java) {
                if (null == mDaoMaster) {
                    mDaoMaster = DaoMaster(getWritableDatabase(context))
                }
            }
        }
        return mDaoMaster
    }
    
    /**
     * 获取DaoSession
     *
     * @param context
     * @return
     */
    fun getDaoSession(context: Context): DaoSession? {
        if (null == mDaoSession) {
            synchronized(KDbManager::class.java) {
                mDaoSession = getDaoMaster(context)?.newSession()
            }
        }
        
        return mDaoSession
    }
}
