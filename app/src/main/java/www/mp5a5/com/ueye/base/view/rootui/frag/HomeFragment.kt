package www.mp5a5.com.ueye.base.view.rootui.frag

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_a.*
import www.mp5a5.com.ueye.base.view.frag.BaseFragment
import www.mp5a5.com.ueye.R

/**
 * @describe
 * @author ：king9999 on 2018/6/21 18：41
 * @email：wwb199055@enn.cn
 */
class HomeFragment : BaseFragment() {
    
    private lateinit var mContent: String
    
    companion object {
        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
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
    
    /*  override fun needHeader(): Boolean {
          return false
      }*/
    
    override fun setTitle(): String {
        return "新闻"
    }
    
    override fun initView() {
        super.initView()
        leftBtn!!.visibility = View.GONE
        textView.text = "新闻"
    }
    
}