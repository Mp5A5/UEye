package www.mp5a5.com.ueye.homemodule

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import www.mp5a5.com.kotlinmvp.util.ToastUtils
import www.mp5a5.com.ueye.R
import www.mp5a5.com.ueye.base.mvp.BaseMvpFragment
import www.mp5a5.com.ueye.base.view.act.BaseActivity
import www.mp5a5.com.ueye.net.entity.HomeBean
import www.mp5a5.com.ueye.util.DateUtil
import java.util.regex.Pattern

/**
 * @describe
 * @author ：king9999 on 2018/6/21 18：41
 * @email：wwb199055@enn.cn
 */
class HomeFragment : BaseMvpFragment<HomeFragPresenter>(), HomeFragContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    
    private lateinit var mContent: String
    private var mAdapter: HomeAdapter? = null
    var data: String? = null
    var loading = false
    
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
        return R.layout.fragment_home
    }
    
    
    override fun setTitle(): String {
        return DateUtil.getToday()
    }
    
    
    override fun initView() {
        super.initView()
        leftBtn!!.visibility = View.GONE
        midBtn!!.typeface = Typeface.createFromAsset(thisActivity!!.assets, "fonts/Lobster-1.4.otf")
        mRefreshLayoutRl.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(thisActivity)
    }
    
    
    override fun initAdapter() {
        super.initAdapter()
        mAdapter = HomeAdapter()
        mRecyclerView.adapter = mAdapter
        mRefreshLayoutRl.setOnRefreshListener(this)
        mAdapter!!.setOnLoadMoreListener(this, mRecyclerView)
        mFloatingActionButtonFab.setOnClickListener { mRecyclerView.smoothScrollToPosition(0) }
        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
    
                if (newState==RecyclerView.SCROLL_STATE_IDLE) {
                    mFloatingActionButtonFab.visibility = View.VISIBLE
                }else{
                    mFloatingActionButtonFab.visibility = View.GONE
                }
                
            }
        })
        //mRecyclerView.addOnScrollListener(myOnScrollListener())
    }
    
 
    
    override fun initNet() {
        super.initNet()
        presenter.requestNetworkFirst(thisActivity!! as BaseActivity, true)
    }
    
    override fun onLoadMoreRequested() {
        presenter.requestNetworkNext(thisActivity as BaseActivity, data!!, "2", false)
    }
    
    override fun onRefresh() {
        presenter.requestNetworkFirst(thisActivity as BaseActivity, true)
    }
    
    override fun getBasePresenter(): HomeFragPresenter {
        return HomeFragPresenter()
    }
    
    override fun showMsg(msg: String) {
        ToastUtils.show(msg)
    }
    
    
    override fun loadMoreEnd(isEnd: Boolean) {
        mAdapter!!.loadMoreEnd(isEnd)
    }
    
    override fun enableRefreshing() {
        mRefreshLayoutRl.isRefreshing = true
    }
    
    override fun unableRefreshing() {
        mRefreshLayoutRl.isRefreshing = false
    }
    
    override fun loadMoreComplete() {
        mAdapter!!.loadMoreComplete()
    }
    
    override fun loadMoreFail() {
        mAdapter!!.loadMoreFail()
    }
    
    override fun enableLoadMore() {
        mAdapter!!.setEnableLoadMore(true)
    }
    
    override fun unableLoadMore() {
        mAdapter!!.setEnableLoadMore(false)
    }
    
    override fun <E> addData(list: List<E>) {
        mAdapter!!.addData(list as List<HomeBean.IssueListBean.ItemListBean>)
    }
    
    override fun <E> setData(list: List<E>) {
        mAdapter!!.setNewData(list as List<HomeBean.IssueListBean.ItemListBean>)
    }
    
    override fun setData(bean: HomeBean) {
        val regEx = "[^0-9]"
        val p = Pattern.compile(regEx)
        val m = p.matcher(bean?.nextPageUrl)
        data = m.replaceAll("").subSequence(1, m.replaceAll("").length - 1).toString()
    }
    

    
}

