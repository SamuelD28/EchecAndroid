package com.samdube.echec.echiquier;

import com.samdube.echec.deplacement.Deplacement;
import com.samdube.echec.piece.Cavalier;
import com.samdube.echec.piece.Fou;
import com.samdube.echec.piece.Piece;
import com.samdube.echec.piece.Piece.CouleurPiece;
import com.samdube.echec.piece.Pion;
import com.samdube.echec.piece.Reine;
import com.samdube.echec.piece.Roi;
import com.samdube.echec.piece.Tour;

import java.util.ArrayList;
import java.util.Arrays;

import static com.samdube.echec.piece.Piece.CouleurPiece.*;

/**
 * Echiquier de base permettant l'ajout de pion
 *
 * @author Samuel Colassin
 * @author Samuel Dube
 */
public class Echiquier {
    /**
     * Constante contenant la taille largeur/longeur de l'échiquier
     */
    public final static int TAILLE_ECHIQUIER = 8;

    private final Position[] m_cases = new Position[TAILLE_ECHIQUIER * TAILLE_ECHIQUIER];

    private ArrayList<Piece> m_pieces = new ArrayList<>();

    private Piece m_pieceSelectionner = null;

    private final ArrayList<ArrayList<Piece>> m_listeDesChangements = new ArrayList<>();

    /**
     * Piece qui est présentement en cours de changement de type de pièce
     */
    private Piece m_pionPromu = null;

    /**
     * Indique s'il y une pièce qui est en cours de promotion dans l'échiquier
     */
    private boolean m_enCoursDePromotion = false;

    /**
     * Constructeur qui initialise l'échiquier
     */
    public Echiquier() {
        initialiser();
        calculerDeplacements();
        m_listeDesChangements.add(copierListeDesPieces());
    }

    /**
     * Constructeur utiliser pour plsu facilement tester
     * des scenarios comme le roque
     *
     * @param pieces Pieces de depart
     */
    Echiquier(Piece... pieces) {
        m_pieces = new ArrayList<>(Arrays.asList(pieces));
        calculerDeplacements();
        m_listeDesChangements.add(copierListeDesPieces());
    }

    /**
     * Methode qui initialise l'echiquier avec des m_cases vides
     * representer avec des X.
     */
    private void initialiser() {
        int i = 0;
        for (int y = 0; y < TAILLE_ECHIQUIER; y++) {
            for (int x = 0; x < TAILLE_ECHIQUIER; x++) {
                Position position = new Position(x, y);
                Piece piece = obtenirPiecePositionDepart(position);
                m_cases[i] = position;
                if (piece != null) {
                    m_pieces.add(piece);
                }
                i++;
            }
        }
    }

    /**
     * Methode permettant d'obtenir toutes les positions
     * des pieces d'une couleur donnee
     *
     * @param p_couleur Couleur des pieces a recenser
     * @return Positions des pieces de la couleur donne
     */
    private Position[] obtenirPositionsPieces(CouleurPiece p_couleur) {
        ArrayList<Position> positions = new ArrayList<>();
        for (Piece piece : m_pieces) {
            if (piece.getCouleur() == p_couleur) {
                positions.add(piece.getPosition());
            }
        }
        return positions.toArray(new Position[0]);
    }

    /**
     * Getter pour obtenir la piece selectionner
     * dans lechiquier
     *
     * @return La piece selectionner
     */
    public Piece getPieceSelectionner() {
        return m_pieceSelectionner;
    }

    /**
     * Setter pour selectionner une piece
     * pour lechiquier
     *
     * @param m_pieceSelectionner Piece selectionner
     */
    public void setPieceSelectionner(Piece m_pieceSelectionner) {
        this.m_pieceSelectionner = m_pieceSelectionner;
    }

    /**
     * Permet de calculer toutes les possibilités de collisions pour toutes les
     * pièces de l'échiquier
     */
    public void calculerDeplacements() {
        Position[] positionsPiecesBlanches = obtenirPositionsPieces(BLANC);
        Position[] positionsPiecesNoires = obtenirPositionsPieces(NOIR);
        for (Piece piece : m_pieces) {
            piece.calculerDeplacementPossibles(positionsPiecesBlanches, positionsPiecesNoires);
        }
        ajusterDeplacementRoi(BLANC);
        ajusterDeplacementRoi(NOIR);
        ajusterDeplacementRoque(BLANC);
        ajusterDeplacementRoque(NOIR);
    }

