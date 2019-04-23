package piece;

import deplacement.Deplacement;
import deplacement.DeplacementPion;
import echiquier.Position;

public class TestPion extends TestPiece {
    @Override
    public int getForceAttendu() {
        return 1;
    }

    @Override
    public char getRepresentationAttendu() {
        return 'p';
    }

    @Override
    public Deplacement getDeplacementAttendu() {
        return new DeplacementPion(getPiece().getPosition());
    }

    @Override
    public Position getPositionAttendu() {
        return new Position(0,0);
    }

    @Override
    public Piece.CouleurPiece getCouleurAttendu() {
        return Piece.CouleurPiece.BLANC;
    }

    @Override
    public Piece getPiece() {
        return new Pion(new Position(0,0));
    }
}
