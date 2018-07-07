package www.mp5a5.com.ueye.memodule.view.act

import android.os.Bundle
import android.os.Parcelable
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import kotlinx.android.synthetic.main.activity_watch_history.*
import www.mp5a5.com.kotlinmvp.util.ToastUtils
import www.mp5a5.com.ueye.R
import www.mp5a5.com.ueye.base.mvp.BaseMvpActivity
import www.mp5a5.com.ueye.dao.VideoEntityCache
import www.mp5a5.com.ueye.homemodule.view.HomeDetailActivity
import www.mp5a5.com.ueye.memodule.mvp.CacheContract
import www.mp5a5.com.ueye.memodule.mvp.CachePresenter
import www.mp5a5.com.ueye.memodule.view.adapter.HistoryAdapter
import www.mp5a5.com.ueye.net.entity.VideoBean
import www.mp5a5.com.ueye.util.CollectionUtils
import www.mp5a5.com.ueye.util.GsonUtils

/**
 * @describe
 * @author ：king9999 on 2018/6/30 13：29
 * @email：wangwenbinc@enn.cn
 */
class WatchHistoryActivity : BaseMvpActivity<CachePresenter>(), CacheContract.View, BaseQuickAdapter.OnItemLongClickListener, BaseQuickAdapter.OnItemClickListener {
    
    
    private var mList: MutableList<VideoBean>? = null
    
    private var mAdapter: HistoryAdapter? = null
    
    override fun getBasePresenter(): CachePresenter {
        return CachePresenter()
    }
    
    override fun initLayoutView(): View {
        return View.inflate(thisActivity!!, R.layout.activity_watch_history, null)
    }
    
    override fun initView() {
        super.initView()
        setSupportActionBar(tMeToolbar)
        val actionBar = supportActionBar
        actionBar?.title = "观看历史"
        actionBar?.setDisplayHomeAsUpEnabled(true)
        rvMeRecyclerView.setHasFixedSize(true)
        rvMeRecyclerView.layoutManager = LinearLayoutManager(thisActivity)
    }
    
    override fun needHeader(): Boolean {
        return false
    }
    
    override fun initAdapter() {
        super.initAdapter()
        mAdapter = HistoryAdapter()
        rvMeRecyclerView.adapter = mAdapter
    }
    
    override fun initListener() {
        super.initListener()
        tMeToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        tvCacheClearAll.setOnClickListener { presenter.deleteAllCache() }
        mAdapter!!.onItemLongClickListener = this
        mAdapter!!.onItemClickListener = this
    }
    
    override fun initNet() {
        super.initNet()
        presenter.requestDao()
    }
    
    override fun showMsg(msg: String) {
        ToastUtils.show(msg)
    }
    
    override fun <E> setData(list: List<E>) {
        mList = list as MutableList<VideoBean>
        mAdapter!!.addData(list as List<VideoBean>)
    }
    
    override fun setNullData() {
        mAdapter!!.setNewData(null)
    }
    
    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        val videoBean = adapter!!.data[position] as VideoBean
        val bundle = Bundle()
        bundle.putParcelable("home_data", videoBean as Parcelable)
        gotoActivity<HomeDetailActivity>(bundle, false)
    }
    
    override fun onItemLongClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int): Boolean {
        val bean = adapter!!.data[position] as VideoBean
        val entityCache = VideoEntityCache(bean.id.toLong(), bean.id, bean.playUrl, GsonUtils.toString(bean))
        showDeleteDialog(entityCache, position)
        return false
    }
    
    private fun showDeleteDialog(bean: VideoEntityCache, position: Int) {
        val builder = AlertDialog.Builder(thisActivity!!)
        val dialog = builder.create()
        builder.setMessage("是否删除当前记录")
        builder.setNegativeButton("否") { _, _ -> dialog.dismiss() }
        builder.setPositiveButton("是") { _, _ ->
            presenter.deleteOneCache(bean)
            if (CollectionUtils.isNotEmpty(mList)) {
                mList!!.removeAt(position)
            }
            mAdapter!!.setNewData(mList!!)
        }
        builder.show()
    }
}