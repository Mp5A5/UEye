package www.mp5a5.com.ueye.module.discovery.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import www.mp5a5.com.ueye.R
import www.mp5a5.com.ueye.net.entity.DiscoveryEntity
import www.mp5a5.com.ueye.util.GlideUtils

/**
 * @describe
 * @author ：king9999 on 2018/7/13 16：06
 * @email：wangwenbinc@enn.cn
 */
class DiscoveryAdapter(layoutId: Int = R.layout.item_discovery) : BaseQuickAdapter<DiscoveryEntity, BaseViewHolder>(layoutId) {
    
    override fun convert(helper: BaseViewHolder?, item: DiscoveryEntity?) {
        item!!.bgPicture!!.let { GlideUtils.display(mContext, helper!!.getView(R.id.iv_discovery_bg), it) }
        helper!!.setText(R.id.tv_discovery_txt, item!!.name)
    }
    
}