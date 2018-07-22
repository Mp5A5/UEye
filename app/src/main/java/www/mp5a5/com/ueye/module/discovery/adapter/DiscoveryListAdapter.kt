package www.mp5a5.com.ueye.module.discovery.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import www.mp5a5.com.ueye.R
import www.mp5a5.com.ueye.net.entity.DiscoveryListInfo
import www.mp5a5.com.ueye.util.GlideUtils

/**
 * @describe
 * @author ：king9999 on 2018/7/22 18：34
 * @email：wangwenbinc@enn.cn
 */
class DiscoveryListAdapter(layoutId: Int = R.layout.item_discovery_list) : BaseQuickAdapter<DiscoveryListInfo.ItemListBean.DataBean, BaseViewHolder>(layoutId) {
    
    override fun convert(helper: BaseViewHolder?, info: DiscoveryListInfo.ItemListBean.DataBean?) {
        val itemBg = info!!.cover!!.feed
        GlideUtils.display(mContext, helper!!.getView(R.id.iv_discovery_list_bg), itemBg!!)
        helper.setText(R.id.tv_title, info!!.title!!)
        val category = info!!.category
        val duration = info!!.duration
        val minute = duration.div(60)
        val second = duration.minus((minute.times(60) as Long))
        val realMinute: String
        val realSecond: String
        realMinute = if (minute < 10) "0" + minute else minute.toString()
        realSecond = if (second < 10) "0" + second else second.toString()
        helper.setText(R.id.tv_category, "$category/$realMinute'$realSecond''")
    }
}