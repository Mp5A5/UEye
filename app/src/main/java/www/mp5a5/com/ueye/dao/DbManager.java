package www.mp5a5.com.ueye.dao;

import www.mp5a5.com.ueye.greenDao.gen.DaoMaster;
import www.mp5a5.com.ueye.greenDao.gen.DaoSession;
import www.mp5a5.com.ueye.util.AppContextUtils;

/**
 * @author ：king9999 on 2018/6/25 11：30
 * @describe
 * @email： wwb199055@enn.cn
 */
public class DbManager {
  private static final String DB_NAME = "ueye.db";
  private final DaoSession mDaoSession;

  private DbManager() {
    DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(AppContextUtils.Companion.getContext(),
        DB_NAME, null);
    DaoMaster mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
    mDaoSession = mDaoMaster.newSession();
  }

  public static DbManager getInstance() {
    return DbManagerHolder.sInstance;
  }

  private static class DbManagerHolder {
    private static final DbManager sInstance = new DbManager();
  }

  public DaoSession getDaoSession() {
    return mDaoSession;
  }

}
