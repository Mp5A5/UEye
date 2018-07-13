package www.mp5a5.com.ueye.module.discovery.frag

import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.fragment_discovery.*
import www.mp5a5.com.ueye.R
import www.mp5a5.com.ueye.base.view.frag.BaseFragment
import www.mp5a5.com.ueye.util.DateUtil

/**
 * @describe
 * @author ：king9999 on 2018/6/21 18：41
 * @email：wwb199055@enn.cn
 */
class DiscoveryFragment : BaseFragment() {
    
    private lateinit var mContent: String
    
    companion object {
        fun newInstance(): DiscoveryFragment {
            val fragment = DiscoveryFragment()
            val bundle = Bundle()
            bundle.putString("msg", "")
            fragment.arguments = bundle
            return fragment
        }
    }
    
    override fun initArgsData() {
        super.initArgsData()
        mContent = arguments!!.getString("msg")
    }
    
    override fun initLayout(): Int {
        return R.layout.fragment_discovery
    }
    
    override fun setTitle(): String {
        return DateUtil.getToday()
    }
    
    override fun initView() {
        super.initView()
        leftBtn!!.visibility = View.GONE
        midBtn!!.typeface = Typeface.createFromAsset(thisActivity!!.assets, "fonts/Lobster-1.4.otf")
        rvDiscoveryRecyclerView.setHasFixedSize(true)
        rvDiscoveryRecyclerView.layoutManager = GridLayoutManager(thisActivity!!, 2)
    }
    
    override fun initAdapter() {
        super.initAdapter()
        
    }
    
}