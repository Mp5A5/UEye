package www.mp5a5.com.ueye.base.view.act

import android.graphics.Typeface
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.ScaleAnimation
import kotlinx.android.synthetic.main.activity_splash.*
import www.mp5a5.com.ueye.R

/**
 * @describe
 * @author ：king9999 on 2018/6/30 12：34
 * @email：wangwenbinc@enn.cn
 */
class SplashActivity : BaseActivity() {
    
    override fun initLayoutView(): View {
        return View.inflate(thisActivity!!, R.layout.activity_splash, null)
    }
    
    override fun needHeader(): Boolean {
        return false
    }
    
    override fun initView() {
        super.initView()
        val font: Typeface = Typeface.createFromAsset(this.assets, "fonts/Lobster-1.4.otf")
        tvEnglishNameIntro.typeface = font
        tvEnglishIntro.typeface = font
        initAnimation()
    }
    
    private fun initAnimation() {
        val animation = AlphaAnimation(0.1f, 1.0f)
        animation.duration = 1000
        val scaleAnimation = ScaleAnimation(0.1f, 1.0f, 0.1f, 1.0f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f)
        scaleAnimation.duration = 1000
        val animationSet = AnimationSet(true)
        animationSet.addAnimation(animation)
        animationSet.addAnimation(scaleAnimation)
        ivIconSplash.startAnimation(animationSet)
        animationSet.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            
            }
            
            override fun onAnimationStart(animation: Animation?) {
            }
            
            override fun onAnimationEnd(animation: Animation?) {
                gotoActivity<HomeActivity>()
            }
            
        })
    }
}