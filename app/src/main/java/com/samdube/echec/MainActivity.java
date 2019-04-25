package com.samdube.echec;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        TableLayout chessboard = (TableLayout) findViewById(R.id.main_board_id);

        for (int y = 0; y < 8; y++) {
            TableRow tr = new TableRow(this);
            int height = chessboard.getHeight();
            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            for (int x = 0; x < 8; x++) {
                Button b = new Button(this);
                b.setText("H");
                int width = chessboard.getWidth();
                b.setLayoutParams(new TableRow.LayoutParams(width / 8, height / 8));
                tr.addView(b);
            }
            chessboard.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }
}
