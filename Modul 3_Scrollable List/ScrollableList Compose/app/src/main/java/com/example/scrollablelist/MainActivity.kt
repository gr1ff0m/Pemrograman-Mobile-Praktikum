package com.example.scrollablelist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.scrollablelist.navigation.NavGraph
import com.example.scrollablelist.ui.theme.ScrollableListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScrollableListTheme {
                NavGraph()
            }
        }
    }
}
