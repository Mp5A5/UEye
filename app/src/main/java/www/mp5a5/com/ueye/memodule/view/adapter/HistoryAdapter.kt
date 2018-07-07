package www.mp5a5.com.ueye.memodule.view.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import www.mp5a5.com.ueye.R
import www.mp5a5.com.ueye.net.entity.VideoBean
import www.mp5a5.com.ueye.util.GlideUtils
import java.text.SimpleDateFormat

/**
 * @describe
 * @author ：king9999 on 2018/7/6 15：19
 * @email：wangwenbinc@enn.cn
 */
class HistoryAdapter(layoutId: Int = R.layout.item_watch_history) : BaseQuickAdapter<VideoBean, BaseViewHolder>(layoutId) {
    
    override fun convert(helper: BaseViewHolder?, item: VideoBean?) {
        val imageUrl = item!!.feed
        imageUrl!!.let { GlideUtils.display(mContext, helper!!.getView(R.id.iv_history_img), it) }
        helper!!.setText(R.id.tv_history_title, item!!.title)
        val category = item!!.category
        val duration = item!!.duration
        val minute = duration?.div(60)
        val second = duration?.minus((minute?.times(60)) as Long)
        val time = item!!.time
        val dateFormat = SimpleDateFormat("MM-dd")
        val date = dateFormat.format(time)
        var realMinute: String
        var realSecond: String
        realMinute = if (minute!! < 10) "0" + minute else minute.toString()
        realSecond = if (second!! < 10) "0" + second else second.toString()
        helper.setText(R.id.tv_history_detail, "$category/$realMinute'$realSecond''/$date")
        //helper.addOnLongClickListener(R.id.cv_history_container)
    }
}