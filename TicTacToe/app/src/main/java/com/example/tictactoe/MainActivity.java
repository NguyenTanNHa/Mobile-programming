package com.example.tictactoe;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    private TicTacToe ticTacToe;
    private ButtonGridAndTextView buttonGridAndTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //setContentView(R.layout.activity_main);
        ticTacToe = new TicTacToe();
        buildGuiByCode();

        //ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
        //    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        //    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
        //   return insets;
        //});
    }

    private void buildGuiByCode() {
        //Get width of screen
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int w = size.x / TicTacToe.SIZE;//dùng chung SIZE của thằng tictactoe vì chuyển SIZE đi qua kia rồi

        ButtonHandler buttonHandler = new ButtonHandler(); //thg này của Controller, những cái khác ko đc can thiệp vô
        buttonGridAndTextView = new ButtonGridAndTextView(this,
                w, TicTacToe.SIZE, buttonHandler); //kết nối đc với bên kia

        //khởi tạo status play
        buttonGridAndTextView.setStatusText(ticTacToe.result());
        //Set GridLayout to Activity
        setContentView(buttonGridAndTextView); //thay gridLayout thành buttonGridAndTextView
    }
    public void update(int row, int col) {
        Log.w("MainActivity", "Click on button (" + row + "," + col);
        int play = ticTacToe.play(row, col);
        if (play == 1) {
            buttonGridAndTextView.setButtonText(row,col,"X");
        } else {
            if (play == 2) {
                buttonGridAndTextView.setButtonText(row,col,"O");
            }
        }
        if (ticTacToe.isGameOver()) {
            buttonGridAndTextView.enableButtons(false);
            buttonGridAndTextView.setStatusText(ticTacToe.result());
            buttonGridAndTextView.setStatusBackGroundColor(Color.RED);
            showNewGameDialog();
        }
    }





    private class ButtonHandler implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Log.w("MainActivity", "Inside onclick, v = "+v);
            for(int row=0; row < TicTacToe.SIZE; row++){
                for(int col=0; col < TicTacToe.SIZE; col++){
                    if(buttonGridAndTextView.isButton((Button) v,row,col)){
                        update(row,col);
                    }
                }
            }
        }
    }
    public void showNewGameDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Tic Tac Toe");
        alert.setMessage("Do you want to play again?");

        PlayDialog playDialog = new PlayDialog();
        alert.setPositiveButton("YES", playDialog);
        alert.setNegativeButton("NO", playDialog);
        alert.show();
    }

    private class PlayDialog implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == -1){ //YES button
                ticTacToe.resetGame();
                buttonGridAndTextView.enableButtons(true);
                buttonGridAndTextView.setStatusText(ticTacToe.result());
                buttonGridAndTextView.setStatusBackGroundColor(Color.GREEN);
                buttonGridAndTextView.resetButtons();
            } else if (which == -2) {// NO button
                MainActivity.this.finish();
            }
        }
    }


}