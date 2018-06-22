package www.mp5a5.com.ueye.base.mvp

import www.mp5a5.com.ueye.base.view.frag.BaseFragment


/**
 * @describe
 * @author ：king9999 on 2018/6/21 11：31
 * @email：wwb199055@enn.cn
 */
abstract class BaseMvpFragment<P : BasePresenter<*>> : BaseFragment(), BaseView {
    
    
    protected lateinit var presenter: P
    
    
    override fun initView() {
        super.initView()
        presenter = getBasePresenter();
        presenter.bind(this)
    }
    
    override fun onResume() {
        super.onResume()
        presenter.bind(this)
    }
    
    override fun onDestroy() {
        super.onDestroy()
        presenter.unBind()
    }
    
    
    abstract fun getBasePresenter(): P
    
    
}

