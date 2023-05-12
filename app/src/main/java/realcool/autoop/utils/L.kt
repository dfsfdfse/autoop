package realcool.autoop.utils

import android.util.Log
var logEnable = true
var tag = "rc--->"
fun e(msg: String) {
    if (logEnable) Log.e(tag, msg)
}