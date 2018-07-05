package www.mp5a5.com.ueye.memodule.view.frag

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_me.*
import www.mp5a5.com.ueye.R
import www.mp5a5.com.ueye.base.view.frag.BaseFragment
import www.mp5a5.com.ueye.memodule.view.act.CacheActivity

/**
 * @describe
 * @author ：king9999 on 2018/6/21 18：41
 * @email：wwb199055@enn.cn
 */
class MineFragment : BaseFragment(), View.OnClickListener {
    
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
        return R.layout.fragment_me
    }
    
    override fun needHeader(): Boolean {
        return false
    }
    
    override fun initView() {
        super.initView()
        tv_advise.typeface = Typeface.createFromAsset(thisContext?.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
        tv_watch.typeface = Typeface.createFromAsset(thisContext?.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
        tv_save.typeface = Typeface.createFromAsset(thisContext?.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
    }
    
    override fun initListener() {
        super.initListener()
        tv_advise.setOnClickListener(this)
        tv_watch.setOnClickListener(this)
        tv_save.setOnClickListener(this)
    }
    
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.tv_watch -> {
                //gotoActivity<WatchActivity>()
            }
            R.id.tv_advise -> {
                //gotoActivity<AdviseActivity>()
            }
            R.id.tv_save -> {
                gotoActivity<CacheActivity>()
            }
        }
    }
    
}