    /**
     * Methode qui ajoute des deplacements disponible
     * aux pieces cibles si un roque est possible
     *
     * @param p_couleur Couleur a verfier le roque
     */
    private void ajusterDeplacementRoque(CouleurPiece p_couleur) {
        int yaxis = (p_couleur == BLANC) ? 0 : 7;

        if (peutGrandRoque(p_couleur)) {
            Roi roi = getRoi(p_couleur);
            Tour tour = (Tour) getPiece(new Position(0, yaxis));

            roi.getDeplacement().ajouterDeplacementPossibles(tour.getPosition());
            tour.getDeplacement().ajouterDeplacementPossibles(roi.getPosition());
        }
        if (peutPetitRoque(p_couleur)) {
            Roi roi = getRoi(p_couleur);
            Tour tour = (Tour) getPiece(new Position(7, yaxis));

            roi.getDeplacement().ajouterDeplacementPossibles(tour.getPosition());
            tour.getDeplacement().ajouterDeplacementPossibles(roi.getPosition());
        }
    }

    /**
     * Methode pour obtenir un roi d'une couleur
     * donne.
     *
     * @param p_couleur Couleur du roi a trouver
     * @return Roi trouve
     */
    public Roi getRoi(CouleurPiece p_couleur) {
        Roi roi = null;
        for (Piece piece : m_pieces) {
            if (piece.getCouleur() == p_couleur && piece instanceof Roi) {
                roi = (Roi) piece;
            }
        }
        return roi;
    }

    /**
     * Methode permettant dajuster le deplacement d"un roi
     * dune couleur donne en fonction des mouvements quil ne
     * peut effectuer
     *
     * @param p_couleur Couleur du roi
     */
    private void ajusterDeplacementRoi(CouleurPiece p_couleur) {
        CouleurPiece couleurInverse = (p_couleur == BLANC) ? NOIR : BLANC;
        Position[] champsAction = getChampsDactions(couleurInverse);
        Roi roi = getRoi(p_couleur);
        Deplacement deplacementRoi = roi.getDeplacement();

        if (Arrays.asList(champsAction).contains(roi.getPosition())) {
            roi.setEstEchec(true);
        } else {
            roi.setEstEchec(false);
        }

        deplacementRoi.retirerDeplacementPossibles(champsAction);
        if (deplacementRoi.getDisponibles().length == 0 && roi.estEchec()) {
            roi.setEchecEtMath(true);
        }
    }

    /**
     * Getter pour savoir si une couleur est en echec
     *
     * @param p_couleur Couleur a verifier
     * @return Vrai la couleur est en echec
     */
    public boolean estEchec(CouleurPiece p_couleur) {
        return getRoi(p_couleur).estEchec();
    }

    /**
     * Getter pour savoir si une couleur est en echec et math
     *
     * @return Vrai si une couleur est en echec
     */
    public boolean estEchecEtMath() {
        return getRoi(NOIR).estEchecEtMath() || getRoi(BLANC).estEchecEtMath();
    }

    /**
     * Methode permettant de savoir si une couleur
     * peut effectuer un grand roque
     *
     * @param p_couleur Couleur a verifier
     * @return Vrai si on peut effectuer un petit roque
     */
    private boolean peutPetitRoque(CouleurPiece p_couleur) {
        int yaxis = (p_couleur == BLANC) ? 0 : 7;
        Piece piece = getPiece(new Position(7, yaxis));

        if (!(piece instanceof Tour)) {
            return false;
        }
        Tour tour = (Tour) piece;

        if (getRoi(p_couleur).jamaisJouer() || tour.jamaisJouer()) {
            return false;
        }

        return getPiece(new Position(5, yaxis)) == null &&
                getPiece(new Position(6, yaxis)) == null;
    }

    /**
     * Methode permettant de savoir si une couleur
     * petu effectuer un grand roque
     *
     * @param p_couleur Couleur a verifier
     * @return Vrai si on peut faire un grand roque
     */
    private boolean peutGrandRoque(CouleurPiece p_couleur) {
        int yaxis = (p_couleur == BLANC) ? 0 : 7;
        Piece piece = getPiece(new Position(0, yaxis));

        if (!(piece instanceof Tour)) {
            return false;
        }
        Tour tour = (Tour) piece;

        if (getRoi(p_couleur).jamaisJouer() || tour.jamaisJouer()) {
            return false;
        }

        return getPiece(new Position(1, yaxis)) == null &&
                getPiece(new Position(2, yaxis)) == null &&
                getPiece(new Position(3, yaxis)) == null;
    }

