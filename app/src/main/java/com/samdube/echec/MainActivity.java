package com.samdube.echec;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.samdube.echec.echiquier.Echiquier;
import com.samdube.echec.echiquier.Position;
import com.samdube.echec.piece.Piece;

import static com.samdube.echec.echiquier.Echiquier.TAILLE_ECHIQUIER;
import static com.samdube.echec.piece.Piece.CouleurPiece.BLANC;

public class MainActivity
        extends AppCompatActivity
        implements View.OnClickListener {

    private TableLayout m_chessboardTableLayout;
    private TextView m_debugingTextView;
    private Echiquier m_echiquier = new Echiquier();
    private Piece m_pieceSelectionner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_debugingTextView = findViewById(R.id.debuging_text);
        m_chessboardTableLayout = findViewById(R.id.main_board_id);
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
        b.setId(Integer.valueOf(String.valueOf(p_position.getX() + "" + p_position.getY())));
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
        ImageButton b = (ImageButton)v;
        Position position = (Position)b.getTag();

        if(m_pieceSelectionner != null && m_pieceSelectionner.getDeplacement().getPositionsDisponible().contains(position)){
            Position positionPiece = m_pieceSelectionner.getPosition();
            int buttonId = Integer.valueOf(String.valueOf(positionPiece.getX() + "" + positionPiece.getY()));
            ImageButton button = m_chessboardTableLayout.findViewById(buttonId);
            button.setImageDrawable(null);
            m_pieceSelectionner.setPosition(position);
            assignerImageBouton(m_pieceSelectionner, b);
            effacerDeplacementPossible();
            m_pieceSelectionner= null;
        }else{
            m_pieceSelectionner = m_echiquier.getPiece(position);
        }

        if(m_pieceSelectionner != null){
            afficherDeplacementPosssible();
        }else{
            effacerDeplacementPossible();
        }
    }

    private void afficherDeplacementPosssible() {
        for(Position positionDisponible : m_pieceSelectionner.getDeplacement().getPositionsDisponible()){
            int buttonId = Integer.valueOf(String.valueOf(positionDisponible.getX() + "" + positionDisponible.getY()));
            ImageButton button = m_chessboardTableLayout.findViewById(buttonId);
            button.setBackgroundColor(getColor(R.color.colorAccent));
        }
    }

    private void effacerDeplacementPossible() {
        for(int i = 0; i < m_chessboardTableLayout.getChildCount(); i++) {
            View view = m_chessboardTableLayout.getChildAt(i);
            if (m_chessboardTableLayout.getChildAt(i) instanceof TableRow) {
                TableRow row = (TableRow) view;
                for(int ii = 0; ii < row.getChildCount(); ii++){
                    if(row.getChildAt(ii) instanceof ImageButton){
                        ImageButton button = (ImageButton)row.getChildAt(ii);
                        button.setBackground(getDrawable(R.drawable.case_border));
                    }
                }
            }
        }
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        TableRow tr = genererTableRow();

        int hauteurCase = m_chessboardTableLayout.getHeight() / TAILLE_ECHIQUIER;
        int largeurCase = m_chessboardTableLayout.getWidth() / TAILLE_ECHIQUIER;
        int rangeDepart = 0;

        for (int i = 0; i < m_echiquier.getCases().length; i++) {
            Position position = m_echiquier.getCases()[i];
            ImageButton b = genererCaseEchiquier(largeurCase, hauteurCase, position);
            Piece piece = m_echiquier.getPiece(position);

            if (piece != null) {
                assignerImageBouton(piece, b);
            }

            if (position.getY() > rangeDepart) {
                m_chessboardTableLayout.addView(tr, 0);
                tr = genererTableRow();
                rangeDepart = position.getY();
            }

            tr.addView(b);

            if (i == m_echiquier.getCases().length - 1) {
                m_chessboardTableLayout.addView(tr, 0);
            }
        }
    }
}
