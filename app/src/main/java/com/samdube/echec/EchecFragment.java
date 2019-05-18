package com.samdube.echec;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.samdube.echec.echiquier.Echiquier;
import com.samdube.echec.echiquier.Position;
import com.samdube.echec.jeux.Manager;
import com.samdube.echec.piece.Piece;

import static android.support.v4.content.res.ResourcesCompat.getColor;
import static android.support.v4.content.res.ResourcesCompat.getDrawable;
import static com.samdube.echec.echiquier.Echiquier.TAILLE_ECHIQUIER;
import static com.samdube.echec.piece.Piece.CouleurPiece.BLANC;

/**
 * Classe qui permet de creer un fragement de jeu d'échec
 *
 * @author Samuel Colassin
 * @author Samuel Dubé
 */
public class EchecFragment extends Fragment implements View.OnClickListener {
    private TableLayout m_chessboardTableLayout;
    private TextView m_joueurEnTourTextView;
    private Echiquier m_echiquier = new Echiquier();
    private Piece m_pieceSelectionner;
    private Manager m_manager = new Manager(m_echiquier);
    private Button m_buttonReinitialiser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater p_inflater, @Nullable ViewGroup p_container, @Nullable Bundle p_savedInstanceState) {
        View view = p_inflater.inflate(R.layout.echec_layout, p_container, false);
        m_chessboardTableLayout = view.findViewById(R.id.main_board_id);
        m_joueurEnTourTextView = view.findViewById(R.id.joueur_en_tour_text_view);
        m_buttonReinitialiser = view.findViewById(R.id.button_reinitialiser);
        m_joueurEnTourTextView.setText(m_manager.getNomJoueurEnTour());
        m_buttonReinitialiser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //afficherDialogueNomJoueur();
                m_echiquier = new Echiquier();
                m_manager.reinitialiserPartie();
                m_joueurEnTourTextView.setText(m_manager.getNomJoueurEnTour());
                dessinerEchiquier();
            }
        });

        view.getViewTreeObserver().addOnWindowFocusChangeListener(new ViewTreeObserver.OnWindowFocusChangeListener() {
            @Override
            public void onWindowFocusChanged(final boolean p_hasFocus) {
                dessinerEchiquier();
            }
        });

        afficherDialogueNomJoueur();
        return view;
    }

    /**
     * Permet de dessiner l'échiquier à l'écran
     */
    private void dessinerEchiquier() {
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
            } else {
                b.setImageDrawable(null);
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

    /**
     * Permet de generer une tablerow pour l'échiquier
     *
     * @return Une TableRow
     */
    private TableRow genererTableRow() {
        TableRow tr = new TableRow(getContext());
        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        return tr;
    }

    /**
     * Permet de générer un case dans l'échiquier
     *
     * @param p_largeur  Largeur de la case
     * @param p_hauteur  Hauteur de la case
     * @param p_position Position de la case
     * @return Un ImageButton représentant la position de l'échiquier
     */
    private ImageButton genererCaseEchiquier(int p_largeur, int p_hauteur, Position p_position) {
        ImageButton b = new ImageButton(getContext());
        TableRow.LayoutParams layout = new TableRow.LayoutParams(p_largeur, p_hauteur);
        b.setLayoutParams(layout);
        b.setTag(p_position);
        b.setId(Integer.valueOf(String.valueOf(p_position.getX() + "" + p_position.getY())));
        b.setBackground(getDrawable(getResources(), R.drawable.case_border, null));
        b.setOnClickListener(this);
        return b;
    }

    /**
     * Permet d'ajouter une image au bouton
     *
     * @param p_piece  La piece qui ce trouve sur le bouton
     * @param p_bouton Le bouton à affecter l'image
     */
    public void assignerImageBouton(Piece p_piece, ImageButton p_bouton) {
        if (p_piece.getCouleur() == BLANC) {
            switch (p_piece.getRepresentation()) {
                case 'c':
                    p_bouton.setImageDrawable(getDrawable(getResources(), R.drawable.ic_cavalier_blanc, null));
                    break;
                case 'f':
                    p_bouton.setImageDrawable(getDrawable(getResources(), R.drawable.ic_fou_blanc, null));
                    break;
                case 'q':
                    p_bouton.setImageDrawable(getDrawable(getResources(), R.drawable.ic_reine_blanc, null));
                    break;
                case 'r':
                    p_bouton.setImageDrawable(getDrawable(getResources(), R.drawable.ic_roi_blanc, null));
                    break;
                case 't':
                    p_bouton.setImageDrawable(getDrawable(getResources(), R.drawable.ic_tour_blanc, null));
                    break;
                default:
                    p_bouton.setImageDrawable(getDrawable(getResources(), R.drawable.ic_pion_blanc, null));
                    break;
            }
        } else {
            switch (p_piece.getRepresentation()) {
                case 'c':
                    p_bouton.setImageDrawable(getDrawable(getResources(), R.drawable.ic_cavalier_noir, null));
                    break;
                case 'f':
                    p_bouton.setImageDrawable(getDrawable(getResources(), R.drawable.ic_fou_noir, null));
                    break;
                case 'q':
                    p_bouton.setImageDrawable(getDrawable(getResources(), R.drawable.ic_reine_noir, null));
                    break;
                case 'r':
                    p_bouton.setImageDrawable(getDrawable(getResources(), R.drawable.ic_roi_noir, null));
                    break;
                case 't':
                    p_bouton.setImageDrawable(getDrawable(getResources(), R.drawable.ic_tour_noir, null));
                    break;
                default:
                    p_bouton.setImageDrawable(getDrawable(getResources(), R.drawable.ic_pion_noir, null));
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        ImageButton b = (ImageButton) v;
        Position position = (Position) b.getTag();

        if (m_pieceSelectionner == null) {
            m_pieceSelectionner = m_echiquier.getPiece(position);
            if (m_pieceSelectionner != null &&
                    m_pieceSelectionner.getCouleur() == m_manager.getCouleurJoueurEnTour()) {
                afficherDeplacementPossible();
            } else {
                m_pieceSelectionner = null;
            }
        } else {
            Position positionPiece = m_pieceSelectionner.getPosition();
            if (m_echiquier.deplacerPiece(m_pieceSelectionner, position)) {
                int buttonId = Integer.valueOf(String.valueOf(positionPiece.getX() + "" + positionPiece.getY()));
                ImageButton button = m_chessboardTableLayout.findViewById(buttonId);
                button.setImageDrawable(null);


                if (m_echiquier.getPionEnCourDePromotion()) {

                    //afficherDialoguePromotion();
                    m_echiquier.promouvoirPion(/*m_manager.getPromotion()*/);

                    buttonId = Integer.valueOf(String.valueOf(position.getX() + "" + position.getY()));
                    button = m_chessboardTableLayout.findViewById(buttonId);
                    assignerImageBouton(m_echiquier.getPiece(position), button);
                } else {
                    assignerImageBouton(m_pieceSelectionner, b);
                }

                effacerDeplacementPossible();

                if (m_echiquier.estEchecEtMat()) {
                    Toast.makeText(getActivity(), "Partie gagnée par :" + m_manager.getNomJoueurEnTour(), Toast.LENGTH_SHORT).show();
                    //desactiverBoutton();
                }

                m_pieceSelectionner = null;
                m_manager.terminerTour();
                m_joueurEnTourTextView.setText(m_manager.getNomJoueurEnTour());
            } else {
                effacerDeplacementPossible();
                m_pieceSelectionner = null;
            }
        }
    }

    /**
     * Permet de colorer les boutons qui correspondent aux deplacements disponibles
     * lorsqu'une piec est sélectionner
     */
    private void afficherDeplacementPossible() {
        for (Position positionDisponible : m_pieceSelectionner.getDeplacementsPossibles()) {
            int buttonId = Integer.valueOf(String.valueOf(positionDisponible.getX() + "" + positionDisponible.getY()));
            ImageButton button = m_chessboardTableLayout.findViewById(buttonId);
            button.setBackgroundColor(getColor(getResources(), R.color.colorAccent, null));
        }
    }

    /**
     * Permet d'enlever la couleur des boutons qui étaient dans les déplacement disponibles de la
     * pièce précédement sélectionner
     */
    private void effacerDeplacementPossible() {
        for (int i = 0; i < m_chessboardTableLayout.getChildCount(); i++) {
            View view = m_chessboardTableLayout.getChildAt(i);
            if (m_chessboardTableLayout.getChildAt(i) instanceof TableRow) {
                TableRow row = (TableRow) view;
                for (int ii = 0; ii < row.getChildCount(); ii++) {
                    if (row.getChildAt(ii) instanceof ImageButton) {
                        ImageButton button = (ImageButton) row.getChildAt(ii);
                        button.setBackground(getDrawable(getResources(), R.drawable.case_border, null));
                    }
                }
            }
        }
    }

    /**
     * Permet d'afficher un dialogue pour obtenir le nom des joueurs
     */
    private void afficherDialogueNomJoueur() {
        AlertDialog dialogue = new AlertDialog.Builder(getActivity())
                //.setTitle("Noms Joueur blanc & Joueur Noir")
                .setPositiveButton(android.R.string.ok, null)
                .create();

        dialogue.setCanceledOnTouchOutside(false);
        dialogue.setCancelable(false);
        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        final EditText saisieJoueurBlanc = new EditText(getContext());
        final EditText saisieJoueurNoir = new EditText(getContext());
        final TextView textJ1 = new TextView(getContext());
        final TextView textJ2 = new TextView(getContext());
        textJ1.setText("Joueur Blanc");
        textJ2.setText("Joueur Noir");
        layout.addView(textJ1);
        layout.addView(saisieJoueurBlanc);
        layout.addView(textJ2);
        layout.addView(saisieJoueurNoir);
        dialogue.setView(layout);

        dialogue.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(final DialogInterface p_dialogue) {
                Button buttonOk = ((AlertDialog) p_dialogue).getButton(AlertDialog.BUTTON_POSITIVE);
                if (buttonOk != null) {
                    buttonOk.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {
                            if (saisieJoueurBlanc.getText().toString().trim().length() > 0
                                    && saisieJoueurNoir.getText().toString().trim().length() > 0) {
                                m_manager.setNomsJoueurs(saisieJoueurBlanc.getText().toString(),
                                        saisieJoueurNoir.getText().toString());
                                m_joueurEnTourTextView.setText(m_manager.getNomJoueurEnTour());
                                p_dialogue.dismiss();
                            } else {
                                Toast.makeText(getActivity(), "Nom trop court", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        dialogue.show();
    }

    // TODO ne marche pas vraiment
    private void afficherDialoguePromotion() {
        AlertDialog dialogue = new AlertDialog.Builder(getActivity())
                .setTitle("Veuillez entrer la premiere lettre de la piece que vous voulez")
                .setPositiveButton(android.R.string.ok, null)
                .create();

        dialogue.setCanceledOnTouchOutside(false);
        dialogue.setCancelable(false);
        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        final EditText promotion = new EditText(getContext());
        layout.addView(promotion);
        dialogue.setView(layout);

        dialogue.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(final DialogInterface p_dialogue) {
                Button buttonOk = ((AlertDialog) p_dialogue).getButton(AlertDialog.BUTTON_POSITIVE);
                if (buttonOk != null) {
                    buttonOk.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {
                            if (promotion.getText().toString().trim().length() == 1) {
                                m_manager.setPromotion(promotion.getText().charAt(0));
                                p_dialogue.dismiss();
                            } else {
                                Toast.makeText(getActivity(), "Trop de caractère", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        dialogue.show();
    }

    //TODO Faire fonctionner Désactiver les boutons
//    private void desactiverBoutton() {
//        //List<View> bouttons = m_chessboardTableLayout.getTouchables();
//
////        for(View v : bouttons){
////            if( v instanceof Button ) {
////                v.setEnabled(false);
////            }
////        }
//
//        for (int i = 0; i < m_chessboardTableLayout.getChildCount(); i++) {
//            View v = m_chessboardTableLayout.getChildAt(i);
//            if (v instanceof Button) {
//                v.setClickable(false);
//
//            }
//        }
//    }
}


//final TextView textJ1 = new TextView(getContext());
//final TextView textJ2 = new TextView(getContext());
//textJ1.setText("J1");
//textJ2.setText("J2");
//layout.addView(textJ1);
//layout.addView(textJ2);
