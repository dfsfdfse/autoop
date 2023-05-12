package realcool.autoop.component

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.view.Gravity
import android.view.MotionEvent
import com.lzf.easyfloat.EasyFloat
import com.lzf.easyfloat.enums.ShowPattern
import com.lzf.easyfloat.enums.SidePattern
import realcool.autoop.R
import realcool.autoop.utils.e

class FloatService : Service() {
    override fun onBind(intent: Intent?) = null

    @SuppressLint("InflateParams")
    override fun onCreate() {
        super.onCreate()
        e("float service 创建了")
        EasyFloat.with(this).setGravity(Gravity.END, 0, 10)
            .setSidePattern(SidePattern.RESULT_SIDE).setShowPattern(ShowPattern.ALL_TIME)
            .setLayout(R.layout.float_service)
            .registerCallback {
                touchEvent { _, e ->
                    if (e.action == MotionEvent.ACTION_UP) {
                        startService(Intent(this@FloatService, MenuService::class.java))
                    }
                }
            }
            .show()
    }
}