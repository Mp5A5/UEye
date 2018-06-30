package www.mp5a5.com.ueye.memodule.view.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import www.mp5a5.com.kotlinmvp.util.LogUtils
import www.mp5a5.com.ueye.R
import www.mp5a5.com.ueye.dao.VideoEntityDaoUtil
import www.mp5a5.com.ueye.net.entity.CustomMission
import www.mp5a5.com.ueye.net.entity.VideoBean
import www.mp5a5.com.ueye.util.CollectionUtils
import www.mp5a5.com.ueye.util.GlideUtils
import zlc.season.rxdownload3.RxDownload
import zlc.season.rxdownload3.core.DownloadConfig.context
import zlc.season.rxdownload3.core.Downloading
import zlc.season.rxdownload3.core.Failed
import zlc.season.rxdownload3.helper.dispose

/**
 * @describe
 * @author ：king9999 on 2018/6/30 13：46
 * @email：wangwenbinc@enn.cn
 */
class DownloadAdapter(layoutId: Int = R.layout.item_download) : BaseQuickAdapter<VideoBean, BaseViewHolder>(layoutId) {
    
    lateinit var disposable: Disposable
    var isDownload = false
    var hasLoaded = false
    
    override fun convert(helper: BaseViewHolder?, item: VideoBean?) {
        
        
        val photoUrl: String? = item?.feed
        
        photoUrl?.let { GlideUtils.display(context!!, helper!!.getView(R.id.iv_photo), it) }
        val title: String? = item?.title
        helper!!.setText(R.id.tv_title, title)
        var category = item?.category
        var duration = item?.duration
        initDownloadState(item, helper)
        VideoEntityDaoUtil.queryForId(mContext, item!!.id).subscribe { idList ->
            if (CollectionUtils.isNotEmpty(idList)) {
                isDownload = false
                helper.setImageResource(R.id.iv_download_state, R.mipmap.icon_download_stop)
            } else {
                isDownload = true
                helper.setImageResource(R.id.iv_download_state, R.mipmap.icon_download_start)
            }
        }
        
        helper!!.getView<ImageView>(R.id.iv_download_state).setOnClickListener {
            if (isDownload) {
                isDownload = false
                helper.setImageResource(R.id.iv_download_state, R.mipmap.icon_download_start)
            } else {
                isDownload = true
                helper.setImageResource(R.id.iv_download_state, R.mipmap.icon_download_stop)
            }
        }
        
        helper.addOnLongClickListener(R.id.rl_me_container)
        
    }
    
    //初始化下载状态
    private fun initDownloadState(item: VideoBean?, helper: BaseViewHolder?) {
        val mission = CustomMission(item!!.playUrl!!, item!!.title!!)
        disposable = RxDownload.create(mission).observeOn(AndroidSchedulers.mainThread()).subscribe { status ->
            
            when (status) {
                is Failed -> LogUtils.e("-->" + "Error")
                is Downloading -> {
                    val percent = status.percent()
                    if (percent.toLong() == 100L) {
                        if (!disposable.isDisposed) {
                            dispose(disposable)
                        }
                        hasLoaded = true
                        helper?.setVisible(R.id.iv_download_state, false)
                        helper?.setText(R.id.tv_detail, "已缓存")
                        isDownload = false
                    } else {
                        helper?.setVisible(R.id.iv_download_state, true)
                        if (isDownload) {
                            helper?.setText(R.id.tv_detail, "缓存中 / $percent%")
                        } else {
                            helper?.setText(R.id.tv_detail, "已暂停 / $percent%")
                        }
                    }
                    
                }
            }
        }
    }
}