package piece;

import deplacement.Deplacement;
import deplacement.DeplacementReine;
import echiquier.Position;

import static piece.Piece.CouleurPiece.BLANC;

public class TestReine extends TestPiece {
    @Override
    public int getForceAttendu() {
        return 3;
    }

    @Override
    public char getRepresentationAttendu() {
        return 'q';
    }

    @Override
    public Deplacement getDeplacementAttendu() {
        return new DeplacementReine(getPiece().getPosition());
    }

    @Override
    public Position getPositionAttendu() {
        return new Position(0,0);
    }

    @Override
    public Piece.CouleurPiece getCouleurAttendu() {
        return BLANC;
    }

    @Override
    public Piece getPiece() {
        return new Reine(new Position(0,0));
    }
}
