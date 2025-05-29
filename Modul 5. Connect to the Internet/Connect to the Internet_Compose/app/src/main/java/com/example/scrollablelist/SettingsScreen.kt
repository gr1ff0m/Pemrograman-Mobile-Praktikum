package com.example.scrollablelist.screens

import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.scrollablelist.SharedPreferenceViewModel

@Composable
fun SettingsScreen(viewModel: SharedPreferenceViewModel) {
    val isDarkMode = viewModel.isDarkMode.collectAsState()

    Row(modifier = Modifier.padding(16.dp)) {
        Text(text = "Dark Mode")
        Switch(
            checked = isDarkMode.value,
            onCheckedChange = { viewModel.toggleDarkMode() },
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}
