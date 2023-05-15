package realcool.autoop.view.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import realcool.autoop.view.viewmodel.EnterViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterScreen(viewModel: EnterViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyRow{
            item {
                Button(onClick = viewModel::changeName) {
                    Text(text = state.floatBtnText)
                }
                Button(onClick = viewModel::changeOrigin) {
                    Text(text = state.originBtnText)
                }
                Button(onClick = viewModel::changeName) {
                    Text(text = state.binaryBtnText)
                }
            }
        }
        OutlinedTextField(value = state.inputText, onValueChange = viewModel::inputText)
    }
}