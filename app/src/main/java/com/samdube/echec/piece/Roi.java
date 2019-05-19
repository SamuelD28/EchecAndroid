package com.samdube.echec.piece;

import com.samdube.echec.deplacement.DeplacementRoi;
import com.samdube.echec.echiquier.Position;

/**
 * Classe permettant de gerer un roi
 *
 * @author Samuel Dube
 */
public class Roi extends Piece {
    private boolean m_estEnEchec;

    /**
     * Constructeur initiant un nouveau roi
     *
     * @param p_position Position du roi sur l'com.samdube.echec.echiquier
     */
    public Roi(Position p_position) {
        super(p_position, new DeplacementRoi(), 'r', 1);
    }

    /**
     * Getter permettant de savoir si le roi
     * est en echec
     *
     * @return Vrai si le roi est en echec
     */
    public boolean getEstEnEchec() {
        return m_estEnEchec;
    }

    /**
     * Methode permettant d'assigner l'attribut
     * estEnEchec au roi
     *
     * @param m_estEnEchec Vrai si le roi est en echec
     */
    public void setEstEnEchec(boolean m_estEnEchec) {
        this.m_estEnEchec = m_estEnEchec;
    }
}
