package www.mp5a5.com.ueye.customview

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * @describe
 * @author ：king9999 on 2018/7/13 14：20
 * @email：wangwenbinc@enn.cn
 */
class RecyclerViewItemDecoration() : RecyclerView.ItemDecoration() {
    
    private var mSpaceValueMap: MutableMap<SpaceType, Int>? = null
    
    constructor(spaceValueMap: MutableMap<SpaceType, Int>) : this() {
        this.mSpaceValueMap = spaceValueMap
    }
    
    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        
        if (mSpaceValueMap?.get(SpaceType.TOP) != null) {
            outRect!!.top = mSpaceValueMap!![SpaceType.TOP]!!
        }
        
        if (mSpaceValueMap?.get(SpaceType.BOTTOM) != null) {
            outRect!!.bottom = mSpaceValueMap!![SpaceType.BOTTOM]!!
        }
        
        if (mSpaceValueMap?.get(SpaceType.LEFT) != null) {
            outRect!!.left = mSpaceValueMap!![SpaceType.LEFT]!!
        }
        
        if (mSpaceValueMap?.get(SpaceType.RIGHT) != null) {
            outRect!!.right = mSpaceValueMap!![SpaceType.RIGHT]!!
        }
    }
    
    enum class SpaceType {
        /**
         * 顶部
         */
        TOP,
        /**
         * 底部
         */
        BOTTOM,
        /**
         * 左边
         */
        LEFT,
        /**
         * 右边
         */
        RIGHT
    }
}