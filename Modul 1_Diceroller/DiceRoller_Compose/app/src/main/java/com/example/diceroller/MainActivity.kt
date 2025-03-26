package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerApp()
        }
    }
}

@Composable
fun DiceRollerApp() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFFFFF5E1) // Warna krem
        ) {
            DiceContent()
        }
    }
}

@Composable
fun DiceContent() {
    var dice1Value by remember { mutableStateOf(1) }
    var dice2Value by remember { mutableStateOf(1) }
    var message by remember { mutableStateOf("Tekan tombol untuk mulai!") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Roll the Dice!", fontSize = 24.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            DiceImage(dice1Value)
            Spacer(modifier = Modifier.width(16.dp))
            DiceImage(dice2Value)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = message, fontSize = 18.sp, color = Color.DarkGray)

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                dice1Value = Random.nextInt(1, 7)
                dice2Value = Random.nextInt(1, 7)

                message = if (dice1Value == dice2Value) {
                    "🥳 Selamat, anda dapat dadu double! 🥳"
                } else {
                    "Anda belum beruntung! 🥺"
                }
            }
        ) {
            Text(text = "Roll", fontSize = 18.sp)
        }
    }
}

@Composable
fun DiceImage(diceValue: Int) {
    Image(
        painter = painterResource(id = getDiceImageResource(diceValue)),
        contentDescription = "Dice $diceValue",
        modifier = Modifier.size(100.dp)
    )
}

fun getDiceImageResource(value: Int): Int {
    return when (value) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        6 -> R.drawable.dice_6
        else -> R.drawable.dice_1
    }
}
