package www.mp5a5.com.ueye.base.view.frag


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.components.support.RxFragment
import www.mp5a5.com.ueye.R
import java.lang.ref.WeakReference


abstract class BaseFragment : RxFragment() {
    private var mContext: RxAppCompatActivity? = null
    private var iView: View? = null
    private var rightBtnTv: TextView? = null
    private var midTitleTv: TextView? = null
    private var leftBtnTv: TextView? = null
    private var viewLineV: View? = null
    private var v: WeakReference<View>? = null
    
    val rightBtn: TextView?
        get() = if (null != rightBtnTv) rightBtnTv else null
    
    val leftBtn: TextView?
        get() = if (null != leftBtnTv) leftBtnTv else null
    
    val midBtn: TextView?
        get() = if (null != midTitleTv) midTitleTv else null
    
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = activity as RxAppCompatActivity?
        initArgsData()
    }
    
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (v == null || v!!.get() == null) {
            iView = inflater.inflate(initLayout(), container, false)
            v = WeakReference(iView!!)
        }
        val parent = v!!.get()!!.parent as? ViewGroup
        if (parent != null) {
            parent.removeView(v!!.get()!!)
        }
        return if (needHeader()) getMergerView(v!!.get()!!) else v!!.get()!!
    }
    
    private fun getMergerView(v: View): View {
        val rootView = View.inflate(activity, R.layout.title_bar_frag, null)
        leftBtnTv = rootView.findViewById(R.id.tv_left_btn)
        rightBtnTv = rootView.findViewById(R.id.tv_right_btn)
        midTitleTv = rootView.findViewById(R.id.tv_mid_title)
        viewLineV = rootView.findViewById(R.id.v_line)
        midTitleTv!!.text = setTitle()
        val baseContainerRl = rootView.findViewById(R.id.rl_base_container) as FrameLayout
        leftBtnClickListener()
        baseContainerRl.addView(v)
        return rootView
    }
    
    
    open fun setTitle(): String {
        return ""
    }
    
    protected open fun setTitle(title: String) {
        if (midTitleTv != null) {
            midTitleTv!!.text = title
        }
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initView()
        initAdapter()
        initNet()
        initListener()
        initRefresh()
    }
    
    
    override fun onResume() {
        super.onResume()
        mContext = activity as RxAppCompatActivity?
        initNetWork()
    }
    
    protected open fun needHeader(): Boolean {
        return true
    }
    
    protected abstract fun initLayout(): Int
    
    protected open fun initArgsData() {
    
    }
    
    protected open fun init() {}
    
    protected open fun initView() {}
    
    protected open fun initNet() {}
    
    protected open fun initAdapter() {}
    
    protected open fun initListener() {}
    
    protected open fun initRefresh() {}
    
    protected open fun initNetWork() {}
    
    
    private fun leftBtnClickListener() {
        leftBtnTv!!.setOnClickListener { mContext!!.finish() }
    }
    
    fun setLeftVisible(visible: Boolean) {
        if (null != leftBtnTv) {
            if (visible) {
                leftBtnTv!!.visibility = View.VISIBLE
            } else {
                leftBtnTv!!.visibility = View.INVISIBLE
            }
        }
    }
    
    fun setRightTextViewVisible(visible: Boolean, txt: String) {
        if (null != rightBtnTv) {
            if (visible) {
                rightBtnTv!!.visibility = View.VISIBLE
                rightBtnTv!!.text = txt
            } else {
                rightBtnTv!!.visibility = View.INVISIBLE
            }
        }
    }
    
    
    val thisActivity: RxAppCompatActivity?
        get() = mContext
    
    val thisContext: Context?
        get() = mContext
    
    inline fun <reified T : Activity> gotoActivity() {
        thisActivity!!.startActivity(Intent(thisActivity, T::class.java))
    }
    
    
    inline fun <reified T : Activity> gotoActivity(bundle: Bundle, isFinish: Boolean) {
        val intent = Intent(thisActivity!!, T::class.java)
        intent.putExtras(bundle)
        thisActivity!!.startActivity(intent)
        if (isFinish) {
            thisActivity!!.finish()
        }
    }
    
}
