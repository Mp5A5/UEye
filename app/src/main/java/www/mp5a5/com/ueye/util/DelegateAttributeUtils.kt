package www.mp5a5.com.ueye.util

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @describe
 * @author ：king9999 on 2018/6/28 15：37
 * @email：wangwenbinc@enn.cn
 */

fun <T> Observable<T>.switchSchedulers(): Observable<T> {
    return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}