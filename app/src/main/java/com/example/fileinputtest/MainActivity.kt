package com.example.fileinputtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.fileinputtest.ui.theme.FileInputTestTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Call initReverseFileContent Method
        initReverseFileContent()

        setContent {
            FileInputTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }

    private fun initReverseFileContent() {
        val fileReader = FileReverser();

        lifecycleScope.launch {
            fileReader.reverseStreams(
                context = baseContext,
                inputFileName = "input.txt",
                outputFileName = "output.txt"
            )
        }
    }


    companion object {
        private const val STORAGE_PERMISSION_CODE = 23
        private const val TAG = "TAG"

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FileInputTestTheme {
        Greeting("Android")
    }
}