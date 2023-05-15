package realcool.autoop.utils

import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.widget.ImageView
import com.lzf.easyfloat.EasyFloat
import com.lzf.easyfloat.enums.ShowPattern
import com.lzf.easyfloat.enums.SidePattern
import realcool.autoop.MainActivity
import realcool.autoop.R

const val cFloatTag = "cFloat"
fun cFloat(ctx: Context): EasyFloat.Builder {
    return EasyFloat.with(ctx).setTag(cFloatTag).setGravity(Gravity.END, 0, 500)
        .setSidePattern(SidePattern.RESULT_SIDE)
        .setShowPattern(ShowPattern.ALL_TIME)
        .setLayout(R.layout.float_service)
        .registerCallback {
            createResult { _, _, view ->
                view?.findViewById<ImageView>(R.id.floatImage)?.setOnClickListener {
                    ctx.startActivity(Intent(ctx, MainActivity::class.java))
                    EasyFloat.dismiss(cFloatTag)
                }
            }
        }
}