package com.example.tictactoe;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class ButtonGridAndTextView extends GridLayout {
    private int SIZE;
    private Button[][] buttons;
    private TextView status;

    public ButtonGridAndTextView(Context context, int width, int size,
                                 OnClickListener listener) {
        //size là truyền SIZE vô trong đây
        super(context);
        //tính kế thừa
        SIZE = size;

        //Create GridLayout
        setRowCount(SIZE + 1);
        setColumnCount(SIZE);

        //Create Button
        buttons = new Button[SIZE][SIZE];
        //
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                buttons[row][col] = new Button(context); //bên kia là this nhưng bên đây là context
                buttons[row][col].setTextSize((int) (width * 0.2));
                buttons[row][col].setOnClickListener(listener);
                addView(buttons[row][col], width, width);
            }

        }

        status = new TextView(context);
        GridLayout.Spec rowSpec = GridLayout.spec(SIZE, 1);
        GridLayout.Spec colSpec = GridLayout.spec(0, SIZE);
        GridLayout.LayoutParams lpStatus = new GridLayout.LayoutParams(rowSpec,colSpec);
        status.setLayoutParams(lpStatus);
        status.setWidth(SIZE*width);
        status.setHeight(width);
        status.setGravity(Gravity.CENTER);
        status.setTextSize((int)(width*.15));
        status.setBackgroundColor(Color.GREEN);
        //status.setText(ticTacToe.result());

        addView(status);
    }
    public void setButtonText(int row, int col, String text){
        buttons[row][col].setText(text);
    }
    public void setStatusText(String text){
        status.setText(text);
    }
    public void setStatusBackGroundColor(int color){
        status.setBackgroundColor(color);
    }
    public boolean isButton( Button button, int row, int col){
        return (button == buttons[row][col]); //kiểm coi nút đó phải nút mình bấm ko
    }
    public void enableButtons (boolean enabled) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                buttons[row][col].setEnabled(enabled);
            }
        }
    }
    public void resetButtons(){
        for (int row=0; row<SIZE; row++)
            for(int col=0; col<SIZE; col++)
                buttons[row][col].setText("");
    }
}
