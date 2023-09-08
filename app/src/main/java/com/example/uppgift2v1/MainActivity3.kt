package com.example.uppgift2v1

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity3 : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        sharedPreferences = getSharedPreferences("uppgift2Preferences", MODE_PRIVATE)

        val spinner = findViewById<Spinner>(R.id.spinner)
        val adapter = ArrayAdapter.createFromResource(this, R.array.pages, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.setSelection(0, false)
        spinner.onItemSelectedListener = this

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val selectedRadioBtn = sharedPreferences.getInt("selectedOption", radioGroup.checkedRadioButtonId)
        radioGroup.check(selectedRadioBtn);

        val btnLogout = findViewById<Button>(R.id.logoutButton)
        btnLogout.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.putBoolean("stayLoggedCheck", false)
            editor.apply()
            val a1 = Intent(this@MainActivity3, MainActivity::class.java)
            startActivity(a1)
            finish()
        }
    }

    override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
        val intent: Intent
        when (i) {
            1 -> {
                intent = Intent(this@MainActivity3, MainActivity2::class.java)
                startActivity(intent)
            }

            2 -> {
                intent = Intent(this@MainActivity3, MainActivity3::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onNothingSelected(adapterView: AdapterView<*>?) {

    }

    override fun onPause() {
        super.onPause()

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val cBoxAge = findViewById<CheckBox>(R.id.checkBox18Plus)
        val checkboxState = cBoxAge.isChecked
        val editor = sharedPreferences!!.edit()
        editor.putBoolean("ageChecker", checkboxState)
        editor.putInt("selectedOption", radioGroup.checkedRadioButtonId)
        editor.apply()
    }

    override fun onResume() {
        super.onResume()

        val spinner = findViewById<Spinner>(R.id.spinner)
        spinner.setSelection(0, false)

        val savedAgeChecker = sharedPreferences!!.getBoolean("ageChecker", false)
        val cBoxAge = findViewById<CheckBox>(R.id.checkBox18Plus)
        cBoxAge.isChecked = savedAgeChecker
    }
}