    /**
     * Permet d'obtenir la pièce à mettre sur l'échiquier
     * selon la position dans celui-ci.
     *
     * @param p_position Position de depart de la piece
     * @return Le type de pièce à mettre dans l'échiquier
     */
    private Piece obtenirPiecePositionDepart(Position p_position) {
        int x = p_position.getX();
        int y = p_position.getY();

        if (y == 1 || y == 6) {
            return new Pion(new Position(x, y));
        } else if (y == 0 || y == 7) {
            if (x == 0 || x == 7) {
                return new Tour(new Position(x, y));
            } else if (x == 1 || x == 6) {
                return new Cavalier(new Position(x, y));
            } else if (x == 2 || x == 5) {
                return new Fou(new Position(x, y));
            } else if (x == 3) {
                return new Reine(new Position(x, y));
            } else {
                return new Roi(new Position(x, y));
            }
        }

        return null;
    }

    /**
     * Methode calculant tout le champ d'action des pieces
     * dune couleur donne
     *
     * @param p_couleur Couleur a ressencer tout le champ daction
     * @return Le champ daction de la couleur
     */
    private Position[] getChampsDactions(CouleurPiece p_couleur) {
        ArrayList<Piece> pieces = new ArrayList<>();
        for (Piece piece : m_pieces) {
            if (piece.getCouleur() == p_couleur) {
                pieces.add(piece);
            }
        }

        ArrayList<Position> positions = new ArrayList<>();
        for (Piece piece : pieces) {
            positions.addAll(Arrays.asList(piece.getDeplacementsPossibles()));
        }

        return positions.toArray(new Position[0]);
    }

    /**
     * Permet d'obtenir la référence d'un pion
     * à une position donnée.
     *
     * @param p_position La position du pion désiré
     * @return Le pion à la position donnée
     */
    public Piece getPiece(Position p_position) {
        Piece pieceTrouve = null;
        for (Piece piece : m_pieces) {
            if (piece.getPosition().equals(p_position)) {
                pieceTrouve = piece;
                break;
            }
        }
        return pieceTrouve;
    }

