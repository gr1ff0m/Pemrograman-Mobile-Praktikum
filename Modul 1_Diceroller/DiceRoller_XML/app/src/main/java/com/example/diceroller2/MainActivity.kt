package com.example.diceroller2

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ambil referensi dari UI
        val dice1Image: ImageView = findViewById(R.id.dice1Image)
        val dice2Image: ImageView = findViewById(R.id.dice2Image)
        val btnRoll: Button = findViewById(R.id.btnRoll)
        val tvMessage: TextView = findViewById(R.id.tvMessage)

        btnRoll.setOnClickListener {
            val dice1Value = Random.nextInt(1, 7)
            val dice2Value = Random.nextInt(1, 7)

            // Update gambar dadu
            dice1Image.setImageResource(getDiceImage(dice1Value))
            dice2Image.setImageResource(getDiceImage(dice2Value))

            // Update pesan berdasarkan hasil
            tvMessage.text = if (dice1Value == dice2Value) {
                "🥳 Selamat, anda dapat dadu double! 🥳"
            } else {
                "🥺 Anda belum beruntung!"
            }
        }
    }

    private fun getDiceImage(value: Int): Int {
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
}
