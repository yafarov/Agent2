package com.example.agent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.agent.databinding.ActivityAuthBinding
import com.example.agent.databinding.ActivityRegistrationBinding

class AuthActivity : AppCompatActivity() {
    lateinit var bc: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bc = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(bc.root)

        bc.linkToReg.setOnClickListener {
            val intent = Intent(this, Registration::class.java)
            startActivity(intent)
        }
        bc.button.setOnClickListener {
             val login = bc.edLogin.text.toString().trim()
            val pass = bc.edPass.text.toString().trim()

            if (login == "" || pass == "")
                Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_SHORT).show()
            else {
                val db = DbHelper(this, null)
                val isAuth = db.getUser(login, pass)
                Log.d("MyLog", "$isAuth")
                if (isAuth) {
                    Toast.makeText(this, "Пользователь $login авторизован", Toast.LENGTH_SHORT)
                        .show()
                    bc.edLogin.text.clear()
                    bc.edPass.text.clear()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this, "Пользователь $login не авторизован", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }


        }
    }