    /**
     * Methode qui verifie si un deplacement met en peril le roi
     *
     * @param p_pieceADeplacer      Piece qui se deplace
     * @param p_positionDeplacement Position ou deplacer la piece
     * @return Vrai si le roi est en danger
     */
    private boolean deplacementMetEnPerilRoi(Piece p_pieceADeplacer, Position p_positionDeplacement) {
        Roi roi = getRoi(p_pieceADeplacer.getCouleur());
        Position positionOriginal = p_pieceADeplacer.getPosition();

        p_pieceADeplacer.deplacer(p_positionDeplacement);
        calculerDeplacements();

        if (roi.estEchec() || roi.estEchecEtMath()) {
            p_pieceADeplacer.getDeplacement().ajouterDeplacementPossibles(positionOriginal);
            p_pieceADeplacer.deplacer(positionOriginal);
            calculerDeplacements();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Methode qui determine si un deplacement peut sauver le roi
     *
     * @param p_pieceADeplacer      Piece qui se deplace
     * @param p_positionDeplacement Position du deplacement
     * @return Vrai si le deplacement sauve le roi
     */
    private boolean deplacementPeutSauverRoi(Piece p_pieceADeplacer, Position p_positionDeplacement) {
        Roi roi = getRoi(p_pieceADeplacer.getCouleur());
        Position positionOriginal = p_pieceADeplacer.getPosition();

        p_pieceADeplacer.deplacer(p_positionDeplacement);
        calculerDeplacements();

        if (!roi.estEchec() && !roi.estEchecEtMath()) {
            return true;
        } else {
            p_pieceADeplacer.getDeplacement().ajouterDeplacementPossibles(positionOriginal);
            p_pieceADeplacer.deplacer(positionOriginal);
            calculerDeplacements();
            return false;
        }
    }

    /**
     * Permet de déplacer une pièce dans l'échiquier
     *
     * @param p_piece    La pièce qu'on veut déplacer
     * @param p_nouvelle La position qu'on désire pour la pièce
     * @return Vrai si le déplacement à fonctionné sinon faux
     */
    public boolean deplacerPiece(Piece p_piece, Position p_nouvelle) {
        if (estEchecEtMath() || !p_piece.peutDeplacer(p_nouvelle)) {
            return false;
        }

        Piece piecePourPrise = getPiece(p_nouvelle);

        if (peutEffectuerRoque(p_piece, piecePourPrise)) {
            return effectuerRoque(p_piece, piecePourPrise);
        } else if (estEchec(p_piece.getCouleur())) {
            return deplacementPeutSauverRoi(p_piece, p_nouvelle);
        } else if (!deplacementMetEnPerilRoi(p_piece, p_nouvelle)) {
            m_pieces.remove(piecePourPrise);
            p_piece.deplacer(p_nouvelle);
            m_listeDesChangements.add(copierListeDesPieces());

            if (p_piece instanceof Pion && ((Pion) p_piece).getPeutPromotion()) {
                m_pionPromu = p_piece;
                m_enCoursDePromotion = true;
            }

            calculerDeplacements();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Methode qui verifie si les pieces en prises remplissent
     * les conditions dun roque
     *
     * @param piece      Piece qui effectue une prise
     * @param piecePrise Prise qui se fait prendre
     * @return Vrai si on peut roque
     */
    private boolean peutEffectuerRoque(Piece piece, Piece piecePrise) {
        return (piece instanceof Roi && piecePrise instanceof Tour && piece.getCouleur() == piecePrise.getCouleur() ||
                piece instanceof Tour && piecePrise instanceof Roi && piece.getCouleur() == piecePrise.getCouleur());
    }

    /**
     * Methode qui effectuer le deplacement de roque
     * pour une tour et un roi donne
     *
     * @param piece      Piece effectuant prise
     * @param piecePrise Piece etant prise
     * @return Vrai si le roque a ete effectuer
     */
    private boolean effectuerRoque(Piece piece, Piece piecePrise) {
        Roi roi;
        Tour tour;

        if (piece instanceof Roi) {
            roi = (Roi) piece;
            tour = (Tour) piecePrise;
        } else {
            roi = (Roi) piecePrise;
            tour = (Tour) piece;
        }

        int yaxis = (roi.getCouleur() == BLANC) ? 0 : 7;

        if (peutPetitRoque(roi.getCouleur()) && tour.getPosition().equals(new Position(7, yaxis))) {
            roi.getDeplacement().ajouterDeplacementPossibles(new Position(6, yaxis));
            roi.deplacer(new Position(6, yaxis));
            tour.deplacer(new Position(5, yaxis));
            return true;
        }
        if (peutGrandRoque(roi.getCouleur()) && tour.getPosition().equals(new Position(0, yaxis))) {
            roi.getDeplacement().ajouterDeplacementPossibles(new Position(2, yaxis));
            roi.deplacer(new Position(2, yaxis));
            tour.deplacer(new Position(3, yaxis));
            return true;
        }
        return false;
    }

    /**
     * Permet de revenir à un état précédant de l'échiquier
     */
    public void revenirEtatPrecedent() {
        if (m_listeDesChangements.size() > 1) {
            int lastIndex = m_listeDesChangements.size() - 2;
            m_pieces = m_listeDesChangements.get(lastIndex);
            m_listeDesChangements.remove(lastIndex + 1);
            calculerDeplacements();
        }
    }

    /**
     * Permet de copier en profondeur une liste des pièces sur l'échiquier
     *
     * @return La liste des pièces sur l'échiquier
     */
    private ArrayList<Piece> copierListeDesPieces() {
        ArrayList<Piece> nouvellesListe = new ArrayList<>();
        for (Piece piece : m_pieces) {
            nouvellesListe.add(Piece.creerCopie(piece));
        }
        return nouvellesListe;
    }

    /**
     * Permet de promouvoir un pion
     *
     * @param p_representation Representation pion
     */
    public void promouvoirPion(char p_representation) {
        if (m_pionPromu != null) {
            Position p = m_pionPromu.getPosition();
            CouleurPiece c = m_pionPromu.getCouleur();
            m_pieces.remove(m_pionPromu);

            switch (p_representation) {
                case 'r':
                    m_pieces.add(new Reine(p, c));
                    break;
                case 'f':
                    m_pieces.add(new Fou(p, c));
                    break;
                case 'c':
                    m_pieces.add(new Cavalier(p, c));
                    break;
                case 't':
                    m_pieces.add(new Tour(p, c));
                    break;
            }
            m_listeDesChangements.add(copierListeDesPieces());

            m_pionPromu = null;
            m_enCoursDePromotion = false;
        }
    }

    /**
     * Permet de savoir s'il y a un pion en cours de promotion dans l'échiquier
     *
     * @return Vrai si un pion est en cours de promotion
     */
    public boolean getEnCourDePromotion() {
        return m_enCoursDePromotion;
    }

    /**
     * Obtient toute les positions de l'échiquier
     *
     * @return Les position dans l'échiquier
     */
    public Position[] getCases() {
        return m_cases;
    }
}
