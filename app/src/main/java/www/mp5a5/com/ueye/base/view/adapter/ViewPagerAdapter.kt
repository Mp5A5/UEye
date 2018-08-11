package www.mp5a5.com.ueye.base.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * @author ：king9999 on 2018/6/19 20：07
 * @describe
 * @email：wwb199055@enn.cn
 */
class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
    
    
    override fun getItem(position: Int): Fragment {
        return RootUiFragmentFactory.createFragment(position)
    }
    
    override fun getCount(): Int {
        return RootUiFragmentFactory.getFragmentSize()
    }
    
    override fun getPageTitle(position: Int): CharSequence? {
        val fragment = RootUiFragmentFactory.createFragment(position)
        return if (fragment.needHeader()) fragment.setTitle() else null
        
    }
    
    
}
