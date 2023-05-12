package realcool.autoop

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import realcool.autoop.component.FloatService
import realcool.autoop.ui.theme.AutoopTheme
import realcool.autoop.utils.e

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AutoopTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting({
                        e("进来了")
                        startService(Intent(this, FloatService::class.java))
                        moveTaskToBack(true)
                    })
                }
            }
        }
    }
}

@Composable
fun Greeting(click: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextButton(onClick = click) {
            Text(text = "float")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AutoopTheme {

    }
}