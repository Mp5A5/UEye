package www.mp5a5.com.ueye.base.view.rootui.frag

import www.mp5a5.com.ueye.base.view.frag.BaseFragment
import www.mp5a5.com.ueye.base.view.frag.FragmentFactory

/**
 * @describe
 * @author ：king9999 on 2018/6/21 20：10
 * @email：wwb199055@enn.cn
 */
object RootUiFragmentFactory : FragmentFactory() {
    
    private const val HOME_POSITION = 0
    private const val DISCOVERY_POSITION = 1
    private const val HOT_POSITION = 2
    private const val ME_POSITION = 3
    private const val MAX_SIZE = 4
    
    override fun createFragment(position: Int): BaseFragment {
        var fragment = fragSparseArray.get(position)
        if (fragment != null) {
            return fragment
        } else {
            when (position) {
                HOME_POSITION -> fragment = HomeFragment.newInstance()
                DISCOVERY_POSITION -> fragment = DiscoveryFragment.newInstance()
                HOT_POSITION -> fragment = HotFragment.newInstance()
                ME_POSITION -> fragment = MineFragment.newInstance()
            }
            fragSparseArray.put(position, fragment)
        }
        return fragment
    }
    
    override fun getFragmentSize(): Int {
        return MAX_SIZE
    }
    
}