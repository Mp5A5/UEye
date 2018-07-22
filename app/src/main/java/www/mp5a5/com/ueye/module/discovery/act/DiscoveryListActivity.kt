package www.mp5a5.com.ueye.module.discovery.act

import android.graphics.Color
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import kotlinx.android.synthetic.main.activity_discovery_list.*
import www.mp5a5.com.kotlinmvp.util.ToastUtils
import www.mp5a5.com.ueye.R
import www.mp5a5.com.ueye.base.mvp.BaseMvpActivity
import www.mp5a5.com.ueye.customview.RecyclerViewItemDecoration
import www.mp5a5.com.ueye.module.discovery.adapter.DiscoveryListAdapter
import www.mp5a5.com.ueye.module.discovery.mvp.contract.DiscoveryListContract
import www.mp5a5.com.ueye.module.discovery.mvp.presenter.DiscoveryListPresenter
import www.mp5a5.com.ueye.module.home.act.VideoPlayerActivity
import www.mp5a5.com.ueye.net.entity.DiscoveryListInfo
import www.mp5a5.com.ueye.net.entity.VideoBean
import www.mp5a5.com.ueye.util.ConstantUtil

/**
 * @describe
 * @author ：king9999 on 2018/7/22 16：06
 * @email：wangwenbinc@enn.cn
 */
class DiscoveryListActivity : BaseMvpActivity<DiscoveryListPresenter>(), DiscoveryListContract.View, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    
    private lateinit var mTitle: String
    private var mAdapter: DiscoveryListAdapter? = null
    private var mIndex = 10
    
    
    override fun getBasePresenter(): DiscoveryListPresenter {
        return DiscoveryListPresenter()
    }
    
    override fun initLayoutView(): View {
        return View.inflate(thisActivity!!, R.layout.activity_discovery_list, null)
    }
    
    override fun needHeader(): Boolean {
        return false
    }
    
    override fun initData() {
        super.initData()
        mTitle = thisActivity!!.intent.getStringExtra("discovery_name")
    }
    
    override fun initView() {
        super.initView()
        setSupportActionBar(tb_discovery_list_toolbar)
        val actionBar = supportActionBar
        actionBar!!.title = mTitle
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        srl_discovery_list_refresh.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN)
        rv_discovery_list_recycler_view.setHasFixedSize(true)
        rv_discovery_list_recycler_view.layoutManager = LinearLayoutManager(thisActivity!!)
        val mutableMapOf = mutableMapOf<RecyclerViewItemDecoration.SpaceType, Int>()
        mutableMapOf[RecyclerViewItemDecoration.SpaceType.BOTTOM] = 10
        mutableMapOf[RecyclerViewItemDecoration.SpaceType.LEFT] = 10
        mutableMapOf[RecyclerViewItemDecoration.SpaceType.RIGHT] = 10
        rv_discovery_list_recycler_view.addItemDecoration(RecyclerViewItemDecoration(mutableMapOf))
    }
    
    override fun initAdapter() {
        super.initAdapter()
        mAdapter = DiscoveryListAdapter()
        rv_discovery_list_recycler_view.adapter = mAdapter
    }
    
    override fun initNet() {
        super.initNet()
        presenter.requestNetWork(thisActivity!!, mTitle, "date", true)
    }
    
    override fun initListener() {
        super.initListener()
        tb_discovery_list_toolbar.setNavigationOnClickListener { onBackPressed() }
        srl_discovery_list_refresh.isRefreshing = true
        srl_discovery_list_refresh.setOnRefreshListener(this)
        mAdapter!!.setOnLoadMoreListener(this, rv_discovery_list_recycler_view)
        mAdapter!!.onItemClickListener = this
    }
    
    override fun showMsg(msg: String) {
        ToastUtils.show(msg)
    }
    
    override fun unableRefresh() {
        srl_discovery_list_refresh.isRefreshing = false
    }
    
    override fun enableLoadMore() {
        mAdapter!!.setEnableLoadMore(true)
    }
    
    override fun unableLoadMore() {
        mAdapter!!.setEnableLoadMore(false)
    }
    
    override fun loadMoreEnd(isEnd: Boolean) {
        mAdapter!!.loadMoreEnd(isEnd)
    }
    
    override fun loadMoreComplete() {
        mAdapter!!.loadMoreComplete()
    }
    
    override fun loadMoreFailed() {
        mAdapter!!.loadMoreFail()
    }
    
    override fun <E> setNewData(list: List<E>) {
        mAdapter!!.setNewData(list as List<DiscoveryListInfo.ItemListBean.DataBean>)
    }
    
    override fun <E> addData(list: List<E>) {
        mAdapter!!.addData(list as List<DiscoveryListInfo.ItemListBean.DataBean>)
    }
    
    override fun addIndex() {
        mIndex = mIndex.plus(10)
    }
    
    override fun resetIndex() {
        mIndex = 10
    }
    
    override fun onRefresh() {
        presenter.requestNetWork(thisActivity!!, mTitle, "date", true)
    }
    
    override fun onLoadMoreRequested() {
        presenter.requestMoreNetWork(thisActivity!!, mIndex, mTitle, "date", false)
    }
    
    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        val dataInfo = adapter!!.data[position] as DiscoveryListInfo.ItemListBean.DataBean
        val id = dataInfo.id
        val feed = dataInfo.cover!!.feed
        val title = dataInfo.title
        val desc = dataInfo.description
        val duration = dataInfo.duration
        val playUrl = dataInfo.playUrl
        val category = dataInfo.category
        val blurred = dataInfo.cover?.blurred
        val collect = dataInfo.consumption?.collectionCount
        val share = dataInfo.consumption?.shareCount
        val reply = dataInfo.consumption?.replyCount
        val time = System.currentTimeMillis()
        val videoBean = VideoBean(id, feed, title, desc, duration, playUrl, category, blurred, collect, share, reply, time)
        val bundle = Bundle()
        bundle.putParcelable(ConstantUtil.VIDEO_BEAN, videoBean)
        gotoActivity<VideoPlayerActivity>(bundle, false)
    }
}
