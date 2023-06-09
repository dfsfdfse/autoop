package realcool.autoop.view.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import realcool.autoop.view.viewmodel.EnterViewModel

@Composable
fun EnterScreen(viewModel: EnterViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyRow{
            item {
                /*Button(onClick = viewModel::ocrDetect) {
                    Text(text = state.ocrBtnText)
                }
                Button(onClick = viewModel::showOrigin) {
                    Text(text = state.originBtnText)
                }
                Button(onClick = viewModel::showGray) {
                    Text(text = state.grayBtnText)
                }*/
                Button(onClick = viewModel::showBinary) {
                    Text(text = state.binaryBtnText)
                }
                Button(onClick = viewModel::showCounters) {
                    Text(text = state.countersBtnText)
                }
                Button(onClick = viewModel::httpOcr) {
                    Text(text = state.ocrHttpBtnText)
                }
            }
        }
        state.bitmap?.asImageBitmap()?.let { Image(bitmap = it, contentDescription = null) }
    }
}