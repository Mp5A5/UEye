package www.mp5a5.com.ueye.base.view.act

import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_home.*
import www.mp5a5.com.kotlinmvp.util.ToastUtils
import www.mp5a5.com.ueye.R
import www.mp5a5.com.ueye.base.view.adapter.ViewPagerAdapter
import www.mp5a5.com.ueye.customview.BottomNavigationViewHelper

/**
 * @describe
 * @author ：king9999 on 2018/6/21 18：26
 * @email：wwb199055@enn.cn
 */
class HomeActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {
    
    private var item: MenuItem? = null
    private var startTime: Long = 0
    private var endTime: Long = 0
    
    override fun initLayoutView(): View {
        return View.inflate(thisActivity, R.layout.activity_home, null)
    }
    
    override fun needHeader(): Boolean {
        return false
    }
    
    override fun initView() {
        super.initView()
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigation)
        setupViewPager(mViewpager)
        initListener()
    }
    
    override fun initListener() {
        mBottomNavigation.setOnNavigationItemSelectedListener(this)
        mViewpager.addOnPageChangeListener(this)
        
        //禁止ViewPager滑动
        //mViewpager.setOnTouchListener { v, event -> false }
    }
    
    private fun setupViewPager(mViewpager: ViewPager?) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        mViewpager!!.offscreenPageLimit = 4
        mViewpager!!.adapter = adapter
    }
    
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_home -> mViewpager.currentItem = 0
            R.id.item_find -> mViewpager.currentItem = 1
            R.id.item_hot -> mViewpager.currentItem = 2
            R.id.item_me -> mViewpager.currentItem = 3
        }
        return false
    }
    
    override fun onPageScrollStateChanged(state: Int) {
    }
    
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }
    
    override fun onPageSelected(position: Int) {
        if (item != null) item!!.isChecked = false else mBottomNavigation.menu.getItem(0).isChecked = false
        item = mBottomNavigation.menu.getItem(position)
        item!!.isChecked = false
    }
    
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event!!.action == KeyEvent.ACTION_DOWN) {
            endTime = System.currentTimeMillis()
            if (endTime - startTime < 2000) {
                finish()
                android.os.Process.killProcess(android.os.Process.myPid())
                System.exit(0)
            } else {
                ToastUtils.show("再按一次退出程序")
                startTime = endTime
            }
        }
        return true
    }
}


