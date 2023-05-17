package realcool.autoop.utils

import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory

fun getBitmapByAssets(asset: AssetManager, filename: String): Bitmap {
    val open = asset.open(filename)
    val bitmap = BitmapFactory.decodeStream(open)
    loge("width:${bitmap.width}, height: ${bitmap.height}")
    return bitmap
}