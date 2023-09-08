package com.example.uppgift2v1

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        sharedPreferences = getSharedPreferences("uppgift2Preferences", MODE_PRIVATE)

        val spinner = findViewById<Spinner>(R.id.spinner)
        val adapter = ArrayAdapter.createFromResource(this, R.array.pages, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.setSelection(0, false)
        spinner.onItemSelectedListener = this

        val btnLogout = findViewById<Button>(R.id.logoutButton)
        btnLogout.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.putBoolean("stayLoggedCheck", false)
            editor.apply()
            val a1 = Intent(this@MainActivity2, MainActivity::class.java)
            startActivity(a1)
            finish()
        }
    }

    override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
        val intent: Intent
        when (i) {
            1 -> {
                intent = Intent(this@MainActivity2, MainActivity2::class.java)
                startActivity(intent)
                finish()
            }

            2 -> {
                intent = Intent(this@MainActivity2, MainActivity3::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onNothingSelected(adapterView: AdapterView<*>?) {

    }

    override fun onPause() {
        super.onPause()

        val editPhoneNumber = findViewById<EditText>(R.id.editPhoneNumber)
        val inputPhoneNumber = editPhoneNumber.text.toString()
        val editEmail = findViewById<EditText>(R.id.editEmail)
        val inputEmail = editEmail.text.toString()
        val editor = sharedPreferences!!.edit()
        editor.putString("phone", inputPhoneNumber)
        editor.putString("email", inputEmail)
        editor.apply()
    }

    override fun onResume() {
        super.onResume()

        val spinner = findViewById<Spinner>(R.id.spinner)
        spinner.setSelection(0, false)

        val savedPhoneNumber = sharedPreferences!!.getString("phone", "")
        val savedEmail = sharedPreferences!!.getString("email", "")
        val editPhoneNumber = findViewById<EditText>(R.id.editPhoneNumber)
        val editEmail = findViewById<EditText>(R.id.editEmail)
        editPhoneNumber.setText(savedPhoneNumber)
        editEmail.setText(savedEmail)
    }
}