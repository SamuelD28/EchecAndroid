package com.samdube.echec.piece;

import com.samdube.echec.deplacement.Deplacement;
import com.samdube.echec.echiquier.Position;
import junit.framework.TestCase;
import com.samdube.echec.piece.Piece.CouleurPiece;

/**
 * Teste la classe pièce et ses méthodes
 *
 * @author Samuel Dubé
 */
public abstract class TestPiece extends TestCase {

    public abstract int getForceAttendu();

    public abstract char getRepresentationAttendu();

    public abstract Deplacement getDeplacementAttendu();

    public abstract Position getPositionAttendu();

    public abstract CouleurPiece getCouleurAttendu();

    public abstract Piece getPiece();

    /**
     * Teste la creation d"une nouvelle com.samdube.echec.piece et
     * verifie ses proprietes
     */
    public void testCreer(){
        assertEquals(getRepresentationAttendu(), getPiece().getRepresentation());
        assertEquals(getForceAttendu(), getPiece().getForce());
        assertEquals(getPositionAttendu(), getPiece().getPosition());
        assertEquals(getCouleurAttendu(), getPiece().getCouleur());
        assertEquals(getDeplacementAttendu().getClass(), getPiece().getDeplacement().getClass());
    }
}
