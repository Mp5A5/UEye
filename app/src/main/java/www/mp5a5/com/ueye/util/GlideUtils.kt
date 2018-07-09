package www.mp5a5.com.ueye.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import io.reactivex.annotations.Nullable
import www.mp5a5.com.ueye.R

/**
 * Created by lvruheng on 2017/7/6.
 */
class GlideUtils {
    
    companion object {
        fun display(context: Context, imageView: ImageView?, url: String) {
            if (imageView == null) {
                throw IllegalArgumentException("argument error")
            }
            
            val requestOptions = RequestOptions()
            requestOptions
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .placeholder(R.mipmap.ic_image_loading)
                    .error(R.mipmap.ic_empty_picture)
            
            Glide.with(context)
                    .load(url)
                    .apply(requestOptions)
                    .into(imageView)
        }
        
        fun displayHigh(context: Context, @Nullable imageView: ImageView, url: String) {
           
            
            val requestOptions = RequestOptions()
            requestOptions
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .format(DecodeFormat.PREFER_ARGB_8888)
                    .centerCrop()
                    .placeholder(R.mipmap.ic_image_loading)
                    .error(R.mipmap.ic_empty_picture)
            Glide.with(context)
                    .asBitmap()
                    .load(url)
                    .apply(requestOptions)
                    .into(imageView)
        }
    }
    
}