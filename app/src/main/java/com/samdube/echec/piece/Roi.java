package com.samdube.echec.piece;

import com.samdube.echec.deplacement.DeplacementRoi;
import com.samdube.echec.echiquier.Position;

/**
 * Classe permettant de gerer un roi
 *
 * @author Samuel Dube
 */
public class Roi extends Piece {
    private boolean m_estEchec;

    private boolean m_estEchecEtMath;

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
    public boolean estEchec() {
        return m_estEchec;
    }

    /**
     * Getter pour savoir si le roi est echec et mat
     *
     * @return Vrai si le roi est echec et math
     */
    public boolean estEchecEtMath() {
        return m_estEchecEtMath;
    }

    /**
     * Methode permettant d'assigner l'attribut
     * estEnEchec au roi
     *
     * @param m_estEnEchec Vrai si le roi est en echec
     */
    public void setEstEchec(boolean m_estEnEchec) {
        this.m_estEchec = m_estEnEchec;
    }

    /**
     * Setter pour changer la valeur de echec et math
     *
     * @param m_estEchecEtMath Valeur pour echec et math
     */
    public void setEchecEtMath(boolean m_estEchecEtMath) {
        this.m_estEchecEtMath = m_estEchecEtMath;
    }
}
