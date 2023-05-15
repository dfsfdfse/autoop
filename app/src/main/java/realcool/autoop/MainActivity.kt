package realcool.autoop

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.ui.platform.ComposeView
import org.opencv.android.OpenCVLoader
import realcool.autoop.utils.cFloat
import realcool.autoop.utils.loge
import realcool.autoop.view.ui.EnterScreen
import realcool.autoop.view.viewmodel.EnterViewModel

class MainActivity : ComponentActivity() {
    private val cw = cFloat(this)

    private val enterViewModel by viewModels<EnterViewModel>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterViewModel.ctx = this
        if (OpenCVLoader.initDebug()) {
            loge("opencv加载成功了!")
        }
        setContentView(R.layout.game_main)
        val main = findViewById<ComposeView>(R.id.mainView)
        main.setContent {
            EnterScreen(viewModel = enterViewModel)
        }

    }
}
