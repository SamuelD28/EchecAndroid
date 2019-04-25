package com.samdube.echec;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.samdube.echec.echiquier.Echiquier;
import com.samdube.echec.echiquier.Position;
import com.samdube.echec.piece.Piece;

import static com.samdube.echec.echiquier.Echiquier.*;
import static com.samdube.echec.piece.Piece.CouleurPiece.*;

public class MainActivity
        extends AppCompatActivity
        implements View.OnClickListener {

    private Echiquier m_echiquier = new Echiquier();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private TableRow genererTableRow() {
        TableRow tr = new TableRow(this);
        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        return tr;
    }

    private ImageButton genererCaseEchiquier(int p_largeur, int p_hauteur, Position p_position) {
        ImageButton b = new ImageButton(this);
        TableRow.LayoutParams layout = new TableRow.LayoutParams(p_largeur, p_hauteur);
        b.setLayoutParams(layout);
        b.setTag(p_position);
        b.setId(Integer.valueOf(String.valueOf(p_position.getX() + p_position.getY())));
        b.setBackground(getDrawable(R.drawable.case_border));
        b.setOnClickListener(this);
        return b;
    }

    public void assignerImageBouton(Piece p_piece, ImageButton p_bouton){
        if (p_piece.getCouleur() == BLANC) {
            switch (p_piece.getRepresentation()) {
                case 'c':
                    p_bouton.setImageDrawable(getDrawable(R.drawable.ic_cavalier_blanc));
                    break;
                case 'f':
                    p_bouton.setImageDrawable(getDrawable(R.drawable.ic_fou_blanc));
                    break;
                case 'q':
                    p_bouton.setImageDrawable(getDrawable(R.drawable.ic_reine_blanc));
                    break;
                case 'r':
                    p_bouton.setImageDrawable(getDrawable(R.drawable.ic_roi_blanc));
                    break;
                case 't':
                    p_bouton.setImageDrawable(getDrawable(R.drawable.ic_tour_blanc));
                    break;
                default:
                    p_bouton.setImageDrawable(getDrawable(R.drawable.ic_pion_blanc));
                    break;
            }
        }else{
            switch (p_piece.getRepresentation()) {
                case 'c':
                    p_bouton.setImageDrawable(getDrawable(R.drawable.ic_cavalier_noir));
                    break;
                case 'f':
                    p_bouton.setImageDrawable(getDrawable(R.drawable.ic_fou_noir));
                    break;
                case 'q':
                    p_bouton.setImageDrawable(getDrawable(R.drawable.ic_reine_noir));
                    break;
                case 'r':
                    p_bouton.setImageDrawable(getDrawable(R.drawable.ic_roi_noir));
                    break;
                case 't':
                    p_bouton.setImageDrawable(getDrawable(R.drawable.ic_tour_noir));
                    break;
                default:
                    p_bouton.setImageDrawable(getDrawable(R.drawable.ic_pion_noir));
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        TableLayout chessboard = findViewById(R.id.main_board_id);
        TableRow tr = genererTableRow();

        int hauteurCase = chessboard.getHeight() / TAILLE_ECHIQUIER;
        int largeurCase = chessboard.getWidth() / TAILLE_ECHIQUIER;
        int rangeDepart = 0;

        for (int i = 0; i < m_echiquier.getCases().length; i++) {
            Position position = m_echiquier.getCases()[i];
            ImageButton b = genererCaseEchiquier(largeurCase, hauteurCase, position);
            Piece piece = m_echiquier.getPiece(position);

            if (piece != null) {
                assignerImageBouton(piece, b);
            }

            if (position.getY() > rangeDepart) {
                chessboard.addView(tr, 0);
                tr = genererTableRow();
                rangeDepart = position.getY();
            }

            tr.addView(b);

            if (i == m_echiquier.getCases().length - 1) {
                chessboard.addView(tr, 0);
            }
        }
    }
}
