package com.example.agent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.agent.databinding.ActivityMainBinding
import com.example.agent.databinding.ActivityRegistrationBinding

class Registration : AppCompatActivity() {
    lateinit var bc: ActivityRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bc = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(bc.root)
        bc.linkToAuth.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }

        bc.button.setOnClickListener {
            val login = bc.edLogin.text.toString().trim()
            val email = bc.edEmail.text.toString().trim()
            val pass = bc.edPass.text.toString().trim()
            if (login == "" || email == "" || pass == "")
                Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_SHORT).show()
            else {
                val user = User(login, email, pass)
                val db = DbHelper(this, null)
                db.addUser(user)
                Toast.makeText(this, "Пользователь $login добавлен", Toast.LENGTH_SHORT).show()
                bc.edLogin.text.clear()
                bc.edEmail.text.clear()
                bc.edPass.text.clear()
            }
        }
    }
}