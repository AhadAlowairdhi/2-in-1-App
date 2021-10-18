package com.example.a2in1app

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_guess_phrase_game.*

class GuessPhraseGame : AppCompatActivity() {
    lateinit var title : TextView
    lateinit var highSc : TextView
    private lateinit var RVguess: RecyclerView
    lateinit var edPhrase: EditText
    lateinit var Check: Button
    lateinit var Guesses: ArrayList<String>
    private var numOfguess = 10

    private var secretPhrase = "the life"
    private var phrase = CharArray(secretPhrase.length)

    private lateinit var sharedPreferences: SharedPreferences

    private var score = 0
    private var highscore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guess_phrase_game)
        sharedPreferences = this.getSharedPreferences(getString(R.string.preference_score), Context.MODE_PRIVATE)
        highscore = sharedPreferences.getInt("HighScore", 0)// --> retrieves data from Shared Preferences

        highSc=findViewById(R.id.tvScore)
        var placeScore = "Highscore: $highscore"
        highSc.text = placeScore


        title = findViewById(R.id.tv1)
        edPhrase = findViewById(R.id.edt)
        Check = findViewById(R.id.btn)

        Guesses = ArrayList()
        RVguess = findViewById(R.id.rvMain)
        RVguess.adapter = guessAdapter(Guesses)
        RVguess.layoutManager = LinearLayoutManager(this)


        for (i in secretPhrase.indices) {
            if (secretPhrase[i] == ' ') {
                phrase[i] = ' '
            } else {
                phrase[i] = '*'
            }
        }

        // phrase = "*".repeat(secretPhrase.length).toCharArray()

        var thePhrase = "The secret phrase is: ${String(phrase)}"
        title.text = thePhrase

        Check.setOnClickListener {
            if (numOfguess != 0)
            {
                if (edPhrase.hint == "Enter A Full Phrase")
                    checkPhrase()
                else
                    checkLetter()
            }
            else
                alertbox("Lost")
        }
    }
    fun checkPhrase() {
        if (edPhrase.text.length != 1) {
            if (edt.text.toString() == secretPhrase ) {
                newScore()
                disableInputs()
                alertbox("Won")
            } else {
                Guesses.add("Wrong Guess")
                edPhrase.hint = "Enter A Letter"

            }
            edt.text.clear()
            edt.clearFocus()
            RVguess.adapter?.notifyDataSetChanged()
        } else {
            Snackbar.make(clMain, "Error TextField is too short", Snackbar.LENGTH_SHORT).show()
        }
    }

    fun checkLetter() {
        val userLetter = edPhrase.text[0]
        val count = secretPhrase.fold(0) {
                sum: Int, c: Char ->
            if (edPhrase.text.toString().contains(c))
                sum + 1
            else
                sum
        }
        if (userLetter in secretPhrase) {
            Guesses.add("Found $count $userLetter")
            for (i in secretPhrase.indices) {
                if (secretPhrase[i] == userLetter)
                    phrase[i] = userLetter
            }
            val thePhrase = "Secret Phrase is: ${String(phrase)}\n Guessed Letter: $userLetter"
            title.text = thePhrase
            //if(secretPhrase == String(phrase))
            //alertbox("You Won")

        } else {
            Guesses.add("Wrong guess, No $userLetter")
            Guesses.add("Number of guess ${--numOfguess}")
        }
        edPhrase.text.clear()
        edt.clearFocus()
        RVguess.adapter?.notifyDataSetChanged()
        RVguess.scrollToPosition(Guesses.size-1)
        edPhrase.hint = "Enter A Full Phrase"
    }
    private fun newScore() {
        score = 0 + numOfguess
        if(score >= highscore){
            highscore = score
            with(sharedPreferences.edit()) {
                putInt("HighScore", highscore)
                apply()
            }
            Snackbar.make(clMain,"Made New Highscore", Snackbar.LENGTH_SHORT).show()
        }
    }
    private fun disableInputs() {
        Check.isEnabled = false
        Check.isClickable = false
        edPhrase.isClickable = false
        edPhrase.isEnabled = false
    }

    private fun alertbox(title: String) {
        // first we create a variable to hold an AlertDialog builder
        val builder = AlertDialog.Builder(this)
        // here we set the message of our alert dialog
        builder.setMessage("You $title\n Phrase is: $secretPhrase \nNew Game?")
            // positive button text and action
            .setPositiveButton("Yes", DialogInterface.OnClickListener { _, _ ->
                this.recreate()
            })
            // negative button text and action
            .setNegativeButton("No", DialogInterface.OnClickListener { dialog, _ ->
                dialog.cancel()
            })
        // create dialog box
        val alert = builder.create()
        // set title for alert dialog box
        alert.setTitle("End of Game")
        // show alert dialog
        alert.show()
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
            R.id.Numbers -> {
                val intent = Intent(this, NumbersGame::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}