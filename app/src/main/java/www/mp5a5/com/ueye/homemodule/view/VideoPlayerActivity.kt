package www.mp5a5.com.ueye.homemodule.view

import android.graphics.BitmapFactory
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_video_player.*
import kotlinx.android.synthetic.main.item_video_player_bottom_view.*
import kotlinx.android.synthetic.main.item_video_player_detail.*
import www.mp5a5.com.ueye.R
import www.mp5a5.com.ueye.base.view.act.BaseActivity
import www.mp5a5.com.ueye.dao.VideoEntityCache
import www.mp5a5.com.ueye.dao.VideoEntityDaoUtil
import www.mp5a5.com.ueye.net.entity.VideoBean
import www.mp5a5.com.ueye.util.*
import java.io.FileInputStream

/**
 * @describe
 * @author ：king9999 on 2018/7/9 11：21
 * @email：wangwenbinc@enn.cn
 */
class VideoPlayerActivity : BaseActivity() {
    
    private var mVideoBean: VideoBean? = null
    private lateinit var mOrientationUtils: OrientationUtils
    private var isPlay: Boolean = false
    
    override fun initLayoutView(): View {
        return View.inflate(thisActivity!!, R.layout.activity_video_player, null)
    }
    
    override fun needHeader(): Boolean {
        return false
    }
    
    override fun initData() {
        super.initData()
        val bundle = intent.extras
        mVideoBean = bundle.getParcelable<VideoBean>(ConstantUtil.VIDEO_BEAN)
    }
    
    override fun initView() {
        super.initView()
        StatusBarUtils.setColor(this, ContextCompat.getColor(thisActivity!!, R.color.white_50FFFFFF))
        mVideoBean!!.playUrl.let { GlideUtils.displayHigh(thisActivity!!, mVideoPlayerBottomBg, it!!) }
        mVideoPlayerDescTv.text = mVideoBean!!.description!!
        mVideoPlayerDescTv.typeface = Typeface.createFromAsset(this.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
        mVideoPlayerTitleTv.text = mVideoBean!!.title!!
        mVideoPlayerTitleTv.typeface = Typeface.createFromAsset(this.assets, "fonts/FZLanTingHeiS-L-GB-Regular.TTF")
        val category = mVideoBean!!.category
        val duration = mVideoBean!!.duration
        val minute = duration!!.div(60)
        val second = duration!!.minus((minute.times(60)) as Long)
        val realMinute: String
        val realSecond: String
        realMinute = if (minute < 10) "0$minute" else minute.toString()
        realSecond = if (second < 10) "0$second" else second.toString()
        mVideoPlayerTimeTv.text = "$category/$realMinute'$realSecond''"
        mVideoPlayerFavorTv.text = mVideoBean!!.collect!!.toString()
        mVideoPlayerShareTv.text = mVideoBean!!.share!!.toString()
        mVideoPlayerReplyTv.text = mVideoBean!!.reply!!.toString()
        initPlayer()
    }
    
    private fun initPlayer() {
        //存入数据库------对应播放历史
        val list = mVideoBean!!.id.let { VideoEntityDaoUtil.queryForId(it) }
        if (CollectionUtils.isEmpty(list)) {
            VideoEntityDaoUtil.insertData(VideoEntityCache(mVideoBean!!.id.toLong(), mVideoBean!!.id, mVideoBean!!.playUrl!!, GsonUtils.toString(mVideoBean!!)))
        }
        
        //增加视频封面
        val imageView = ImageView(thisActivity!!)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        Flowable.create<String>({ e: FlowableEmitter<String> ->
            val target = Glide.with(thisActivity!!)
                    .load(mVideoBean!!.feed!!)
                    .downloadOnly(100, 100)
            var mPath: String? = null
            try {
                val file = target.get()
                mPath = file.absolutePath
            } catch (e: Exception) {
                e.printStackTrace()
            }
            e.onNext(mPath!!)
            
        }, BackpressureStrategy.BUFFER)
                .flatMap { t ->
                    val inputStream = FileInputStream(t)
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    Flowable.just(bitmap)
                }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { b ->
                    imageView.setImageBitmap(b)
                    mVideoPlayerGsp.thumbImageView = imageView
                }
        
        
        mVideoPlayerGsp.titleTextView.visibility = View.VISIBLE
        mVideoPlayerGsp.backButton.visibility = View.VISIBLE
        mOrientationUtils = OrientationUtils(thisActivity!!, mVideoPlayerGsp)
        mVideoPlayerGsp.setIsTouchWiget(true)
        mVideoPlayerGsp.setIsTouchWigetFull(true)
        mVideoPlayerGsp.isRotateViewAuto = false
        mVideoPlayerGsp.isLockLand = false
        mVideoPlayerGsp.isAutoFullWithSize = true
        mVideoPlayerGsp.isShowFullAnimation = true
        mVideoPlayerGsp.isNeedLockFull = true
        
        val uri = intent.extras.getString("localFile")
        if (uri != null) {
            mVideoPlayerGsp.setUp(uri, false, null, mVideoBean!!.title)
        } else {
            mVideoPlayerGsp.setUp(mVideoBean!!.playUrl!!, false, null, mVideoBean!!.title)
        }
        
        initPlayListener()
        
    }
    
    private fun initPlayListener() {
        mVideoPlayerGsp.setVideoAllCallBack(object : VideoListener() {
            override fun onPrepared(url: String?, vararg objects: Any?) {
                super.onPrepared(url, *objects)
                //开始播放了才能旋转和全屏
                mOrientationUtils.isEnable = true
                isPlay = true
            }
            
            override fun onQuitFullscreen(url: String?, vararg objects: Any?) {
                super.onQuitFullscreen(url, *objects)
                mOrientationUtils.backToProtVideo()
            }
        })
        
        mVideoPlayerGsp.setLockClickListener { _,lock -> mOrientationUtils.isEnable = !lock}
    }
}