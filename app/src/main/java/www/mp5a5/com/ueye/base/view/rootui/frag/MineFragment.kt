package www.mp5a5.com.ueye.base.view.rootui.frag

import android.os.Bundle
import kotlinx.android.synthetic.main.fragment_a.*
import www.mp5a5.com.ueye.R
import www.mp5a5.com.ueye.base.view.frag.BaseFragment

/**
 * @describe
 * @author ：king9999 on 2018/6/21 18：41
 * @email：wwb199055@enn.cn
 */
class MineFragment : BaseFragment() {
    
    private lateinit var mContent: String
    
    companion object {
        fun newInstance(): MineFragment {
            val fragment = MineFragment()
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
        return R.layout.fragment_a
    }
    
    override fun needHeader(): Boolean {
        return false
    }
    
    override fun initView() {
        super.initView()
        textView.text = "更多"
    }
    
    
}