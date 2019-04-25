package com.samdube.echec;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.samdube.echec.echiquier.Echiquier;
import com.samdube.echec.echiquier.Position;
import com.samdube.echec.piece.Piece;

import static com.samdube.echec.echiquier.Echiquier.*;

public class MainActivity extends AppCompatActivity {

    private Echiquier m_echiquier = new Echiquier();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private TableRow genererTableRow(){
        TableRow tr = new TableRow(this);
        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        return tr;
    }

    private Button genererCaseEchiquier(int p_largeur, int p_hauteur, String p_texte, Position p_position){
        Button b = new Button(this);
        b.setLayoutParams(new TableRow.LayoutParams(p_largeur, p_hauteur));
        b.setTag(p_position);
        b.setText(String.valueOf(p_position.getX() + "," + String.valueOf(p_position.getY())));
        return b;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        TableLayout chessboard = findViewById(R.id.main_board_id);
        TableRow tr = genererTableRow();

        int hauteurCase = chessboard.getHeight() / TAILLE_ECHIQUIER;
        int largeurCase = chessboard.getWidth() / TAILLE_ECHIQUIER;
        int rangeDepart = 0;

        for(int i = 0; i <  m_echiquier.getCases().length; i++){
            String texteCase = "";
            Position position = m_echiquier.getCases()[i];
            Piece piece = m_echiquier.getPiece(position);

//            if(piece != null){
//                texteCase = String.valueOf(piece.getRepresentation());
//            }

            if(position.getY() > rangeDepart){
                chessboard.addView(tr, 0);
                tr = genererTableRow();
                rangeDepart = position.getY();
            }

            tr.addView(genererCaseEchiquier(largeurCase, hauteurCase, texteCase, position));

            if(i == m_echiquier.getCases().length - 1){
                chessboard.addView(tr, 0);
            }
        }
    }
}
