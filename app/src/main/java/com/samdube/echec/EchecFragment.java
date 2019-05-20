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

import java.util.ArrayList;

import static android.support.v4.content.res.ResourcesCompat.getColor;
import static android.support.v4.content.res.ResourcesCompat.getDrawable;
import static com.samdube.echec.echiquier.Echiquier.TAILLE_ECHIQUIER;
import static com.samdube.echec.piece.Piece.CouleurPiece.BLANC;
import static com.samdube.echec.piece.Piece.CouleurPiece.NOIR;

/**
 * Classe qui permet de creer un fragement de jeu d'échec
 *
 * @author Samuel Colassin
 * @author Samuel Dubé
 */
public class EchecFragment extends Fragment implements View.OnClickListener {
    private TableLayout m_chessboardTableLayout;

    private TextView m_joueurEnTourTextView;

    private Echiquier m_echiquier;

    private Manager m_manager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater p_inflater, @Nullable ViewGroup p_container, @Nullable Bundle p_savedInstanceState) {
        View view = p_inflater.inflate(R.layout.echec_layout, p_container, false);
        m_chessboardTableLayout = view.findViewById(R.id.main_board_id);
        m_joueurEnTourTextView = view.findViewById(R.id.tourJoueur_textView);

        Button buttonReinitialiser = view.findViewById(R.id.button_reinitialiser);
        buttonReinitialiser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Init();
            }
        });

        Button buttonRevenir = view.findViewById(R.id.button6);
        buttonRevenir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m_echiquier.revenirEtatPrecedent();
                m_manager.revenirTour();
                dessinerEchiquier();
                actualiserEtatUI();
            }
        });

        Init();
        return view;
    }

    /**
     * Methode qui initialise une partie dechec
     */
    private void Init() {
        m_echiquier = new Echiquier();
        if (m_manager == null) {
            afficherDialogueNomJoueur();
            m_manager = new Manager(m_echiquier);
        }
        m_manager.reinitialiserPartie();
        dessinerEchiquier();
        actualiserEtatUI();
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

    private void actualierEchiquier(){
        for(Position p : m_echiquier.getCases()){
            int buttonId = Integer.valueOf(String.valueOf(p.getX() + "" + p.getY()));
            ImageButton button = m_chessboardTableLayout.findViewById(buttonId);
            Piece piece = m_echiquier.getPiece(p);
            if(piece!=null){
                assignerImageBouton(piece, button);
            }else{
                button.setImageDrawable(null);
            }
        }
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
        ImageButton imageBouton = (ImageButton) v;
        Position nouvellePosition = (Position) imageBouton.getTag();

        if (m_echiquier.getPieceSelectionner() == null) {
            selectionnerPieceEchiquier(nouvellePosition);
        } else {
            if (m_echiquier.deplacerPiece(m_echiquier.getPieceSelectionner(), nouvellePosition)) {
                m_echiquier.calculerTousDeplacements();
                m_manager.terminerTour();
            }

            deselectionnerPieceEchiquier();
        }
        if (m_echiquier.getEnCourDePromotion()) {
            afficherDialoguePromotion(nouvellePosition);
        }

        actualiserEtatUI();
    }

    /**
     * Methode qui deselectionner la piece selectionner
     * de lechiquier et qui met a jour UI
     */
    private void deselectionnerPieceEchiquier() {
        effacerDeplacementPossible();
        m_echiquier.setPieceSelectionner(null);
    }

    /**
     * Methode qui selectionne une piece de lechiquier
     * a partir de la grille et qui met a jour le ui
     *
     * @param p_position Position a selectionner dans la grille
     */
    private void selectionnerPieceEchiquier(Position p_position) {
        Piece pieceSelectionner = m_echiquier.getPiece(p_position);
        if (pieceSelectionner != null && pieceSelectionner.getCouleur() == m_manager.getCouleurJoueurEnTour()) {
            m_echiquier.setPieceSelectionner(pieceSelectionner);
            afficherDeplacementPossible();
        }
    }

    /**
     * Methode qui actualise le UI avec les differents qui
     * surviennent dans la partie
     */
    private void actualiserEtatUI() {
        actualierEchiquier();

        if (m_echiquier.estEchec(BLANC) || m_echiquier.estEchec(BLANC)) {
            Position positionRoi = m_echiquier.getRoi(BLANC).getPosition();
            changerCouleurCase(R.color.colorPrimary, positionRoi);
            Toast.makeText(getActivity(), "Echec joueur:" + m_manager.getNomJoueurEnTour(), Toast.LENGTH_SHORT).show();
        }

        if (m_echiquier.estEchec(NOIR) || m_echiquier.estEchecEtMath(NOIR)) {
            Position positionRoi = m_echiquier.getRoi(NOIR).getPosition();
            changerCouleurCase(R.color.colorPrimary, positionRoi);
            Toast.makeText(getActivity(), "Echec et math joueur:" + m_manager.getNomJoueurEnTour(), Toast.LENGTH_SHORT).show();
        }

        m_joueurEnTourTextView.setText(m_manager.getNomJoueurEnTour());
    }

    /**
     * Permet de colorer les boutons qui correspondent aux deplacements disponibles
     * lorsqu'une piec est sélectionner
     */
    private void afficherDeplacementPossible() {
        changerCouleurCase(R.color.colorAccent, m_echiquier.getPieceSelectionner().getDeplacementsPossibles());
    }

    /**
     * Methode pour changer la couleur dune case sur lechiquier
     *
     * @param p_colorID       Couleur a assinger
     * @param p_positionCases Positions des cases a changer la couleur
     */
    private void changerCouleurCase(int p_colorID, Position... p_positionCases) {
        for (Position position : p_positionCases) {
            int buttonId = Integer.valueOf(String.valueOf(position.getX() + "" + position.getY()));
            ImageButton button = m_chessboardTableLayout.findViewById(buttonId);
            button.setBackgroundColor(getColor(getResources(), p_colorID, null));
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
                .setPositiveButton(android.R.string.ok, null)
                .create();
        dialogue.setCanceledOnTouchOutside(false);
        dialogue.setCancelable(false);
        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        final EditText saisieJBlanc = new EditText(getContext());
        final EditText saisieJNoir = new EditText(getContext());
        final TextView texteJBlanc = new TextView(getContext());
        final TextView texteJNoir = new TextView(getContext());
        texteJBlanc.setText(R.string.joueur_blanc_dialogue);
        texteJNoir.setText(R.string.joueur_noir_dialogue);
        layout.addView(texteJBlanc);
        layout.addView(saisieJBlanc);
        layout.addView(texteJNoir);
        layout.addView(saisieJNoir);
        dialogue.setView(layout);
        dialogue.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface p_dialogue) {
                Button buttonOk = ((AlertDialog) p_dialogue).getButton(AlertDialog.BUTTON_POSITIVE);
                if (buttonOk != null) {
                    buttonOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String jblanc = saisieJBlanc.getText().toString().trim();
                            String jnoir = saisieJNoir.getText().toString().trim();

                            if (validerSaisieNom(jblanc, jnoir)) {
                                m_manager.setNomsJoueurs(jblanc, jnoir);
                                p_dialogue.dismiss();
                            } else {
                                Toast.makeText(getActivity(), R.string.validation_nom, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        dialogue.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                actualiserEtatUI();
                dessinerEchiquier();
            }
        });
        dialogue.show();
    }

    private void afficherDialoguePromotion(Position p_position) {
        AlertDialog dialogue = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.titre_promotion_dialogue)
                .create();
        dialogue.setCanceledOnTouchOutside(false);
        dialogue.setCancelable(false);
        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        final Button boutonReine = new Button(getContext());
        final Button boutonFou = new Button(getContext());
        final Button boutonCavalier = new Button(getContext());
        final Button boutonTour = new Button(getContext());
        boutonReine.setText(R.string.reine);
        boutonFou.setText(R.string.fou);
        boutonCavalier.setText(R.string.cavalier);
        boutonTour.setText(R.string.tour);
        boutonReine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promouvoirPion(p_position, 'r');
                dialogue.dismiss();
            }
        });
        boutonFou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promouvoirPion(p_position, 'f');
                dialogue.dismiss();
            }
        });
        boutonCavalier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promouvoirPion(p_position, 'c');
                dialogue.dismiss();
            }
        });
        boutonTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promouvoirPion(p_position, 't');
                dialogue.dismiss();
            }
        });
        layout.addView(boutonReine);
        layout.addView(boutonFou);
        layout.addView(boutonCavalier);
        layout.addView(boutonTour);
        dialogue.setView(layout);
        dialogue.show();
    }

    private void promouvoirPion(Position p_position, char p_representation) {
        m_echiquier.promouvoirPion(p_representation);
        int buttonId = Integer.valueOf(String.valueOf(p_position.getX() + "" + p_position.getY()));
        ImageButton button = m_chessboardTableLayout.findViewById(buttonId);
        assignerImageBouton(m_echiquier.getPiece(p_position), button);
    }

    private boolean validerSaisieNom(String p_nomBlanc, String p_nomNoir) {
        return !p_nomBlanc.equals(p_nomNoir) && p_nomBlanc.length() > 2 && p_nomNoir.length() > 2 &&
                p_nomBlanc.length() < 12 && p_nomNoir.length() < 12;
    }

    //TODO Faire fonctionner Désactiver les boutons
    private void desactiverBoutton() {
        ArrayList<View> bouttons = m_chessboardTableLayout.getTouchables();

        for (View v : bouttons) {
            if (v instanceof Button) {
                v.setEnabled(false);
            }
        }

        for (int i = 0; i < m_chessboardTableLayout.getChildCount(); i++) {
            View v = m_chessboardTableLayout.getChildAt(i);
            if (v instanceof Button) {
                v.setClickable(false);
            }
        }
    }
}