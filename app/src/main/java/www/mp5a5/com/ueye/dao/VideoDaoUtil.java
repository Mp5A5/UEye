package www.mp5a5.com.ueye.dao;

import android.annotation.SuppressLint;
import android.content.Context;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author ：king9999 on 2018/6/25 11：22
 * @describe
 * @email：wwb199055@enn.cn
 */
@SuppressLint("CheckResult")
public class VideoDaoUtil {
  /**
   * @desc 添加数据至数据库
   **/

  public static void insertData(final Context context, final VideoEntityCache entityCache) {
    Observable.create((ObservableOnSubscribe<VideoEntityCache>) e ->
        DbManager.getDaoSession(context).getVideoEntityCacheDao().insert(entityCache)).subscribeOn(Schedulers.io());
  }

  /**
   * @desc 将数据实体通过事务添加至数据库
   **/
  public static void insertData(final Context context, final List<VideoEntityCache> list) {
    Observable.create((ObservableOnSubscribe<List<VideoEntityCache>>) e ->
        DbManager.getDaoSession(context).getVideoEntityCacheDao().insertInTx(list)).subscribeOn(Schedulers.io());
  }

  /**
   * @desc 添加数据至数据库，如果存在，将原来的数据覆盖
   **/
  public static void saveData(final Context context, final VideoEntityCache entityCache) {
    Observable.create((ObservableOnSubscribe<VideoEntityCache>) e ->
        DbManager.getDaoSession(context).getVideoEntityCacheDao().save(entityCache)).subscribeOn(Schedulers.io());
  }


  /**
   * @desc 查询所有数据
   **/
  public static Observable<List<VideoEntityCache>> queryAll(final Context context) {
    Observable<List<VideoEntityCache>> observable = Observable.create((ObservableOnSubscribe<List<VideoEntityCache>>)
        e -> {
          List<VideoEntityCache> list = DbManager.getDaoSession(context).getVideoEntityCacheDao().queryBuilder().build
              ().list();
          e.onNext(list);
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    return observable;
  }

  /**
   * @desc 删除数据
   **/
  public static void deleteData(final Context context, final VideoEntityCache entityCache) {

    Observable.create((ObservableOnSubscribe<VideoEntityCache>) e ->
        DbManager.getDaoSession(context).getVideoEntityCacheDao().delete(entityCache)).subscribeOn(Schedulers.io());

  }

  /**
   * @desc 删除全部数据
   **/
  public static void deleteAll(final Context context) {

    Observable.create((ObservableOnSubscribe<VideoEntityCache>) e ->
        DbManager.getDaoSession(context).getVideoEntityCacheDao().deleteAll()).subscribeOn(Schedulers.io());

  }

  /**
   * @desc 更新数据
   **/
  public static void updateData(final Context context, final VideoEntityCache entityCache) {
    Observable.create((ObservableOnSubscribe<VideoEntityCache>) e ->
        DbManager.getDaoSession(context).getVideoEntityCacheDao().update(entityCache)).subscribeOn(Schedulers.io());

  }

}
