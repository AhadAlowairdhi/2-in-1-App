package com.example.a2in1app

import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var btnNumber: Button
    lateinit var btnGuess: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnNumber = findViewById(R.id.btnNum)
        btnGuess = findViewById(R.id.btnGuess)

        btnNumber.setOnClickListener {
            val intent = Intent(this, NumbersGame::class.java)
            startActivity(intent)
        }
        btnGuess.setOnClickListener {
            val intent = Intent(this, GuessPhraseGame::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.Numbers -> {
                val intent = Intent(this, NumbersGame::class.java)
                startActivity(intent)
                return true
            }
            R.id.Guess -> {
                val intent = Intent(this, GuessPhraseGame::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}