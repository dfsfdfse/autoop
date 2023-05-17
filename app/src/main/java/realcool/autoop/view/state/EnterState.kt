package realcool.autoop.view.state

import android.content.Context
import android.graphics.Bitmap

data class EnterState(
    /*var floatBtnText: String = "悬浮窗",*/
    var originBtnText: String = "原图",
    var grayBtnText: String = "灰度图",
    var binaryBtnText: String = "二值图",
    var ocrBtnText: String = "ocr",
    var ocrHttpBtnText: String = "ocrHttp",
    var countersBtnText: String = "轮廓",
    var imgPath: String = "shijie.jpg",
    var countersBitmap: Bitmap? = null,
    var originBitmap: Bitmap? = null,
    var binaryBitmap: Bitmap? = null,
    var grayBitmap: Bitmap? = null,
    var ocrBitmap: Bitmap? = null,
    var bitmap: Bitmap? = null
) : State
