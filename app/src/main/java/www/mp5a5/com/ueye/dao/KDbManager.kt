package www.mp5a5.com.ueye.dao

import www.mp5a5.com.ueye.greenDao.gen.DaoMaster
import www.mp5a5.com.ueye.greenDao.gen.DaoSession
import www.mp5a5.com.ueye.util.AppContextUtils

/**
 * @describe
 * @author ：king9999 on 2018/6/25 14：20
 * @email：wwb199055@enn.cn
 */
object KDbManager {
    
    private val DB_NAME = "ueye.db"
    private val mDaoSession: DaoSession
    
    init {
        val devOpenHelper = DaoMaster.DevOpenHelper(AppContextUtils.getContext(), DB_NAME, null)
        val daoMaster = DaoMaster(devOpenHelper.writableDatabase)
        mDaoSession = daoMaster.newSession()
    }
    
    
    val daoSession: DaoSession
        get() = mDaoSession
    
   
}
