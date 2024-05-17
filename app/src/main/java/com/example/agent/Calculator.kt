package com.example.agent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import com.example.agent.databinding.ActivityCalculatorBinding
import com.example.agent.databinding.ActivityMainBinding
import kotlin.math.pow
import kotlin.math.roundToInt

class Calculator : AppCompatActivity() {
    lateinit var bc:ActivityCalculatorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bc = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(bc.root)

        bc.ivMain.setOnClickListener {
            finish()
        }

        var currentMax1 = 12000000
        var currentStep1 = 50000
        var currentProgress1 = 0
        bc.seekBar1.max = (currentMax1/currentStep1)
        bc.seekBar1.setOnSeekBarChangeListener(object: OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar1: SeekBar?, progress: Int, fromUser: Boolean) {
                currentProgress1 = progress * currentStep1
                bc.tvNum.text = "$currentProgress1"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        var currentMax2 = 12000000
        var currentStep2 = 50000
        var currentProgress2 = 0
        bc.seekBar2.max = (currentMax2/currentStep2)
        bc.seekBar2.setOnSeekBarChangeListener(object: OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar2: SeekBar?, progress: Int, fromUser: Boolean) {
                currentProgress2 = progress * currentStep2
                bc.tvNum1.text = "$currentProgress2"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
        //var currentMax3 = 30
        // var currentStep3 = 60
        var currentProgress3 = 0f
        bc.seekBar3.max = 250
        bc.seekBar3.setOnSeekBarChangeListener(object: OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar3: SeekBar?, progress: Int, fromUser: Boolean) {
                currentProgress3 = (progress*0.1f)
                bc.tvNum2.text = "$currentProgress3 %"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
        var years = 0
        bc.seekBar4.setOnSeekBarChangeListener(object: OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar4: SeekBar?, progress: Int, fromUser: Boolean) {
                years = progress
                bc.tvNum3.text = "$years"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
        bc.btResult.setOnClickListener {
            val monthRate = currentProgress3 /12/100 // Ежемесячная ставка

            val base = 1 + monthRate //рассчет общей ставки
            val exponent = years * 12
            val result = base.toDouble().pow(exponent).toFloat()
            val totalRate = result // Общаяя ставка

            val sumkredit = currentProgress1-currentProgress2
            val monthPay = sumkredit * monthRate*totalRate/(totalRate-1) // Ежемесячный платеж
            val pereplata = monthPay*exponent-sumkredit
            bc.tvMonthPay.text = "${monthPay.toString()} ₽"
            bc.tvKredit.text = "${sumkredit.toString()} ₽"
            bc.tvPereplata.text = "${pereplata.toString()} ₽"
            Log.d("MyLog", "Ежемесячная ставка: $monthRate, base: $base, exponent; " +
                    "$exponent, result: $result, Общая ставка; $totalRate, Ежемесячный платеж: $monthPay, Pereplata: $pereplata")
        }

    }
}