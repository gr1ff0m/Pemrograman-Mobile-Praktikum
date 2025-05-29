package com.example.scrollablelist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.scrollablelist.navigation.NavGraph
import com.example.scrollablelist.ui.theme.ScrollableListTheme

class MainActivity : ComponentActivity() {

    private val apiKey = "9fad0dc9a0338ecf00596875e4cf5645" // Masukkan API Key di sini

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScrollableListTheme {
                NavGraph()
            }
        }
    }
}
