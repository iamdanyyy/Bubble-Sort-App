package com.example.bubblesorter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bubblesorter.ui.theme.BubbleSorterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BubbleSorterTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    BubbleSorter()
                }
            }
        }
    }
}

@Composable
fun BubbleSorter() {

    var input by remember { mutableStateOf("") }
    var answer by remember { mutableStateOf("") }

    fun sortingLogic() {
        // Split the input by spaces and convert to an integer array
        var numbers = input?.split(" ")?.map { it.toInt() }?.toTypedArray()
        var temp: Int

        if (numbers != null) {
            for (i in 0..numbers.size - 2) {
                for (j in 0..numbers.size - i - 2) {
                    if (numbers[j] > numbers[j + 1]) {
                        temp = numbers[j]
                        numbers[j] = numbers[j + 1]
                        numbers[j + 1] = temp
                    }
                }
            }
        }
        answer = numbers!!.joinToString(", ")
    }

    val customTextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 30.sp,
    )
    
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = input,
            onValueChange = {
                input = it},
            label = {Text(  "Enter array")}
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { sortingLogic() }) {
            Text(text = "Sort")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = answer, style = customTextStyle)

        Spacer(modifier = Modifier.height(300.dp))
        Text(text = "Copyright @Amdany")
    }
}