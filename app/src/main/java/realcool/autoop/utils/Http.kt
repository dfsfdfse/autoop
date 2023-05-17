package realcool.autoop.utils

import android.graphics.Bitmap
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream

@OptIn(DelicateCoroutinesApi::class)
fun ocr(bitmap: Bitmap) {
    val client = OkHttpClient()
    GlobalScope.launch(Dispatchers.IO) {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val rBody = stream.toByteArray().toRequestBody("image/png".toMediaType())
        val requestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart("file", "weizhi.png", rBody).build()
        val req = Request.Builder().url("http://192.168.0.111:8090/ocr")
            .post(requestBody)
            .build()
        val res = client.newCall(req).execute()
        val str = res.body?.string()
        loge(str.toString())
    }
}