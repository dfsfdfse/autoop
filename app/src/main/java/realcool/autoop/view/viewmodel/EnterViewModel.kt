package realcool.autoop.view.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.opencv.android.Utils
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc
import realcool.autoop.utils.getBitmapByAssets
import realcool.autoop.utils.loge
import realcool.autoop.view.state.EnterState
import kotlin.random.Random

class EnterViewModel : BaseViewModel<EnterState>() {
    override val muteState: MutableStateFlow<EnterState> = MutableStateFlow(EnterState())

    @SuppressLint("StaticFieldLeak")
    lateinit var ctx: Context
    fun showFloat() {

    }

    fun showOrigin() {
        setState {
            originBitmap = originBitmap ?: getBitmapByAssets(ctx.assets, "sky.jpg")
            bitmap = originBitmap
        }
    }

    fun showBinary() {
        setState {
            originBitmap = originBitmap ?: getBitmapByAssets(ctx.assets, "sky.jpg")
            binaryBitmap = binaryBitmap ?: getBinaryBitmap(originBitmap!!)
            bitmap = binaryBitmap
        }
    }

    private fun getBinaryBitmap(origin: Bitmap): Bitmap{
        val o = Mat()
        Utils.bitmapToMat(origin, o)
        Imgproc.cvtColor(o, o, Imgproc.COLOR_BGR2GRAY)
        val clone = Bitmap.createBitmap(origin.width, origin.height, Bitmap.Config.ARGB_8888)
        Utils.matToBitmap(o, clone)
        return clone
    }
}



