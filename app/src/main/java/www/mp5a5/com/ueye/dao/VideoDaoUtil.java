package www.mp5a5.com.ueye.dao;

import android.annotation.SuppressLint;
import android.content.Context;

import java.util.List;

/**
 * @author ：king9999 on 2018/6/25 11：22
 * @describe
 * @email：wwb199055@enn.cn
 */
@SuppressLint("CheckResult")
public class VideoDaoUtil {
  /**
   * @desc 添加数据至数据库
   */
  public static void insertData(final VideoEntityCache entityCache) {
    DbManager.getInstance().getDaoSession().getVideoEntityCacheDao().insert(entityCache);
  }

  /**
   * @desc 将数据实体通过事务添加至数据库
   */
  public static void insertData(final List<VideoEntityCache> list) {
    DbManager.getInstance().getDaoSession().getVideoEntityCacheDao().insertInTx(list);
  }

  /**
   * @desc 添加数据至数据库，如果存在，将原来的数据覆盖
   */
  public void saveData(final VideoEntityCache entityCache) {
    DbManager.getInstance().getDaoSession().getVideoEntityCacheDao().save(entityCache);
  }


  /**
   * @desc 查询所有数据
   */
  public static List<VideoEntityCache> queryAll() {
    return DbManager.getInstance().getDaoSession()
        .getVideoEntityCacheDao()
        .queryBuilder()
        .build()
        .list();

  }

  /**
   * @desc 删除数据
   */
  public static void deleteData(final VideoEntityCache entityCache) {
    DbManager.getInstance().getDaoSession().getVideoEntityCacheDao().delete(entityCache);
  }

  /**
   * @desc 删除全部数据
   **/
  public static void deleteAll(final Context context) {
    DbManager.getInstance().getDaoSession().getVideoEntityCacheDao().deleteAll();
  }

  /**
   * @desc 更新数据
   **/
  public static void updateData(final VideoEntityCache entityCache) {
    DbManager.getInstance().getDaoSession().getVideoEntityCacheDao().update(entityCache);
  }

}
