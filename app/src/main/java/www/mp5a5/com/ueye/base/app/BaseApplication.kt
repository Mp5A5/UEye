package www.mp5a5.com.ueye.base.app

import www.mp5a5.com.ueye.util.ConstantUtil

/**
 * @describe
 * @author ：king9999 on 2018/6/21 13：57
 * @email：wwb199055@enn.cn
 */
class BaseApplication : BaseCommonApplication() {
    
    override fun setBaseUrl(): String {
        return ConstantUtil.BASE_ULR
    }
    
    
}