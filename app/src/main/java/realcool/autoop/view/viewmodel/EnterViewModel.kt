package realcool.autoop.view.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import com.benjaminwan.ocrlibrary.OcrEngine
import kotlinx.coroutines.flow.MutableStateFlow
import org.opencv.android.Utils
import org.opencv.core.Mat
import org.opencv.core.MatOfPoint
import org.opencv.core.Point
import org.opencv.core.Scalar
import org.opencv.core.Size
import org.opencv.imgproc.Imgproc
import realcool.autoop.utils.getBitmapByAssets
import realcool.autoop.utils.loge
import realcool.autoop.utils.ocr
import realcool.autoop.view.state.EnterState

class EnterViewModel : BaseViewModel<EnterState>() {
    override val muteState: MutableStateFlow<EnterState> = MutableStateFlow(EnterState())

    @SuppressLint("StaticFieldLeak")
    lateinit var ctx: Context

    lateinit var ocrEngine: OcrEngine

    fun showOrigin() {
        setState {
            originBitmap = originBitmap ?: getBitmapByAssets(ctx.assets, imgPath)
            bitmap = originBitmap
        }
    }

    fun showGray() {
        setState {
            originBitmap = originBitmap ?: getBitmapByAssets(ctx.assets, imgPath)
            grayBitmap = grayBitmap ?: getGrayBitmap(originBitmap!!)
            bitmap = grayBitmap
        }
    }

    fun showBinary() {
        setState {
            originBitmap = originBitmap ?: getBitmapByAssets(ctx.assets, imgPath)
            binaryBitmap = binaryBitmap ?: getBinaryBitmap(originBitmap!!)
            bitmap = binaryBitmap
        }
    }

    fun showCounters() {
        setState {
            countersBitmap = getCounters(bitmap)
            bitmap = countersBitmap
        }
    }

    fun ocrDetect() {
        setState {
            ocrBitmap = ocrBitmap(bitmap)
            bitmap = ocrBitmap
        }
    }

    fun httpOcr(){
        ocr(getBitmapByAssets(ctx.assets, "weizhi.png"))
    }

    private fun ocrBitmap(bitmap: Bitmap?): Bitmap? {
        if (bitmap != null) {
            val out = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
            val max = maxOf(bitmap.width, bitmap.height)
            val result = ocrEngine.detect(bitmap, out, max)
            loge(result.toString())
            return out
        }
        return null
    }

    private fun getCounters(bitmap: Bitmap?): Bitmap? {
        if (bitmap != null) {
            val mat = Mat()
            Utils.bitmapToMat(bitmap, mat)
            val gray = Mat()
            Imgproc.cvtColor(mat, gray, Imgproc.COLOR_RGB2GRAY)
            val binary = Mat()
            Imgproc.threshold(gray, binary, 200.0, 255.0, Imgproc.THRESH_BINARY_INV)
            val h = Mat()
            val points = ArrayList<MatOfPoint>()
            val str = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, Size(7.0, 7.0))
            Imgproc.erode(binary, binary, str, Point(-1.0, -1.0), 3)
            //Imgproc.dilate(mat, mat, str)
            Imgproc.findContours(binary, points, h, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE)
            for (i in 0 until points.size){
                val rect = Imgproc.boundingRect(points[i])
                val rate = minOf(rect.width, rect.height)/ maxOf(rect.width, rect.height)
                Imgproc.drawContours(mat, points, i, Scalar(255.0, 0.0, 0.0), 2)
            }
            val ret = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
            Utils.matToBitmap(mat, ret)
            h.release()
            mat.release()
            return ret
        }
        return null
    }

    private fun getGrayBitmap(origin: Bitmap): Bitmap {
        val o = Mat()
        Utils.bitmapToMat(origin, o)
        Imgproc.cvtColor(o, o, Imgproc.COLOR_BGR2GRAY)
        val clone = Bitmap.createBitmap(origin.width, origin.height, Bitmap.Config.ARGB_8888)
        Utils.matToBitmap(o, clone)
        o.release()
        return clone
    }

    private fun getBinaryBitmap(origin: Bitmap): Bitmap {
        val o = Mat()
        Utils.bitmapToMat(origin, o)
        Imgproc.threshold(o, o, 200.0, 255.0, Imgproc.THRESH_BINARY)
        val clone = Bitmap.createBitmap(origin.width, origin.height, Bitmap.Config.ARGB_8888)
        Utils.matToBitmap(o, clone)
        o.release()
        return clone
    }
}



