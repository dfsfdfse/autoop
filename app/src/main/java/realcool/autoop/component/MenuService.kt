package realcool.autoop.component

import android.app.Service
import android.content.Intent

class MenuService : Service() {
    override fun onBind(intent: Intent?) = null

    override fun onCreate() {
        super.onCreate()
    }
}