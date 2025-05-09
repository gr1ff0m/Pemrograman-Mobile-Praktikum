package com.example.kalkulator2

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    private lateinit var costInput: EditText
    private lateinit var tipSpinner: Spinner
    private lateinit var roundUpSwitch: Switch
    private lateinit var tipResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        costInput = findViewById(R.id.costInput)
        tipSpinner = findViewById(R.id.tipSpinner)
        roundUpSwitch = findViewById(R.id.roundUpSwitch)
        tipResult = findViewById(R.id.tipResult)

        val tipOptions = listOf(10, 15, 20, 25)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tipOptions)
        tipSpinner.adapter = adapter

        val updateTip = {
            val cost = costInput.text.toString().toDoubleOrNull() ?: 0.0
            val tipPercent = tipSpinner.selectedItem as Int
            val roundUp = roundUpSwitch.isChecked

            var tip = cost * tipPercent / 100
            if (roundUp) tip = ceil(tip)

            tipResult.text = "Jumlah Tip: Rp%.2f".format(tip)
        }

        costInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = updateTip()
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        roundUpSwitch.setOnCheckedChangeListener { _, _ -> updateTip() }

        tipSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) = updateTip()

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
}
