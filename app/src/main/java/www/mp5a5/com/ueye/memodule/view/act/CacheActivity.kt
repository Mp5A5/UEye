package www.mp5a5.com.ueye.memodule.view.act

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_cache.*
import www.mp5a5.com.kotlinmvp.util.ToastUtils
import www.mp5a5.com.ueye.R
import www.mp5a5.com.ueye.base.mvp.BaseMvpActivity
import www.mp5a5.com.ueye.memodule.mvp.CacheContract
import www.mp5a5.com.ueye.memodule.mvp.CachePresenter
import www.mp5a5.com.ueye.memodule.view.adapter.CacheAdapter
import www.mp5a5.com.ueye.net.entity.VideoBean

/**
 * @describe
 * @author ：king9999 on 2018/6/30 13：29
 * @email：wangwenbinc@enn.cn
 */
class CacheActivity : BaseMvpActivity<CachePresenter>(), CacheContract.View {
    
    private var mAdapter: CacheAdapter? = null
    
    override fun showMsg(msg: String) {
        ToastUtils.show(msg)
    }
    
    override fun <E> setData(list: List<E>) {
        mAdapter!!.addData(list as List<VideoBean>)
    }
    
    override fun getBasePresenter(): CachePresenter {
        return CachePresenter()
    }
    
    override fun initLayoutView(): View {
        return View.inflate(thisActivity!!, R.layout.activity_cache, null)
    }
    
    override fun initView() {
        super.initView()
        setSupportActionBar(tMeToolbar)
        val actionBar = supportActionBar
        actionBar?.title = "我的缓存"
        actionBar?.setDisplayHomeAsUpEnabled(true)
        rvMeRecyclerView.setHasFixedSize(true)
        rvMeRecyclerView.layoutManager = LinearLayoutManager(thisActivity)
    }
    
    override fun needHeader(): Boolean {
        return false
    }
    
    override fun initAdapter() {
        super.initAdapter()
        mAdapter = CacheAdapter()
        rvMeRecyclerView.adapter = mAdapter
    }
    
    override fun initListener() {
        super.initListener()
        tMeToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
    
    override fun initNet() {
        super.initNet()
        presenter.requestDao(thisActivity!!)
    }
    
}