package www.mp5a5.com.ueye.homemodule.view

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.graphics.BitmapFactory
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_home_detail.*
import www.mp5a5.com.kotlinmvp.util.LogUtils
import www.mp5a5.com.ueye.R
import www.mp5a5.com.ueye.base.view.act.BaseActivity
import www.mp5a5.com.ueye.net.entity.VideoBean
import www.mp5a5.com.ueye.util.GlideUtils
import www.mp5a5.com.ueye.util.StatusBarUtils
import www.mp5a5.com.ueye.util.VideoListener
import java.io.FileInputStream

/**
 * @describe
 * @author ：king9999 on 2018/6/23 11：28
 * @email：wwb199055@enn.cn
 */
class HomeDetailActivity : BaseActivity() {
    
    private lateinit var videoBean: VideoBean
    private lateinit var imageView: ImageView
    private lateinit var orientationUtils: OrientationUtils
    private var isPlay: Boolean = false
    private var isPause: Boolean = false
    
    override fun initLayoutView(): View {
        return View.inflate(thisActivity, R.layout.activity_home_detail, null)
    }
    
    override fun needHeader(): Boolean {
        return false
    }
    
    
    override fun initData() {
        super.initData()
        val bundle = intent.extras
        videoBean = bundle.getParcelable<VideoBean>("home_data")
    }
    
    override fun initView() {
        super.initView()
        LogUtils.e("-->" + videoBean.toString())
        StatusBarUtils.setColor(this, ContextCompat.getColor(thisActivity!!, R.color.white_50FFFFFF))
        val bgUrl = videoBean.blurred
        bgUrl?.let { GlideUtils.displayHigh(this, mHomeDetailBottomBg, bgUrl) }
        mHomeDetailVideoDescTv.text = videoBean.description
        mHomeDetailVideoDescTv.typeface = Typeface.createFromAsset(this.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
        mHomeDetailTitleTv.text = videoBean.title
        mHomeDetailTitleTv.typeface = Typeface.createFromAsset(this.assets, "fonts/FZLanTingHeiS-L-GB-Regular.TTF")
        val category = videoBean.category
        val duration = videoBean.duration
        val minute = duration?.div(60)
        val second = duration?.minus((minute?.times(60)) as Long)
        val realMinute: String
        val realSecond: String
        realMinute = if (minute!! < 10) "0$minute" else minute.toString()
        realSecond = if (second!! < 10) "0$second" else second.toString()
        mHomeDetailVideoTimeTv.text = "$category / $realMinute'$realSecond''"
        mHomeDetailVideoFavorTv.text = videoBean.collect.toString()
        mHomeDetailVideoShareTv.text = videoBean.share.toString()
        mHomeDetailVideoReplyTv.text = videoBean.share.toString()
        playVideo()
    }
    
    private fun playVideo() {
        val uri = intent.extras.getString("loaclFile")
        if (uri != null) {
            Log.e("uri", uri)
            mHomeDetailPlayerGsp.setUp(uri, false, null, null)
        } else {
            mHomeDetailPlayerGsp.setUp(videoBean.playUrl, false, null, null)
        }
        imageView = ImageView(this)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        addCover(imageView)
        mHomeDetailPlayerGsp.titleTextView.visibility = View.GONE
        mHomeDetailPlayerGsp.backButton.visibility = View.VISIBLE
        orientationUtils = OrientationUtils(this, mHomeDetailPlayerGsp)
        mHomeDetailPlayerGsp.setIsTouchWiget(true)
        //关闭自动旋转
        mHomeDetailPlayerGsp.isRotateViewAuto = false
        mHomeDetailPlayerGsp.isLockLand = false
        mHomeDetailPlayerGsp.isShowFullAnimation = false
        mHomeDetailPlayerGsp.isNeedLockFull = true
        playListener()
    }
    
    //增加封面
    private fun addCover(imageView: ImageView) {
        var mPath: String? = null
        var mInputStream: FileInputStream
        Flowable.create({ emitter: FlowableEmitter<String> ->
            val downloadOnly = Glide.with(thisActivity as BaseActivity)
                    .load(videoBean.feed)
                    .downloadOnly(100, 100)
            try {
                val cacheFile = downloadOnly.get()
                mPath = cacheFile.absolutePath
            } catch (e: Exception) {
                e.printStackTrace()
            }
            emitter.onNext(mPath!!)
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { t ->
                    mInputStream = FileInputStream(t)
                    val bitmap = BitmapFactory.decodeStream(mInputStream)
                    imageView.setImageBitmap(bitmap)
                    mHomeDetailPlayerGsp.thumbImageView = imageView
                }
    }
    
    //视频点击事件
    private fun playListener() {
        mHomeDetailPlayerGsp.fullscreenButton.setOnClickListener {
            //直接横屏
            orientationUtils.resolveByClick()
            //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏StatusBar
            mHomeDetailPlayerGsp.startWindowFullscreen(thisActivity, true, true)
        }
        
        mHomeDetailPlayerGsp.setVideoAllCallBack(object : VideoListener() {
            override fun onPrepared(url: String?, vararg objects: Any?) {
                super.onPrepared(url, *objects)
                //开始播放了才能旋转和全屏
                orientationUtils.isEnable = false
                isPlay = true
            }
            
            override fun onQuitFullscreen(url: String?, vararg objects: Any?) {
                super.onQuitFullscreen(url, *objects)
                orientationUtils.let { orientationUtils.backToProtVideo() }
            }
        })
        
        mHomeDetailPlayerGsp.setLockClickListener { view, lock ->
            //配合下方的onConfigurationChanged
            orientationUtils.isEnable = !lock
        }
        mHomeDetailPlayerGsp.backButton.setOnClickListener { onBackPressed() }
    }
    
    override fun initListener() {
        super.initListener()
        mHomeDetailVideoDownloadIv.setOnClickListener { }
    }
    
    override fun onPause() {
        super.onPause()
        isPause = true
    }
    
    override fun onResume() {
        super.onResume()
        isPause = false
    }
    
    override fun onDestroy() {
        super.onDestroy()
        GSYVideoManager.releaseAllVideos()
        orientationUtils?.let {
            orientationUtils.releaseListener()
        }
    }
    
    override fun onBackPressed() {
        orientationUtils.let {
            orientationUtils.backToProtVideo()
        }
        if (GSYVideoManager.backFromWindowFull(this)) {
            return
        }
        super.onBackPressed()
    }
    
    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        if (isPlay && !isPause) {
            if (newConfig!!.orientation == ActivityInfo.SCREEN_ORIENTATION_USER) {
                if (!mHomeDetailPlayerGsp.isIfCurrentIsFullscreen) {
                    mHomeDetailPlayerGsp.startWindowFullscreen(thisActivity, true, true)
                }
            } else {
                //新版本isIfCurrentIsFullscreen的标志位内部提前设置了，所以不会和手动点击冲突
                if (mHomeDetailPlayerGsp.isIfCurrentIsFullscreen) {
                    GSYVideoManager.backFromWindowFull(this)
                }
                orientationUtils.let { orientationUtils.isEnable = true }
            }
        }
    }
}