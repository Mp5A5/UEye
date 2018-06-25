package www.mp5a5.com.ueye.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import www.mp5a5.com.ueye.greenDao.gen.DaoMaster;
import www.mp5a5.com.ueye.greenDao.gen.DaoMaster.DevOpenHelper;
import www.mp5a5.com.ueye.greenDao.gen.DaoSession;

/**
 * @author ：king9999 on 2018/6/25 11：30
 * @describe
 * @email： wwb199055@enn.cn
 */
public class DbManager {
  // 是否加密
  private static final String DB_NAME = "ueye.db";
  private static DbManager mDbManager;
  private static DevOpenHelper mDevOpenHelper;
  private static DaoMaster mDaoMaster;
  private static DaoSession mDaoSession;
  private Context mContext;

  private DbManager(Context context) {
    this.mContext = context;
    // 初始化数据库信息
    mDevOpenHelper = new DevOpenHelper(context, DB_NAME);
    getDaoMaster(context);
    getDaoSession(context);
  }

  public static DbManager getInstance(Context context) {
    if (null == mDbManager) {
      synchronized (DbManager.class) {
        if (null == mDbManager) {
          mDbManager = new DbManager(context);
        }
      }
    }
    return mDbManager;
  }

  /**
   * @desc 获取可读数据库
   **/
  public static SQLiteDatabase getReadableDatabase(Context context) {
    if (null == mDevOpenHelper) {
      getInstance(context);
    }
    return mDevOpenHelper.getReadableDatabase();
  }

  /**
   * @desc 获取可写数据库
   **/
  public static SQLiteDatabase getWritableDatabase(Context context) {
    if (null == mDevOpenHelper) {
      getInstance(context);
    }
    return mDevOpenHelper.getWritableDatabase();
  }

  /**
   * @desc 获取DaoMaster
   **/
  public static DaoMaster getDaoMaster(Context context) {
    if (null == mDaoMaster) {
      synchronized (DbManager.class) {
        if (null == mDaoMaster) {
          mDaoMaster = new DaoMaster(getWritableDatabase(context));
        }
      }
    }
    return mDaoMaster;
  }

  /**
   * @desc 获取DaoSession
   **/
  public static DaoSession getDaoSession(Context context) {
    if (null == mDaoSession) {
      synchronized (DbManager.class) {
        mDaoSession = getDaoMaster(context).newSession();
      }
    }

    return mDaoSession;
  }
}
