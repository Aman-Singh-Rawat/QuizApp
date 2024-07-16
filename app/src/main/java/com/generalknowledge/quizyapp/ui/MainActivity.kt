package com.generalknowledge.quizyapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.generalknowledge.quizyapp.R
import com.generalknowledge.quizyapp.util.Constants

class MainActivity : AppCompatActivity() {
    private val btnStart: Button
        get() = findViewById(R.id.btnStart)

    private val etName: TextView
        get() = findViewById(R.id.etName)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnStart.setOnClickListener { navigateAnotherActivity() }
    }

    private fun navigateAnotherActivity() {
        if (etName.text.toString().isBlank())
            Toast.makeText(this, "Please Enter Valid Name", Toast.LENGTH_SHORT).show()
        else {
            val intent = Intent(this, QuizQuestionActivity::class.java)
            intent.putExtra(Constants.USER_NAME, etName.text.toString())
            startActivity(intent)
        }
    }
}