package www.mp5a5.com.ueye.module.home.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import www.mp5a5.com.ueye.R
import www.mp5a5.com.ueye.net.entity.HomeBean.IssueListBean.ItemListBean
import www.mp5a5.com.ueye.util.GlideUtils

/**
 * @describe
 * @author ：king9999 on 2018/6/22 17：09
 * @email：wwb199055@enn.cn
 */
class HomeAdapter(layoutId: Int = R.layout.item_home) : BaseQuickAdapter<ItemListBean, BaseViewHolder>(layoutId) {
    
    override fun convert(helper: BaseViewHolder?, item: ItemListBean?) {
        
        val title = item?.data?.title
        val category = item?.data?.category
        val minute = item?.data?.duration?.div(60)
        val second = item?.data?.duration?.minus((minute?.times(60)) as Long)
        val realMinute: String
        val realSecond: String
        
        if (minute!! < 10) realMinute = "0" + minute else realMinute = minute.toString()
        if (second!! < 10) realSecond = "0" + second else realSecond = second.toString()
        var playUrl = item?.data?.playUrl
        val photo = item?.data?.cover?.feed
        val author = item?.data?.author
        
        GlideUtils.display(mContext!!, helper!!.getView(R.id.iv_home_Photo), photo!!)
        helper.setText(R.id.tv_home_title, title)
        helper.setText(R.id.tv_home_detail, "发布于 $category / $realMinute:$realSecond")
        if (author != null) {
            GlideUtils.display(mContext, helper!!.getView(R.id.iv_home_use), author.icon)
        } else {
            helper.setVisible(R.id.iv_home_use, false)
        }
    }
}