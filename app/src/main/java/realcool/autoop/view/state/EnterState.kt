package realcool.autoop.view.state

import android.content.Context
import android.graphics.Bitmap

data class EnterState(
    var floatBtnText: String = "悬浮窗",
    var originBtnText: String = "原图",
    var binaryBtnText: String = "灰度图",
    var originBitmap: Bitmap? = null,
    var binaryBitmap: Bitmap? = null,
    var bitmap: Bitmap? = null
) : State
