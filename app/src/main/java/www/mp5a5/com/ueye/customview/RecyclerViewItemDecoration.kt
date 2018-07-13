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
    
    private var mSpaceValueMap: HashMap<SpaceType, Int>? = null
    
     constructor(spaceValueMap: HashMap<SpaceType, Int>) : this() {
        this.mSpaceValueMap = spaceValueMap
    }
    
    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect!!.top = mSpaceValueMap!!.get(SpaceType.TOP)!!
        outRect!!.bottom = mSpaceValueMap!!.get(SpaceType.BOTTOM)!!
        outRect!!.left = mSpaceValueMap!!.get(SpaceType.LEFT)!!
        outRect!!.right = mSpaceValueMap!!.get(SpaceType.RIGHT)!!
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