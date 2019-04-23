package piece;

import deplacement.Deplacement;
import deplacement.DeplacementRoi;
import echiquier.Position;
import piece.Piece.CouleurPiece;

import static piece.Piece.CouleurPiece.*;

public class TestRoi extends TestPiece {
    @Override
    public int getForceAttendu() {
        return 1;
    }

    @Override
    public char getRepresentationAttendu() {
        return 'r';
    }

    @Override
    public Deplacement getDeplacementAttendu() {
        return new DeplacementRoi(getPiece().getPosition());
    }

    @Override
    public Position getPositionAttendu() {
        return new Position(7,7);
    }

    @Override
    public CouleurPiece getCouleurAttendu() {
        return NOIR;
    }

    @Override
    public Piece getPiece() {
        return new Roi(new Position(7,7));
    }
}
