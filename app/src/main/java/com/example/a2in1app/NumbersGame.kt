package com.example.a2in1app

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class NumbersGame : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    lateinit var guesses: ArrayList<String>
    lateinit var ednum: EditText
    lateinit var btn: Button
    var trying = 3
    var randNum = Random.nextInt(10)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_numbers_game)
        recyclerView = findViewById(R.id.rvnum)
        ednum = findViewById(R.id.ednum)
        btn = findViewById(R.id.btn)
        //lLayout = findViewById(R.id.LMain)

        guesses = ArrayList()


        recyclerView.adapter = adapter(guesses)
        recyclerView.layoutManager = LinearLayoutManager(this)

        btn.setOnClickListener {
            val nums = ednum.text.toString().toInt()
            if (trying != 0 ) {
                if (nums == randNum) {
                    guesses.add("You guessed $nums")
                    guesses.add("you got it !")
                    customAlert()
                } else {
                    trying--
                    guesses.add("You guessed $nums")
                    guesses.add("wrong guess, you have $trying guesses left.")
                }
            } else{
                guesses.add("Oh noo the game is over, the number was $randNum")
                customAlert()

            }

            recyclerView.adapter?.notifyDataSetChanged()
            ednum.text.clear()
            ednum.clearFocus()
        }
    }
    private fun customAlert() {

        val build = AlertDialog.Builder(this)

        // set message of alert dialog
        build.setMessage("Do you want another game?")
            .setCancelable(false)
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->
                this.recreate()
            })
            // negative button text and action
            .setNegativeButton("No", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })

        val GameOver = build.create()
        GameOver.setTitle("New Game")
        GameOver.show()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.Home -> {
                val intent = Intent(this, MainActivity::class.java)
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