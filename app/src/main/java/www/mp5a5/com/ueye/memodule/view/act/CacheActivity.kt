package www.mp5a5.com.ueye.memodule.view.act

import android.view.View
import kotlinx.android.synthetic.main.activity_cache.*
import www.mp5a5.com.ueye.R
import www.mp5a5.com.ueye.base.view.act.BaseActivity

/**
 * @describe
 * @author ：king9999 on 2018/6/30 13：29
 * @email：wangwenbinc@enn.cn
 */
class CacheActivity : BaseActivity() {
    
    override fun initLayoutView(): View {
        return View.inflate(thisActivity!!, R.layout.activity_cache, null)
    }
    
    override fun initView() {
        super.initView()
        setSupportActionBar(tMeToolbar)
        val actionBar = supportActionBar
        actionBar?.title = "我的缓存"
        actionBar?.setDisplayHomeAsUpEnabled(true)
        
    }
    
    override fun initListener() {
        super.initListener()
        tMeToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}