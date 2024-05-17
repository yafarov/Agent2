package com.example.agent

import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.agent.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var bc:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bc = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bc.root)
        bc.bNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.main_menu -> {
                    Toast.makeText(this, "Main Menu", Toast.LENGTH_SHORT).show()
                }
                R.id.clients -> {
                    Toast.makeText(this, "clients", Toast.LENGTH_SHORT).show()
                }
                R.id.top100 -> {
                    Toast.makeText(this, "top", Toast.LENGTH_SHORT).show()
                }
                R.id.profile_menu -> {
                    Toast.makeText(this, "profile menu", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
        bc.btCalc.setOnClickListener {
            val intent = Intent(this, Calculator::class.java)
            startActivity(intent)
        }


        }
    }
