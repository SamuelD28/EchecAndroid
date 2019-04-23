package piece;

import deplacement.Deplacement;
import deplacement.DeplacementFou;
import echiquier.Position;

import static piece.Piece.CouleurPiece.BLANC;

public class TestFou extends TestPiece {
    @Override
    public int getForceAttendu() {
        return 2;
    }

    @Override
    public char getRepresentationAttendu() {
        return 'f';
    }

    @Override
    public Deplacement getDeplacementAttendu() {
        return new DeplacementFou(getPiece().getPosition());
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
        return new Fou(new Position(0,0));
    }
}
