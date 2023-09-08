package com.example.uppgift2v1

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

private lateinit var sharedPreferences: SharedPreferences
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("uppgift2Preferences", MODE_PRIVATE)

        val cBoxStayLoggedIn = findViewById<CheckBox>(R.id.checkBoxStayLgdIn)
        cBoxStayLoggedIn.isChecked = sharedPreferences.getBoolean("stayLoggedCheck", false)
        val isBoxChecked = sharedPreferences.getBoolean("stayLoggedCheck", false)
        if (isBoxChecked) {
            val a2 = Intent(this@MainActivity, MainActivity2::class.java)
            startActivity(a2)
            finish()
        }


        val btnLogin = findViewById<Button>(R.id.loginButton)
        val inputUsername = findViewById<EditText>(R.id.inputUsername)
        val inputPassword = findViewById<EditText>(R.id.inputPassword)


        btnLogin.setOnClickListener {
            if (inputUsername.text.toString() == "Bob" && inputPassword.text.toString() == "123") {
                val a2 = Intent(this@MainActivity, MainActivity2::class.java)
                startActivity(a2)
                finish()
            } else {
                Toast.makeText(this@MainActivity, "Wrong username or password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        val cBoxStayLoggedIn = findViewById<CheckBox>(R.id.checkBoxStayLgdIn)
        val isBoxChecked = cBoxStayLoggedIn.isChecked
        val editor = sharedPreferences.edit()
        editor.putBoolean("stayLoggedCheck", isBoxChecked)
        editor.apply()
    }

    override fun onBackPressed() {
        val startMain = Intent(Intent.ACTION_MAIN)
        startMain.addCategory(Intent.CATEGORY_HOME)
        startMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(startMain)
    }

}