package www.mp5a5.com.ueye.util

import java.util.*

/**
 * @describe
 * @author ：king9999 on 2018/6/22 16：50
 * @email：wwb199055@enn.cn
 */
class DateUtil {
    
    companion object {
        fun getToday(): String {
            val list = arrayOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
            val data = Date()
            val calendar: Calendar = Calendar.getInstance()
            calendar.time = data
            var index: Int = calendar.get(Calendar.DAY_OF_WEEK) - 1
            if (index < 0) {
                index = 0
            }
            return list[index]
        }
    }
}