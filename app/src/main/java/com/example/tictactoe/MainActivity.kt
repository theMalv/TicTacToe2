package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var game : TicTac
    var tictacButtons = ArrayList<Int>(9);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        game = TicTac();
        Init();
    }

    fun Init() {
        for (i in 0..8)
        {
            tictacButtons.add(R.id.TT1 + i);
            Log.d("button", tictacButtons[i].toString());
        }
    }

    fun buttonTicTacClick(view: View) {
        playGame(tictacButtons.indexOf(view.id), view as ImageButton);
    }

    fun Reset(view :View) {
        for (buttonId in tictacButtons) {
            var button = findViewById<ImageButton>(buttonId);
            button.setImageResource(0);
        }
        game = TicTac();
    }

    fun playGame(cellid : Int, button : ImageButton)
    {
        if (game.isGameWinned)
        {
            Toast.makeText(this,"Winner is player ${if (game.firstPlayer) 1 else 2}",Toast.LENGTH_LONG).show();
            return;
        }

        SetTicTacButtonImage(button)

        game.tictacButtonPressed(cellid);
        if (game.isGameWinned) {
            Toast.makeText(this,"Winner is player ${if (game.firstPlayer) 1 else 2}",Toast.LENGTH_LONG).show();
        }
        else
           game.ChangePlayer();

    }

    fun SetTicTacButtonImage(button: ImageButton) {
        val tic = R.drawable.tic;
        val tac = R.drawable.tac;
        val tictacImage = if (game.firstPlayer) tac else tic;
        button.setImageResource(tictacImage);
    }
}