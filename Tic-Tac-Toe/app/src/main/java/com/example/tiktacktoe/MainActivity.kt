package com.example.tiktacktoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var currentMove: String = "X"

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var button6: Button
    private lateinit var button7: Button
    private lateinit var button8: Button
    private lateinit var button9: Button

    private lateinit var textView: TextView
    private lateinit var newGameButton: Button

    private lateinit var buttons: Array<Array<Button>>

    private var moveCount: Int = 0
    private var isGameOver: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)
        button6 = findViewById(R.id.button6)
        button7 = findViewById(R.id.button7)
        button8 = findViewById(R.id.button8)
        button9 = findViewById(R.id.button9)

        buttons = arrayOf(
            arrayOf(button1, button2, button3),
            arrayOf(button4, button5, button6),
            arrayOf(button7, button8, button9)
        )

        textView = findViewById(R.id.textView)

        newGameButton = findViewById(R.id.newGameButton)

        textView.text = "Current Move: $currentMove"
    }

    fun button1Clicked(view: View) {
        handleClick(button1)
    }

    fun button2Clicked(view: View) {
        handleClick(button2)
    }

    fun button3Clicked(view: View) {
        handleClick(button3)
    }

    fun button4Clicked(view: View) {
        handleClick(button4)
    }

    fun button5Clicked(view: View) {
        handleClick(button5)
    }

    fun button6Clicked(view: View) {
        handleClick(button6)
    }

    fun button7Clicked(view: View) {
        handleClick(button7)
    }

    fun button8Clicked(view: View) {
        handleClick(button8)
    }

    fun button9Clicked(view: View) {
        handleClick(button9)
    }

    fun newGameButtonClicked(view: View) {
        button1.setText(" ")
        button2.setText(" ")
        button3.setText(" ")
        button4.setText(" ")
        button5.setText(" ")
        button6.setText(" ")
        button7.setText(" ")
        button8.setText(" ")
        button9.setText(" ")

        currentMove = "X"

        isGameOver = false

        moveCount = 0

        textView.setText("Current Move: $currentMove")
    }


    private fun changeMove() {
        if (this.currentMove == "X") {
            this.currentMove = "O"
        } else {
            this.currentMove = "X"
        }

        moveCount++

        if (moveCount < 9) {
            textView.setText("Current Move: $currentMove")
        } else {
            textView.setText("Tie!")
            isGameOver = true
        }
    }

    private fun handleClick(button: Button?) {
        if (isGameOver) {
            return
        }

        if (button != null) {
            if (button.text == " ") {
                button.setText(currentMove)

                val didWin: Boolean = checkForWin()

                if (didWin) {
                    isGameOver = true
                    textView.setText("Player $currentMove Wins!")
                } else {
                    changeMove()
                }
            } else {
                print("button already used")
            }
        } else {
            print("button's value is null")
        }
    }

    private fun checkForWin(): Boolean {
        val board = Array(3) { i ->
            Array(3) { j ->
                buttons[i][j].text.toString()
            }
        }

        // Check rows and columns
        for (i in 0 until 3) {
            if (board[i][0] != " " && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true // Row win
            }
            if (board[0][i] != " " && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return true // Column win
            }
        }

        // Check diagonals
        if (board[0][0] != " " && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true // Diagonal from top-left to bottom-right win
        }
        if (board[0][2] != " " && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true // Diagonal from top-right to bottom-left win
        }

        // No win found
        return false
    }

}