package www.mp5a5.com.ueye.module.discovery.frag

import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import kotlinx.android.synthetic.main.fragment_discovery.*
import www.mp5a5.com.kotlinmvp.util.ToastUtils
import www.mp5a5.com.ueye.R
import www.mp5a5.com.ueye.base.mvp.BaseMvpFragment
import www.mp5a5.com.ueye.base.view.act.BaseActivity
import www.mp5a5.com.ueye.customview.RecyclerViewItemDecoration
import www.mp5a5.com.ueye.module.discovery.adapter.DiscoveryAdapter
import www.mp5a5.com.ueye.module.discovery.mvp.contract.DiscoveryContract
import www.mp5a5.com.ueye.module.discovery.mvp.presenter.DiscoveryPresenter
import www.mp5a5.com.ueye.net.entity.DiscoveryEntity
import www.mp5a5.com.ueye.util.DateUtil

/**
 * @describe
 * @author ：king9999 on 2018/6/21 18：41
 * @email：wwb199055@enn.cn
 */
class DiscoveryFragment : BaseMvpFragment<DiscoveryPresenter>(), DiscoveryContract.View, BaseQuickAdapter.OnItemClickListener {
    
    private lateinit var mContent: String
    private lateinit var mDiscoveryAdapter: DiscoveryAdapter
    
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
        val map = mutableMapOf<RecyclerViewItemDecoration.SpaceType, Int>()
        map[RecyclerViewItemDecoration.SpaceType.TOP] = 10
        map[RecyclerViewItemDecoration.SpaceType.BOTTOM] = 10
        map[RecyclerViewItemDecoration.SpaceType.LEFT] = 10
        map[RecyclerViewItemDecoration.SpaceType.RIGHT] = 10
        rvDiscoveryRecyclerView.addItemDecoration(RecyclerViewItemDecoration(map))
    }
    
    override fun initAdapter() {
        super.initAdapter()
        mDiscoveryAdapter = DiscoveryAdapter()
        rvDiscoveryRecyclerView.adapter = mDiscoveryAdapter
    }
    
    override fun initNet() {
        super.initNet()
        presenter.requestNetWork((thisActivity as BaseActivity?)!!, true)
    }
    
    override fun initListener() {
        super.initListener()
        mDiscoveryAdapter.onItemClickListener = this
    }
    
    override fun showMsg(msg: String) {
        ToastUtils.show(msg)
    }
    
    override fun <E> setNewData(list: List<E>) {
        mDiscoveryAdapter.setNewData(list as MutableList<DiscoveryEntity>)
    }
    
    override fun setEmptyView() {
        mDiscoveryAdapter.setNewData(null)
    }
    
    
    override fun getBasePresenter(): DiscoveryPresenter {
        return DiscoveryPresenter()
    }
    
    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        val bean = adapter!!.data[position] as DiscoveryEntity
        val bundle = Bundle()
        bundle.putString("discovery_name", bean.name!!)
        //gotoActivity<>(bundle, false)
    }
}